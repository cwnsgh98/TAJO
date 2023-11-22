<template>
    <div>
        <div class="리뷰별점">
            리뷰별점
        </div>
        <div class="리뷰내용">
            리뷰내용
        </div>
        <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
            <button>작성</button>
        </RouterLink>
        <RouterLink :to="`/Wherego/${Route.params.courseid}/Detail`">
            뒤로가기
        </RouterLink>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
import { useRoute } from 'vue-router';

const Route = useRoute();
</script>

<style scoped>
div{
    padding-top: 12px;
    padding-bottom: 40px;
}
</style>