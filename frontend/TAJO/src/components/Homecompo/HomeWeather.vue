<template>
    <div class="weather">
        <div class="main">
            <div class="main-left">
                <div class="오늘의날씨">
                    <span>오늘의 날씨는, 라이딩하기 좋은 날씨 에요!</span>
                </div>
                <div class="날씨">
                    <div>
                        <p v-if="loading">위치 정보를 불러오는 중입니다...</p>
                        <p v-else>
                            현재 위치: {{ latitude }}, {{ longitude }}
                        <p>기온: {{ weatherData.tmp }}</p>
                        <p>하늘 상태: {{ weatherData.sky }}</p>
                        <p>강수 형태: {{ weatherData.pty }}</p>
                        <p>강수 확률: {{ weatherData.pop }}%</p>
                        </p>
                    </div>

                </div>
            </div>
            <div class="main-right">
                <div class="옷추천">
                    <span>가벼운 외투를 챙기면 좋아요.</span>
                </div>
                <div class="옷이미지">
                    <img src="@/assets/가벼운외투.png">
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
    const weatherData = ref({
      tmp: null,
      sky: null,
      pty: null,
      pop: 0,
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
                break;
              case '3':
                weatherData.value.sky = '구름많음';
                break;
              case '4':
                weatherData.value.sky = '흐림';
                break;
            }
          } else if (item.category === 'PTY') {
            weatherData.value.pty = item.fcstValue;
          } else if (item.category === 'POP') {
            weatherData.value.pop = item.fcstValue;
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
      getLocation();
    });

    return { loading, latitude, longitude, weatherData };
  },
};

</script>

<style scoped>
.weather {
    width: 900px;
    height: 300px;
}

.main {
    margin-top: 5%;
    display: flex;
    justify-content: space-between;
    border: 2px solid #5C5C5C;
}

.main-left,
.main-right {
    display: flex;
    flex-direction: column;
}

img {
    width: 160px;
    height: 160px;
}
</style>