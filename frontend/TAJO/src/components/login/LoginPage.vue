<template>
    <div>
        <div class="mainbox">
            <div class="logintajo">
                <span class="tj"> 타조 로그인</span>
            </div>
            <div class="loginbox">
                <div class="login_id">
                    <img class="icon" src="@/assets/usericon.png">
                    <label for="userid"> 아이디 :</label>
                    <input id="userid" type="text" v-model="userid">
                </div>
                <div class="password_id">
                    <img class="icon" src="@/assets/passwordicon.png">
                    <label for="password"> 비밀번호 : </label>
                    <input id="password" type="password" v-model="password">
                </div>
                <div class="btn_box">
                    <button @click="LoginUser">
                    <img class="btn" src="@/assets/로그인버튼.png">
                    </button>
                    <button @click="CancelLogin">
                        <img class="btn" src="@/assets/취소버튼.png">
                    </button>
                </div>
            </div>
            <div class="footergo">
                <RouterLink to="/regist">
                    <img class="goregist" src="@/assets/아직회원.png">
                </RouterLink>
            </div>
        </div>
    </div>
</template>

<script setup>

import { ref } from 'vue';
import {useRouter} from 'vue-router'


const router = useRouter();
const userid = ref("");
const password= ref("");

const emit = defineEmits(["login-user"]);
const CancelLogin = () => {
    router.push("/");
}
const LoginUser = () => {
    const user = {
        userid: userid.value,
        password: password.value,
    };
    emit("login-user", user);
};

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
    width: 90px;
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
    width: 530px;
    height: 290px;
}
.mainbox{
    display: flex;
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