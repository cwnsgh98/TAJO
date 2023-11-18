<template>
    <div class="HelpMain">
        <div class="helpselect">
            <span>고장부위를 선택하세요</span>
            <div class="selectpart">
                <button>안장</button>
                <button>체인</button>
                <button>핸들</button>
                <button>바퀴</button>
                <button>브레이크</button>
            </div>
        </div>
        <div class="helpresult">
            <div class="result-head">
                <span>다음 영상을 보고 수리해볼까요 ?</span>
            </div>
            <div class="result-show">
                <!--v-for 로 영상들 받아서 돌려야함 일단은 기본세팅-->
                <div class="video-list">
                    
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>