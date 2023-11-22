<template>
    <div class="result">
        <button class="돌아가기" @click="showWriteform">다시 뜨겁게 달리기</button>
        <h2>today's total</h2>
        <div class="result-top">
            <div class="box">
                <img src="@/assets/이미지시간.png">
                <span>라이딩 시간</span>
                <span>{{ store.todayTime }}분</span>
            </div>

            <div class="box">
                <img src="@/assets/이미지자전거.png">
                <span>라이딩 거리</span>
                <span>{{ store.todayDist }}km</span>
            </div>

            <div class="box">
                <img src="@/assets/이미지불꽃.png">
                <span>태운 칼로리</span>
                <span>{{ store.todayCal }}kcal</span>
            </div>
        </div>
        <div class="box box2">
            <img :src="coffeeImage" alt="Coffee Image">
            <span class="텍스트">{{ coffeeText }}</span>
            <span>{{ store.todayCal }} kcal</span>
        </div>
        <div class="riding-distance">
            <span>현재까지 라이딩한 거리는</span>
            <div class="distance-bottom">
                <span>전체사용자 중 </span>
                <span class="pink">상위 {{ distStore.distRank == 0 ? 1 : distStore.distRank }}%</span>
                <span>예요.</span>
            </div>
        </div>
        <div class="meettajo">
            <span class="stroked-text"> {{ currentMonth }}월에는 타조를</span>
            <span class="stroked-text"> {{ recordStore.dayCount }}번 만났어요</span>
        </div>
        <div class="calendar">
            <div class="header">Sun</div>
            <div class="header">Mon</div>
            <div class="header">Tue</div>
            <div class="header">Wed</div>
            <div class="header">Thu</div>
            <div class="header">Fri</div>
            <div class="header">Sat</div>

            <!-- Empty Cells for November 1st -->
            <div class="day empty"></div>
            <div class="day empty"></div>
            <div class="day empty"></div>

            <!-- Days of November -->
            <div v-for="day in 30" :key="day" :class="{ 'day': true, 'highlight': recordStore.day.has(day) }"
                @mouseover="showInfoWindow(day)" @mouseout="hideInfoWindow(day)">
                <span>{{ day }}</span>
            </div>
            <!-- 수정된 부분: day-info 컴포넌트에 선택된 날짜 전달 -->
            <DayInfo :day="selectedDay" :showInfo="showInfo" :infoWindowStyle="infoWindowStyle" />
        </div>
    </div>
</template>
  
<script>
import { ref, watch, onMounted } from 'vue';
import DayInfo from '@/components/마이타조/DayInfo.vue';
import { useTodayStore } from '@/stores/today';
import { useDistanceStore } from '@/stores/distance';
import { useRecordStore } from '@/stores/record';

