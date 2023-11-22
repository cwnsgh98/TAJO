<template>
    <div class="TajoMap">
        <div class="map">
            <div class="지도배경">
                <div class="인덱스">
                <MapBank/>
                </div>
                <button @click="toggleResult" class="help-button">
                    <img class="도와줘" src="@/assets/도와줘타조.png">
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref,computed, inject} from 'vue';
import MapBank from './MapBank.vue'

const emit = defineEmits(['help']);

const toggleResult = () => {
    emit('help')
};

</script>

<style scoped>
.인덱스{
    z-index: -1;
}
.지도배경{
    position: relative;
}

.편의시설버튼{
    width: 120px;
}
.편의시설선택{
    display: flex;
    flex-direction: column;
    min-width: 210px;
}
.help-button {
    position: absolute;
    top :60px;
    right: 50px;
    border: none;
    background: none;
    padding: 0;
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
    z-index: 2;
}

.help-button:hover {
    transform: scale(1.1);
    /* 호버 시에 1.1배로 확대되도록 하는 효과 */
}

.도와줘 {
    width: 155px;
    height: 75px;
}
</style>