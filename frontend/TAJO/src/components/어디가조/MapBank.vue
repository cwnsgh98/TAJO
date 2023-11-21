<template>
  <div>
    <div class="맵밑에">
      <span>장소 검색기</span>
      <div>
        <input v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
        <button @click="searchPlaces">검색</button>
      </div>
    </div>
    <div id="map"></div>

    <div class="편의시설선택">
      <button class="편의시설버튼" @click="toggleSelect">편의시설 선택하기</button>

      <label for="fruitSelect" v-show="isSelectVisible">편의시설 선택:</label>
      <select id="fruitSelect" v-show="isSelectVisible" v-model="selectedFruit" @change="searchNearbyPlaces">
        <option v-for="fruit in fruits" :key="fruit.id" :value="fruit.id">{{ fruit.name }}</option>
      </select>

      <p>선택한 편의시설: {{ selectedFruitName }}</p>
    </div>
    <p>
      <input type="checkbox" id="chkUseDistrict" @click="setOverlayMapTypeId" /> 지적편집도 정보 보기
      <input type="checkbox" id="chkTerrain" @click="setOverlayMapTypeId" /> 지형정보 보기
      <input type="checkbox" id="chkTraffic" @click="setOverlayMapTypeId" /> 교통정보 보기
      <input type="checkbox" id="chkBicycle" @click="setOverlayMapTypeId" /> 자전거도로 정보 보기
    </p>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';

const fruits = [
  { id: '', name: '자전거 코스' },
  { id: 'CE7', name: '화장실' },
  { id: 'PM9', name: '약국' },
  { id: 'CS2', name: '편의점' },
  // 다른 편의시설들...
];
const selectedFruit = ref(null);
const isSelectVisible = ref(false);

const selectedFruitName = computed(() => {
  const selected = fruits.find(fruit => fruit.id === selectedFruit.value);
  return selected ? selected.name : '';
});

const toggleSelect = () => {
  isSelectVisible.value = !isSelectVisible.value;
};

let map = null;
let infowindow = null;
let ps = null;
const searchKeyword = ref('자전거 코스');
let markers = [];
const markerInfo = ref([]);

onMounted(async () => {
  if (window.kakao && window.kakao.maps) {
    initMap();
  } else {
    const script = document.createElement('script');
    script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=4db16c8d32c6e47d1849987c33d4cc9c&libraries=services';
    document.head.appendChild(script);
    script.onload = () => {
      kakao.maps.load(initMap);
    };
  }
});

const initMap = () => {
  const container = document.getElementById('map');
  const options = {
    center: new kakao.maps.LatLng(37.566826, 126.9786567),
    level: 7,
  };
  map = new kakao.maps.Map(container, options);

  infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
  ps = new kakao.maps.services.Places(map);



  var mapTypeControl = new kakao.maps.MapTypeControl();

  // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
  // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
  map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  var zoomControl = new kakao.maps.ZoomControl();


  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
  kakao.maps.event.addListener(map, 'idle', function () {
    // do something
    searchPlaces();
    printMarkerNames();
    clearMarkerInfo();
  });

  // map.addOverlayMapTypeId(kakao.maps.MapTypeId.TERRAIN); 
  // 사용자의 현재 위치를 얻어오는 함수 호출
  getCurrentLocation();
  console.log('kakao object:', window.kakao);


};
function printMarkerNames() {
  console.log('현재 지도에 표시된 마커들의 이름:');
  console.log('markerInfo 배열의 크기:', markerInfo.value.length);
  markerInfo.value.forEach(marker => {
    console.log(marker.name);
  });
};

