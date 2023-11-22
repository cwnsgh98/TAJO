<template>
  <div class="day-info" v-if="showInfo" :style="infoWindowStyle">
    <!-- 각 날짜에 따른 정보를 여기에 추가하세요 -->
    <span>{{ day }}일</span>
    <span>총 {{ recordStore.thisDayDist }}km</span>
  </div>
</template>

<script>
import { ref, watch } from 'vue';
import { useTodayStore } from '@/stores/today';
import { useDistanceStore } from '@/stores/distance';
import { useRecordStore } from '@/stores/record';

export default {
  props: {
    day: Number,
    showInfo: Boolean,
    infoWindowStyle: Object, // 추가: 부모로부터 받은 스타일 객체
  },
  setup(props) {
    const store = useTodayStore();
    const distStore = useDistanceStore();
    const recordStore = useRecordStore();

    // 스타일 객체를 반응형으로 변경
    const infoWindowStyle = ref({});

    // 부모로부터 받은 스타일을 반영
    watch(() => props.infoWindowStyle, (newStyle) => {
      // Vue의 반응성을 유지하기 위해 객체를 복사하여 새로운 객체를 만듭니다.
      infoWindowStyle.value = { ...newStyle };
    });

    return { store, distStore, recordStore, infoWindowStyle };
  },
};
</script>


<style scoped>
.day-info {
  display: flex;
  flex-direction: column;
  position: absolute;
  background-color: #fff0f0;
  border: 3px solid #FC9797;
  padding: 10px;
  z-index: 999; /* 다른 요소 위에 표시되도록 설정합니다. */
}
</style>
