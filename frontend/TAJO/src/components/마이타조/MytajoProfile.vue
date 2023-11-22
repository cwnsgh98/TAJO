<template>
    <div class="Profilemain">
        <div class="profile">
            <div  v-if="user">
            <div class="pro">
            </div>
            <div class="프로필">
                <span class="gradepyo">{{nickname}}</span> 
                <a class="check">프로필편집</a>
            </div>
            </div>
            <div v-if="!user">
            <div class="anonymous" >
            </div>
            <RouterLink  :to="{ name: 'login' }">
                로그인 하러가기
            </RouterLink>
            <span class="gradepyo" >회원 정보가 없습니다.</span>
        </div>
        </div>
        <div class="tajo-grade">
            <span class="gradepyo">내 타조 등급 :</span>
            <div class="boxbox">
                <img :src="src" alt="Ostrich Image">
                    <span class="mykm">{{store.totalDist}}km</span>
                </div>
        </div>
        <div class="tajo-excel">
            <span class="gradepyo">타조등급표</span>
            <div class="타조등급1">
                <div class="boxbox">
                    <img src="@/assets/egg.png">
                    <span>0</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich1.png">
                    <span>100</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich2.png">
                    <span>500</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich3.png">
                    <span>1000</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich4.png">
                    <span>2000</span>
                </div>
            </div>
            <div class="타조등급2">
                <div class="boxbox">
                    <img src="@/assets/ostrich5.png">
                    <span>3000</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich6.png">
                    <span>5000</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich7.png">
                    <span>10000</span>
                </div>
                <div class="boxbox">
                    <img src="@/assets/ostrich8.png">
                    <span>20000</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted,onUpdated, watch} from 'vue'
import eggImage from '@/assets/egg.png';
import ostrich1Image from '@/assets/ostrich1.png';
import ostrich2Image from '@/assets/ostrich2.png';
import ostrich3Image from '@/assets/ostrich3.png';
import ostrich4Image from '@/assets/ostrich4.png';
import ostrich5Image from '@/assets/ostrich5.png';
import ostrich6Image from '@/assets/ostrich6.png';
import ostrich7Image from '@/assets/ostrich7.png';
import ostrich8Image from '@/assets/ostrich8.png';
import { useDistanceStore } from '@/stores/distance'
import { useRecordStore } from '@/stores/record'
const store = useDistanceStore();
const recordStore = useRecordStore();
const user = ref(null);
const grade = ref("등급이 없습니다");
const nickname = ref("로그인 해 주세요");
const src = ref("");
onMounted(() => {
    
    const savedUser = localStorage.getItem("loginUser");
    
    if (savedUser) {
        user.value = JSON.parse(savedUser);
        grade.value = user.value.grade;
        nickname.value = user.value.nickname;
        src.value = getOstrichImage(grade.value);
    }

    watch(() => [recordStore.grade], ([grade]) => {
  src.value = getOstrichImage(grade);
});
});
// onUpdated(() => {
    
//     const savedUser = localStorage.getItem("loginUser");
    
//     if (savedUser) {
//         user.value = JSON.parse(savedUser);
//         grade.value = user.value.grade;
//         nickname.value = user.value.nickname;
//         src.value = getOstrichImage(grade.value);
//     }
// });

const getOstrichImage = (grade) => {
  switch (grade) {
    case 'egg':
      return eggImage;
    case 'bronze':
      return ostrich1Image;
    case 'silver':
      return ostrich2Image;
    case 'gold':
      return ostrich3Image;
    case 'platinum':
      return ostrich4Image;
    case 'emerald':
      return ostrich5Image;
    case 'diamond':
      return ostrich6Image;
    case 'master':
      return ostrich7Image;
    case 'challenger':
      return ostrich8Image;
    default:
      return ostrich1Image; // 기본 값
  }
};
</script>

<style scoped>
*{
    box-sizing: border-box;
}
.프로필{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.profile,.tajo-excel,.tajo-grade{
    padding-top: 30px;
    padding-bottom: 20px;
    padding-left: 0px;
    padding-right: 0px;
}
.check{
    color: rgb(90, 97, 233);
    font-weight: 600;
}

.gradepyo{
    font-size: 24px;
    font-weight: 700;
    padding-top: 7px;
    padding-bottom: 9px;
}
.boxbox {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin: 6px;
}
.boxbox span{
    font-weight: 700;
}
.타조등급1,
.타조등급2 {
    display: flex;
    justify-content: space-around;
}

img {
    width: 47px;
    height: 50px;
}

.tajo-grade {
    display: flex;
    align-items: center;
    justify-content: center;
}

.Profilemain {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    height: 750px;
    width: 380px;
    background-color: #F5F5E9;
}

.tajo-excel {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background-color: #F5F5E9;
}

.profile {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

.pro {
    width: 205px;
    height: 205px;
    border-radius: 102.5px;
    border: 2px solid #000;
    background: url('@/assets/누오.webp') rgb(255, 255, 243, 1) 50% / 100% 100% no-repeat;

}

.anonymous {
    width: 205px;
    height: 205px;
    border-radius: 102.5px;
    border: 2px solid #000;
    background: url('@/assets/anonymity.png') rgb(255, 255, 243, 1) 50% / 100% 100% no-repeat;

}

</style>