function setOverlayMapTypeId() {
  let mapTypes = {
    terrain: kakao.maps.MapTypeId.TERRAIN,
    traffic: kakao.maps.MapTypeId.TRAFFIC,
    bicycle: kakao.maps.MapTypeId.BICYCLE,
    useDistrict: kakao.maps.MapTypeId.USE_DISTRICT
  };
  var chkTerrain = document.getElementById('chkTerrain'),
    chkTraffic = document.getElementById('chkTraffic'),
    chkBicycle = document.getElementById('chkBicycle'),
    chkUseDistrict = document.getElementById('chkUseDistrict');

  // 지도 타입을 제거합니다
  for (var type in mapTypes) {
    map.removeOverlayMapTypeId(mapTypes[type]);
  }

  // 지적편집도정보 체크박스가 체크되어있으면 지도에 지적편집도정보 지도타입을 추가합니다
  if (chkUseDistrict.checked) {
    map.addOverlayMapTypeId(mapTypes.useDistrict);
  }

  // 지형정보 체크박스가 체크되어있으면 지도에 지형정보 지도타입을 추가합니다
  if (chkTerrain.checked) {
    map.addOverlayMapTypeId(mapTypes.terrain);
  }

  // 교통정보 체크박스가 체크되어있으면 지도에 교통정보 지도타입을 추가합니다
  if (chkTraffic.checked) {
    map.addOverlayMapTypeId(mapTypes.traffic);
  }

  // 자전거도로정보 체크박스가 체크되어있으면 지도에 자전거도로정보 지도타입을 추가합니다
  if (chkBicycle.checked) {
    map.addOverlayMapTypeId(mapTypes.bicycle);
  }

}




// 사용자의 현재 위치를 얻어오는 함수
const getCurrentLocation = () => {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(
      (position) => {
        const lat = position.coords.latitude;
        const lng = position.coords.longitude;
        const currentLocation = new kakao.maps.LatLng(lat, lng);

        // 지도의 중심을 현재 위치로 이동
        map.setCenter(currentLocation);

        // 장소 검색 서비스 호출 (주변 검색)
        ps.categorySearch('', placesSearchCB, { useMapBounds: true });
      },
      (error) => {
        console.error('Error getting user location:', error);
      }
    );
  } else {
    console.error('Geolocation is not supported by this browser.');
  }
};

function placesSearchCB(data, status, pagination) {
  if (status === kakao.maps.services.Status.OK) {
    for (let i = 0; i < data.length; i++) {
      displayMarker(data[i]);
    }
  }
}

// function displayMarker(place) {
//   const marker = new kakao.maps.Marker({
//     map: map,
//     position: new kakao.maps.LatLng(place.y, place.x),
//   });

//   markers.push(marker);

//   //마커를 클릭할때 실행되는 함수
//   kakao.maps.event.addListener(marker, 'click', function () {
//     console.log('장소 상세 정보:', place.place_name);
//     infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
//     infowindow.open(map, marker);
//   });
// }
function displayMarker(place) {

  const marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(place.y, place.x),
  });

  markers.push(marker);

  // 마커 정보를 markerInfo 배열에 추가
  markerInfo.value.push({
    name: place.place_name,
    position: { latitude: place.y, longitude: place.x },
  });

  // 마커를 클릭할 때 실행되는 함수
  kakao.maps.event.addListener(marker, 'click', function () {
    console.log('장소 상세 정보:', place.place_name);
    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
    infowindow.open(map, marker);
  });
}


const clearMarkerInfo = () => {
  markerInfo.value = [];
};


function removeMarkers() {
  for (let i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }
  markers = [];
}

const searchPlaces = () => {
  removeMarkers();
  infowindow.close();
  ps.keywordSearch(searchKeyword.value, placesSearchCB, { useMapBounds: true });
};

const searchNearbyPlaces = () => {
  removeMarkers();
  const selectedFruitInfo = fruits.find(fruit => fruit.id === selectedFruit.value);

  if (selectedFruitInfo) {
    searchKeyword.value = selectedFruitInfo.name;
    // 편의시설 주변 장소 검색
    // ps.categorySearch(selectedFruitInfo.id, placesSearchCB, { useMapBounds: true });
    searchPlaces();
  }
};


</script>

<style scoped>
.맵밑에 {
  width: 350px;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 7px;
}

#map {
  margin: 10px 20px 20px 20px;
  width: 1130px;
  height: 550px;
}
</style>
