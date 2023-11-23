<template>
  <div class="weather" :style="{ backgroundImage: 'url(' + backsrc + ')' }">
    <div class="main">
      <div class="main-left">
        <div class="오늘의날씨">
          <span>{{ getWeatherDescription() }}</span>
        </div>
        <div class="날씨">
          <div>
            <p v-if="loading">위치 정보를 불러오는 중입니다...</p>
            <p v-else>
              <!-- <p class="pcute"><span class="cute">현재 위치</span> : {{ latitude }}, {{ longitude }}</p> -->
              <div class="weathermain">
                <div class="weathertop">
                  <p class="pcute">
                    <img class="날씨이미지" :src="getWeatherImage()">
                    <span><span class="cute">하늘 상태</span> : {{ weatherData.sky }}</span>
                  </p>
                </div>
                <!-- <p class="pcute"><span class="cute">강수 형태</span> : {{ weatherData.pty }}</p> -->
                <div class="weatherbottom">
                  <p class="pcute">
                    <img src="@/assets/umbrella.png">
                    <span class="cute">강수 확률</span>{{ weatherData.pop }}%
                  </p>
                  <p class="pcute">
                    <img src="@/assets/windy.png">
                    <span class="cute">바람</span>{{ weatherData.wsd }}m/s
                  </p>
                  <p class="pcute">
                    <img src="@/assets/humidity.png">
                    <span class="cute">습도</span>{{ weatherData.reh }}%
                  </p>
                </div>
              </div>
            </p>
          </div>
        </div>
      </div>
      <div class="main-right">
        <div class="옷추천">
          <p class="pcute"><span class="cute 기온">기온 : {{ weatherData.tmp }}</span> </p>
          <span>{{ getOutfitRecommendation() }}</span>
        </div>
        <div class="옷이미지">
          <img :src="getOutfitImage()">
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const loading = ref(true);
    const latitude = ref(null);
    const longitude = ref(null);
    const backsrc=ref('src/assets/backwind2.gif');
    const weatherData = ref({
      tmp: null,
      sky: null,
      pty: null,
      pop: 0,
      wsd:0,
      vec: 0,
      reh:0,
    });

    const getLocation = () => {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(
          showPosition,
          showError
        );
      } else {
        alert('Geolocation is not supported by this browser.');
      }
    };

    const showPosition = async (position) => {
      loading.value = false;
      latitude.value = position.coords.latitude;
      longitude.value = position.coords.longitude;

      await fetchWeatherData(latitude.value, longitude.value);
    };

    const fetchWeatherData = async (lat, lon) => {
      const API_URL = 'http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst';
      const today = new Date();
      let year = today.getFullYear();
      let month = today.getMonth() + 1;
      let day = today.getDate();
      month = month < 10 ? '0' + month : month;
      day = day < 10 ? '0' + day : day;
      const todayStr = `${year}${month}${day}`;

      try {
        // 예제에서는 단순히 위도와 경도를 더해서 nx, ny 계산
        const nx = Math.floor(lat + 0.5); // 간단한 예시
        const ny = Math.floor(lon + 0.5); // 간단한 예시

        const response = await axios.get(API_URL, {
          params: {
            ServiceKey: import.meta.env.VITE_WEATHER_API_KEY,
            dataType: 'JSON',
            base_date: todayStr,
            base_time: '0200',
            numOfRows: 15,
            nx,
            ny,
          },
        });

        const data = response.data.response.body.items.item;

      //TMP : 1시간 기온 ℃
      //UUU : 풍속(동서) m/s
      //VVV : 풍속(남북) m/s
      //VEC : 풍향 deg
      //WSD : 풍속 m/s
      //SKY : 하늘상태 --> 코드 : 맑음(1), 구름많음(3), 흐림(4)
      //PTY : 강수형태 --> 코드 : 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
      //POP : 강수확률 %
      //WAV : 파고 M
      //PCP : 1시간 강수량 범주(1mm)
      //REH : 습도 %
      //SNO : 1시간 적설량
      //TMN : 일 최저기온 ℃ x
      //TMX : 일 최고기온 ℃ x

        data.forEach((item) => {
          if (item.category === 'TMP') {
            weatherData.value.tmp = item.fcstValue;
          } else if (item.category === 'SKY') {
            switch (item.fcstValue) {
              case '1':
                weatherData.value.sky = '맑음';
                backsrc.value='src/assets/backsunny2.gif';
                break;
              case '3':
                weatherData.value.sky = '구름많음';
                break;
              case '4':
                weatherData.value.sky = '흐림';
                backsrc.value='src/assets/backsunny2.gif';
                break;
            }
          } else if (item.category === 'PTY') {
            weatherData.value.pty = item.fcstValue;
          } else if (item.category === 'POP') {
            weatherData.value.pop = item.fcstValue;
          } else if (item.category ==='WSD'){
            weatherData.value.wsd = item.fcstValue;
          } else if (item.category ==='REH'){
            weatherData.value.reh = item.fcstValue;
          }
        });
      } catch (error) {
        console.error('Error fetching weather data:', error);
      }
    };

    

    const showError = (error) => {
      loading.value = false;
      switch (error.code) {
        case error.PERMISSION_DENIED:
          alert('User denied the request for Geolocation.');
          break;
        case error.POSITION_UNAVAILABLE:
          alert('Location information is unavailable.');
          break;
        case error.TIMEOUT:
          alert('The request to get user location timed out.');
          break;
        case error.UNKNOWN_ERROR:
          alert('An unknown error occurred.');
          break;
      }
    };

    onMounted(() => {
      backsrc.value='src/assets/backwind2.gif'
      getLocation();
    });

    return { loading, latitude, longitude, weatherData, backsrc, };
  },
  methods: {
    getWeatherDescription() {
      if (this.weatherData.sky === '맑음') {
        return '오늘의 날씨는, 맑아요!';
      } else if (this.weatherData.sky === '구름많음') {
        return '오늘의 날씨는, 구름이 많아요!';
      } else if (this.weatherData.sky === '흐림') {
        return '오늘의 날씨는, 흐려요!';
      } else {
        return '오늘의 날씨는, 라이딩하기 좋은 날씨에요!';
      }
    },
    getOutfitRecommendation() {
      if (this.weatherData.tmp <= 0) {
        return '오늘 짱추워요 두껍게 입거나 실내 스포츠를 즐겨봅시다.';
      } else if (this.weatherData.tmp <= 10){
        return '따수운 외투를 챙기면 좋아요. 추운날씨입니다';
      } else if (this.weatherData.tmp <= 15){
        return '약간 쌀쌀한 기온이랍니다. 따숩게 입어볼까요?';
      }else if (this.weatherData.tmp <= 23){
        return '최고의 라이딩 날씨!!!! 가볍게 입어볼까요?';
      } else {
        return '뜨거운 기온이랍니다 가볍게 입어볼까요?'
      }
    },
    getOutfitImage() {
      if (this.weatherData.tmp <= 0) {
        return 'src/assets/clothes1.gif';
      } else if (this.weatherData.tmp <= 10) {
        return 'src/assets/clothes2.gif';
      }else if (this.weatherData.tmp <= 15) {
        return 'src/assets/clothes3.gif';
      }else if (this.weatherData.tmp <= 23) {
        return 'src/assets/clothes4.gif';
      }else {
        return 'src/assets/clothes5.png';
      }
    },
    getWeatherImage(){
      if (this.weatherData.sky =='맑음' && this.weatherData.pty==0) {
        return 'src/assets/weathersun.gif';
      } else if (this.weatherData.sky == '흐림' && this.weatherData.pty==0) {
        return 'src/assets/weatherclouds.gif';
      }else if (this.weatherData.sky == '구름많음' && this.weatherData.pty==0) {
        return 'src/assets/weatherclouds.gif';
      }else if (this.weatherData.pty==1) {
        return 'src/assets/weatherrain.gif';
      }else if (this.weatherData.pty==2) {
        return 'src/assets/weatherrain.gif';
      }else if (this.weatherData.pty==3) {
        return 'src/assets/weatherrain.gif';
      }else if (this.weatherData.pty==4){
        return 'src/assets/weathersnow.gif';
      }
      console.log(this.weatherData.pty)
      console.log(this.weatherData.sky)
      
    },
  },
}

