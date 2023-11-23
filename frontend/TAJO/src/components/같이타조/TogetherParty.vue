<template>
    <div class="partymain">
        <div class="mainmain">

            <div class="partyheader">
                <span>파티이름</span>
                <button @click="$emit('closeToggle')">닫기창</button>
            </div>
            <div class="파티원목록">
                <div class="파티원정보">
                    <div class="파티원사진">
                        
                    </div>
                    <div class="파티원정보">
                        <table>
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>이름</th>
                                    <th>등급</th>
                                    
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(user, index) in userList " :key="user.userid">
                                    <td>{{ index }}</td>
                                    <td>{{ user.nickname }}</td>
                                    <td>{{ user.grade }}</td>
                              
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useGroupStore} from '@/stores/group'
import axios from 'axios'
const groupStore = useGroupStore();
const userList = ref([])
onMounted(async ()=>{
    userList.value = groupStore.memberList;
    for(const user in userList.value) {
        const gradeResponse = await axios.get(`http://localhost:8080/api-user/grade`, {
        params: { userid: user.userid },
      });
        user.grade = gradeResponse.data;
    }
})
</script>

<style scoped>
.mainmain{
    padding: 30px;
}
</style>