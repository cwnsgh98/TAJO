<template>
  <div class="main">
    <div class="main-header">
      <h2 >즐거운 라이딩을 위한 플랫폼,TAJO</h2>
    </div>
    <div class= homeMiddle>
      <HomeCarousel/>
      <div class="right">
        <HomeReco/>
      </div>
    </div>
    <div class="homeMiddle-right">
      <HomeTeacherTajo/>
      <HomeWeather/>
    </div>
    
  </div>
</template>

<script setup>
import HomeCarousel from '../components/Homecompo/HomeCarousel.vue';
import HomeReco from '../components/Homecompo/HomeReco.vue';
import HomeWeather from '../components/Homecompo/HomeWeather.vue';
import HomeTeacherTajo from '../components/Homecompo/HomeTeacherTajo.vue';

</script>

<style scoped>
h2{
  font-size: 30px;
}
*{
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
.right{
  display: flex;
  align-items: center;
  margin: 20px;
}
.main{
  width: 100%;
  
}
.main-header{
  min-height: 167px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.homeMiddle{
  width: 100%;
  display: flex;
  justify-content: space-around;
 margin-bottom: 40px;
}
.homeMiddle-right{
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-bottom: 140px;
}
</style>