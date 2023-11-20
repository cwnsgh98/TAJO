<template>
    <div>
      <div class="mainbox">
        <div class="logintajo">
          <span class="tj"> 타조 회원가입</span>
        </div>
        <div class="loginbox">
          <!-- 나는 평소에 이렇게 달려요 -->
          
  
          <!-- 닉네임 -->
          <div class="login_id">
            <img class="icon" src="@/assets/ostrich2.png">
            <label for="nickname"> 닉네임 :</label>
            <input id="nickname" type="text" v-model="nickname">
          </div>
  
          <!-- 아이디 -->
          <div class="login_id">
            <img class="icon" src="@/assets/ostrich3.png">
            <label for="userid"> 아이디 :</label>
            <input id="userid" type="text" v-model="userid">
          </div>
  
          <!-- 비밀번호 -->
          <div class="login_id">
            <img class="icon" src="@/assets/ostrich4.png">
            <label for="password"> 비밀번호 :</label>
            <input id="password" type="password" v-model="password">
          </div>
  
          <!-- 비밀번호 확인 -->
          <div class="login_id">
            <img class="icon" src="@/assets/ostrich5.png">
            <label for="passwordcheck"> 비밀번호확인 :</label>
            <input id="passwordcheck" type="password" v-model="passwordcheck">
          </div>
  
          <!-- 가입 버튼과 취소 버튼 -->
          <div class="btn_box">
            <button @click="regist">
              <img class="btn" src="@/assets/가입버튼.png">
            </button>
            <button @click="cancelRegist">
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
  
  const router = useRouter();
  const emit = defineEmits(["create-user"]);
  
  const userid = ref("");
  const password = ref("");
  const passwordcheck = ref("");
  const nickname = ref("");
  
  const cancelRegist = () => {
    router.push("/");
  }
  
  const regist = () => {
    if (password.value !== passwordcheck.value) {
      alert("입력한 비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
      return;
    }
    if (
      userid.value === "" ||
      password.value === "" ||
      nickname.value === ""
    ) {
      alert("모든 내용을 입력해주세요");
      return;
    }
  
    const user = {
      userid: userid.value,
      password: password.value,
      nickname: nickname.value,
    };
  
    emit("create-user", user);
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