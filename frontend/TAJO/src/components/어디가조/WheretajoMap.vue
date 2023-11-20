<template>
    <div class="TajoMap">
        <div class="map">
            지도가 들어갈 자리입니다.
            <MapBank/>
        </div>
        <button @click="toggleResult" class="help-button">
            <img class="도와줘" src="@/assets/도와줘타조.png">
        </button>
        <div class="편의시설선택">
            <button class="편의시설버튼" @click="toggleSelect">편의시설 선택하기</button>

            <label for="fruitSelect" v-show="isSelectVisible">편의시설 선택:</label>
            <select id="fruitSelect" v-show="isSelectVisible" v-model="selectedFruit">
                <option v-for="fruit in fruits" :key="fruit.id" :value="fruit.id">{{ fruit.name }}</option>
            </select>

            <p>선택한 편의시설: {{ selectedFruitName }}</p>
        </div>
    </div>
</template>

<script setup>
import { ref,computed, inject } from 'vue';
import MapBank from './MapBank.vue'

const fruits = [
  { id: 1, name: '화장실' },
  { id: 2, name: '약국' },
  { id: 3, name: '편의점' },
  // 다른 과일들...
];

const selectedFruit = ref(null);
const isSelectVisible = ref(false);

const selectedFruitName = computed(() => {
  const selected = fruits.find(fruit => fruit.id === selectedFruit.value);
  return selected ? selected.name : '';
});

const toggleSelect = () => {
  isSelectVisible.value = !isSelectVisible.value;
};




const showResult = inject('showResult');

const toggleResult = () => {
    showResult.value = !showResult.value;
};

</script>

<style scoped>
#fruitSelect{
    max-width: 120px;
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
    border: none;
    background: none;
    padding: 0;
    cursor: pointer;
    transition: transform 0.3s ease-in-out;
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