<template>
  <div>
    <div id="map">
      <div class="맵밑에">
        <!-- <span>장소 검색기</span>
        <div class="장소검색기">
          <input v-model="searchKeyword" placeholder="장소 검색어를 입력하세요">
          <button @click="searchPlaces">검색</button>
        </div> -->
        <div class="체크박스">
          <input type="checkbox" id="chkBicycle" @click="setOverlayMapTypeId" /> <span>자전거도로 정보 보기</span>
        </div>
      </div>
      <div class="편의시설선택" >
        <select id="fruitSelect" v-model="selectedFruit" @change="searchNearbyPlaces">
          <option v-for="fruit in fruits" :key="fruit.id" :value="fruit.id">{{ fruit.name }}</option>
        </select>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useCourseStore } from '../../stores/course';
import {useRouter} from 'vue-router';
const router = useRouter();
const courseStore = useCourseStore();

const fruits = [

  { id: '편의시설을 선택해주세요', name: '편의시설을 선택해주세요' },
  { id: 'CE7', name: '화장실' },
  { id: 'PM9', name: '약국' },
  { id: 'CS2', name: '편의점' },
  // 다른 편의시설들...
];
const selectedFruit = ref('편의시설을 선택해주세요');
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
    setTimeout(()=> printMarkerNames(), 150)
    searchNearbyPlaces();
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
  courseStore.clearCourseList();
  markerInfo.value.forEach(async marker => {
    await courseStore.getMarkerCourse(marker.courseid);

  });
  console.log(courseStore.courseList)
};

function setOverlayMapTypeId() {
  let mapTypes = {
    terrain: kakao.maps.MapTypeId.TERRAIN,
    traffic: kakao.maps.MapTypeId.TRAFFIC,
    bicycle: kakao.maps.MapTypeId.BICYCLE,
    useDistrict: kakao.maps.MapTypeId.USE_DISTRICT
  };
  var chkBicycle = document.getElementById('chkBicycle');
  // chkTerrain = document.getElementById('chkTerrain'),
    // chkTraffic = document.getElementById('chkTraffic'),
    // chkUseDistrict = document.getElementById('chkUseDistrict');

  // 지도 타입을 제거합니다
  for (var type in mapTypes) {
    map.removeOverlayMapTypeId(mapTypes[type]);
  }

  // 지적편집도정보 체크박스가 체크되어있으면 지도에 지적편집도정보 지도타입을 추가합니다
  // if (chkUseDistrict.checked) {
  //   map.addOverlayMapTypeId(mapTypes.useDistrict);
  // }

  // // 지형정보 체크박스가 체크되어있으면 지도에 지형정보 지도타입을 추가합니다
  // if (chkTerrain.checked) {
  //   map.addOverlayMapTypeId(mapTypes.terrain);
  // }

  // // 교통정보 체크박스가 체크되어있으면 지도에 교통정보 지도타입을 추가합니다
  // if (chkTraffic.checked) {
  //   map.addOverlayMapTypeId(mapTypes.traffic);
  // }

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
      // Check if the place category is "자전거 코스"
      if (data[i].category_name !== "서비스,산업 > 건설,건축 > 건설자재") {
        displayMarker(data[i]);
      }
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
const emit = defineEmits(['markerClick']);
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
    category: place.category_name,
    courseid : place.id,
  });
  
  // 마커를 클릭할 때 실행되는 함수
  kakao.maps.event.addListener(marker, 'click', function () { 
    console.log('장소 상세 정보:', place);
    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
    infowindow.open(map, marker);
    emit('markerClick', place.id);
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
    const searchKeywords = ['자전거 코스', selectedFruitInfo.name];
    searchKeywords.forEach(keyword => {
      searchKeyword.value = keyword;
      searchPlaces();

  });
}
};


</script>


<style scoped>

button {
  height: 40px;
  width: 40px;
  background: #151825;
  border-radius: 50%;
  border: 1px solid #1D9AF2;
  border-top: 2px solid #fff;
  animation: spinner1 600ms linear infinite;
}

button.loading {
  cursor: not-allowed; /* 추가: 로딩 중일 때는 클릭 비활성화 */
}

@keyframes spinner1 {
  to {
    transform: rotate(360deg);
  }
}

#map {
  position: relative;
  width: 1200px;
  height: 712px;
  z-index: 2;
}
.체크박스{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 24px;
  width: 160px;
  background-color: #FFFFFF;
  border: 1px solid #000000;
}
.체크박스 span{
  margin-left: 5px;
}

.맵밑에 {
  position: absolute;
  top: 0px;
  right: 90px;
  width: 200px;
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin: 7px; /* 여백 조절 */
  z-index: 4;
}

.편의시설선택 {
  position: absolute;
  bottom: 20px;
  left: 600px;
  display: flex;
  align-items: center;
  z-index: 4;
  background-color: #FFFFFF;
}
.fruitSelect{
  width: 130px;
  height: 30px;
}
#fruitSelect {
    font-size: 16px;
    padding: 10px;
    border: 2px solid #3d3d3d; /* 연핑크 색상으로 설정 */
    border-radius: 5px;
    background-color: #fff; /* 배경색을 흰색으로 설정 */
    color: #1d1d1d; /* 글자색을 연핑크로 설정 */
    outline: none; /* 포커스시 테두리 제거 */
    cursor: pointer;
    transition: border-color 0.3s ease; /* 변화 시 부드럽게 전환되도록 설정 */
    font-family: 'cookierun';
  }

  #fruitSelect:hover,
  #fruitSelect:focus {
    border-color: #2e2e2e; /* 호버 및 포커스시 테두리 색상 변경 */
  }

  /* 드롭다운 메뉴 스타일링 */
  #fruitSelect option {
    background-color: #fff;
    color: #ff69b4;
  }

  /* 선택된 항목 스타일링 */
  #fruitSelect option:checked {
    background-color: #b25aff;
    color: #fff;
  }
/* 나머지 스타일링을 원하는 대로 추가하세요 */
</style>