</script>

<style scoped>
.기온{
  font-size: 20px;
}
.오늘의날씨{
  width: 263.930px;
padding: 14px 0px 10px 19px;
display: flex;
justify-content: center;
}
p img{
  width: 50px;
  height: 50px;
}
.weathertop ,.weatherbottom{
  display: flex;
  justify-content: center;
}
.weathermain{
  display: flex;
  flex-direction: column;
}
.pcute{
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 14px;
}
.cute{
  min-width: 30px;
  /* border: solid 2px #F96C85;
  border-radius: 40px; */
  padding: 3px;
  padding-left: 9px;
  padding-right: 9px;
}
.weather {
    width: 900px;
    height: 300px;
    /* background: url('/src/assets/backrain2.gif'); */
    /* background: url('/src/assets/backsunny2.gif'); */
    /* background: url('/src/assets/backwind2.gif'); */
    /* background-repeat: no-repeat; */
    /* background-position: center; */
    /* background: url('/src/assets/backsnow2.gif'); */
}

.main {
  min-height: 250px;
    display: flex;
    justify-content: space-around;
    border: 2px solid #5C5C5C;
    border-radius: 30px;
}

.main-left{
  display: flex;
    flex-direction: column;
    justify-content: space-around;
}
.main-right {
    
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
img, .날씨이미지{
    width: 130px;
    height: 130px;
}
</style>