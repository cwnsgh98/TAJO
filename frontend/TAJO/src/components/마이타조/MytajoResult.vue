<template>
    <div class="result">
        <div class="result-top">
            <div class="box">
                <img src="@/assets/이미지시간.png">
                <span>라이딩 시간</span>
                <span>15 : 00</span>
            </div>

            <div class="box">
                <img src="@/assets/이미지자전거.png">
                <span>라이딩 거리</span>
                <span>15 km</span>
            </div>

            <div class="box">
                <img src="@/assets/이미지불꽃.png">
                <span>태운 칼로리</span>
                <span>55 kcal</span>
            </div>
        </div>
        <div class="box box2">
            <img src="@/assets/이미지커피.png">
            <span>카푸치노 한잔 만큼 불태웠어요</span>
            <span>55 kcal</span>
        </div>
        <div class="riding-distance">
            <span>현재까지 라이딩한 거리는</span>
            <div class="distance-bottom">
                <span>전체사용자 중 </span>
                <span class="pink">상위 10%</span>
                <span>예요.</span>
            </div>
        </div>
        <div class="meettajo">
            <span class="stroked-text"> 11월에는 타조를</span>
            <span class="stroked-text"> 6번 만났어요</span>
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
            <div class="day">1</div>
            <div class="day">2</div>
            <div class="day">3</div>
            <div class="day">4</div>
            <div class="day">5</div>
            <div class="day">6</div>
            <div class="day">7</div>
            <div class="day">8</div>
            <div class="day">9</div>
            <div class="day">10</div>
            <div class="day">11</div>
            <div class="day">12</div>
            <div class="day">13</div>
            <div class="day">14</div>
            <div class="day">15</div>
            <div class="day">16</div>
            <div class="day">17</div>
            <div class="day">18</div>
            <div class="day">19</div>
            <div class="day">20</div>
            <div class="day">21</div>
            <div class="day">22</div>
            <div class="day">23</div>
            <div class="day">24</div>
            <div class="day">25</div>
            <div class="day">26</div>
            <div class="day">27</div>
            <div class="day">28</div>
            <div class="day">29</div>
            <div class="day">30</div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.riding-distance,
.meettajo {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding-bottom: 40px;
    padding-top: 40px;
}

.result-top,
.box2 {
    padding-top: 30px;

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
  visibility: hidden; /* 빈 칸을 보이지 않도록 설정 */
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