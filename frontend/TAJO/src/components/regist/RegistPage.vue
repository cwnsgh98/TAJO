<template>
    <div>
        <div class="mainbox">
            <div class="logintajo">
                <span class="tj"> 타조 회원가입</span>
            </div>
            <div class="loginbox">
                <div class="login_id">
                    <img class="icon" src="@/assets/ostrich1.png">
                    <label for="userid"> 지역 :</label>
                    <input id="userid" type="text" v-model="user.city">
                </div>
                <div class="login_id">
                    <img class="icon" src="@/assets/ostrich2.png">
                    <label for="userid"> 닉네임 :</label>
                    <input id="userid" type="text" v-model="user.nicname">
                </div>
                <div class="login_id">
                    <img class="icon" src="@/assets/ostrich3.png">
                    <label for="userid"> 아이디 :</label>
                    <input id="userid" type="text" v-model="user.id">
                </div>
                <div class="login_id">
                    <img class="icon" src="@/assets/ostrich4.png">
                    <label for="userid"> 비밀번호 :</label>
                    <input id="userid" type="text" v-model="user.password">
                </div>
                <div class="login_id">
                    <img class="icon" src="@/assets/ostrich5.png">
                    <label for="userid"> 비밀번호확인 :</label>
                    <input id="userid" type="text" v-model="user.passwordcheck">
                </div>
                <div class="password_id">
                    <img class="icon" src="@/assets/ostrich6.png">
                    <label for="password"> 이메일 : </label>
                    <input id="password" type="password" v-model="user.email">
                </div>
                <div class="btn_box">
                    <button @click="LoginUser">
                    <img class="btn" src="@/assets/가입버튼.png">
                    </button>
                    <button @click="LoginUser">
                        <img class="btn" src="@/assets/취소버튼.png">
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

import { useRouter } from 'vue-router'
import { ref } from 'vue';
const user = ref({
    city:'',
    nicname:'',
    id: '',
    password:'',
    passwordcheck:'',
    email: ''
})

const router = useRouter();

const LoginUser = function () {
    router.push({ name: 'home' })
}


</script>

<style scoped>
.footergo{
    padding: 20px;
}
.tj{
    font-size: 30px;
    font-weight: 700;
}
.logintajo{
    width: 90%;
    display: flex;
    padding: 30px;
    justify-content: center;

}
label{
    width: 100px;
    height: 21px;
    margin-left: 20px;
}
.btn{
    width: 83px;
    height: 27px;
}
.login_id, .password_id{
    margin-left: 60px;
    display: flex;
    align-items: center;
    padding: 15px;
}
button{
    border-radius: 20px;
    border: solid 2px #FFFFFF;
    background: #F7CAC9;
}
input{
    border-radius: 20px;
    background: #F7CAC9;
    height: 27px;
}
.btn_box{
    margin-top: 20px;
    width: 100%;
    display: flex;
    justify-content:space-evenly;
    align-items: center;

}
.loginbox{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    border: 3px solid #A4A4A4;
    border-radius: 40px;
    width: 520px;
    height: 600px;
}
.mainbox{
    display: flex;
    width: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.goregist {
    width: 230px;
    height: 18px;
}

.icon {
    height: 35px;
    width: 30px;
}
</style>