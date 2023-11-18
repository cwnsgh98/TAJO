<template>
    <div>
        <div class="header">
            <div class="header-fifty">
                <div class="fifty-first">
                    <RouterLink class="router-link" :to="{ name: 'home' }">
                    <img class="tajo" src="@/assets/누끼딴로고.png"></RouterLink>
                </div>
                <div class="fifty-second">
                    <RouterLink class="router-link" :to="{ name: 'login' }">
                    <img class="login" src="@/assets/로그인찐.png">
                    </RouterLink>
                    <RouterLink class="router-link" :to="{ name: 'regist' }">
                        <img class="regist" src="@/assets/회원가입찐.png">
                    </RouterLink>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.router-link {
  transition: transform 0.3s ease-in-out;
}
.regist:hover {
  transform: scale(1.1);
}
.tajo:hover {
  transform: scale(1.05);
}
.login:hover {
  transform: scale(1.1);
}
a{
    text-decoration: none;
}
/* .tajo{
    width: 88px;
height: 42px;
flex-shrink: 0;
} */
.tajo{
    width: 140px;
height: 49px;
flex-shrink: 0;
}
.fifty-second{
    width: 30%;
}
.login{
    margin-right: 40px;
    width: 50px;
    height: 25px;
}
.regist{
    width: 66.6px;
    height: 25px;
}
.header-fifty{
    width: 53%;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.header{
width: 100%;
height: 82px;
flex-shrink: 0;
border-bottom: 2px solid #A4A4A4;
background: #FFFFF3;
display: flex;
justify-content: flex-end;
}
</style>