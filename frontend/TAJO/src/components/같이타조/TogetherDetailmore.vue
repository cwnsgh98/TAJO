<template>
    <div>
        <TogetherParty :groupid="groupid" :selectedName="name" @close-toggle="$emit('closeToggle')"/>
    </div>
</template>

<script setup>
import TogetherParty from '@/components/같이타조/TogetherParty.vue'
const props = defineProps({
    groupid : Number,
    name : String,
})
</script>

<style scoped>
</style>