export default {
    components: {
        DayInfo,
    },

    setup(props, { emit }) {
        const showWriteform = () => {
            emit('show-writeform');
        };
        const store = useTodayStore();
        const distStore = useDistanceStore();
        const recordStore = useRecordStore();
        const currentMonth = ref(0);
        const currentDay = ref(0);
        const selectedDay = ref(null);
        const showInfo = ref(false);

        currentMonth.value = new Date().getMonth() + 1;
        currentDay.value = new Date().getDate();

        const infoWindowStyle = ref({});
        watch(selectedDay, (newDay) => {
            const index = newDay - 1;
            const top = Math.floor((index + 3) / 7) * 50 + 450;
            const left = ((index + 3) % 7) * 47 + 1270;

            infoWindowStyle.value = {
                top: `${top}px`,
                left: `${left}px`,
            };
        });

        const coffeeImage = ref('');
        const coffeeText = ref('');

        const setCoffeeImage = (todayCal) => {
            if (store.todayCal <= 30) {
                return "src/assets/ostrich1.png";
            } else if (todayCal <= 50) {
                return "src/assets/orange.png";
            } else if (todayCal <= 100) {
                return "src/assets/이미지커피.png";
            } else if (todayCal <= 150) {
                return "src/assets/cake-slice.png";
            } else if (todayCal <= 200) {
                return "src/assets/food.png";
            } else if (todayCal <= 250) {
                return "src/assets/fried-potatoes.png";
            } else if (todayCal <= 300) {
                return "src/assets/chicken-leg.png";
            } else if (todayCal <= 350) {
                return "src/assets/pizza.png";
            } else {
                return "src/assets/ostrich8.png";
            }

        }
        const setCoffeeText= (todayCal)=> {
            if (store.todayCal <= 30) {
                return "이정도는 운동이 아닙니다";
            }
            else if (todayCal <= 50) {
                return "오렌지 하나 만큼 불태웠어요";
            } else if (todayCal <= 100) {
                return "카푸치노 한잔만큼 달렸어요";
            } else if (todayCal <= 150) {
                return "케이트 한조각의 반만큼 달렸어요!";
            } else if (todayCal <= 200) {
                return "와!!! 붕어빵!!!";
            } else if (todayCal <= 250) {
                return "감자튀김만큼 달렸어요 대 감 자";
            } else if (todayCal <= 300) {
                return "치킨닭다리 하나만큼 달리셨어용";
            } else if (todayCal <= 350) {
                return "피자 세조각만큼 달렸어요";
            } else {
                return "너무달리셨어요";
            }

        }

        // watch 함수의 사용 방법 변경
        onMounted(() => {
            coffeeText.value = setCoffeeText(store.todayCal);
                coffeeImage.value = setCoffeeImage(store.todayCal);
            watch(() => [store.todayCal], ([todayCal]) => {
                coffeeText.value = setCoffeeText(todayCal);
                coffeeImage.value = setCoffeeImage(todayCal);
            });

        });


        function showInfoWindow(day) {
            selectedDay.value = day;
            showInfo.value = true;
            recordStore.plusThisDayDist(day);
        }

        function hideInfoWindow(day) {
            selectedDay.value = null;
            showInfo.value = false;
            recordStore.thisDayDist = 0;
        }

        return {
            showWriteform,
            coffeeImage,
            coffeeText,
            store,
            distStore,
            recordStore,
            currentMonth,
            currentDay,
            selectedDay,
            showInfo,
            showInfoWindow,
            hideInfoWindow,
            infoWindowStyle,
        };
    },
};
</script>

<style scoped>
.텍스트{
    margin-top: 6px;
}
.돌아가기 {
    border: solid 1px #000000;
    border-radius: 20px;
    padding: 10px 20px;
    cursor: pointer;
    background-color: #F7CAC9;
    color: #000000;
    transition: background-color 0.3s;
    margin: 20px;
    font-family: 'cookierun';
}

.riding-distance,
.meettajo {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding-bottom: 40px;
    padding-top: 40px;
}

.day.highlight {
    background-color: #FC9797;
    /* 형광 분홍색 또는 다른 원하는 색상으로 설정 */
    color: #000000;
    /* 글자 색상을 흰색으로 설정 */
}

.result-top,
.box2 {
    padding-top: 30px;

}

.날짜 {
    font-size: 10px;
}

span {
    font-size: 18px;
    font-weight: 700;
    font-family: 'cookierun';
}

.pink {
    color: rgb(226, 67, 120);
}

img {
    height: 60px;
    width: 60px;
}

.stroked-text {
    -webkit-text-stroke: 2px #FC9797;
    /* Safari 및 Chrome 브라우저에서 사용 가능 */
    color: white;
    /* 텍스트 색상 지정 */
    font-size: 32px;
    font-family: 'cookierun';
}

.calendar {
    display: grid;
    grid-template-columns: repeat(7, 1fr);
    gap: 1px;
    background-color: #eee;
    border: 1px solid #ccc;
    width: 300px;
    height: 300px;
    overflow: hidden;
    margin-bottom: 80px;
}

.day {
    padding: 6px;
    text-align: center;
    border: 1px solid #ccc;
    background-color: #fff;
}

.header {
    text-align: center;
    padding: 6px;
    background-color: #ddd;
}

.empty {
    background-color: #eee;
    visibility: hidden;
    /* 빈 칸을 보이지 않도록 설정 */
}

.box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    height: 114px;
    widows: 97px;
}

.result-top {
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 70%;
}

.result {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}
</style>