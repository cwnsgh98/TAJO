<template>
    <div class="partymain">
        <div class="mainmain">

            <div class="partyheader">
                <span>파티이름</span>
                <button>닫기창</button>
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
                                    <th>내용</th>
                                    <th>코스</th>
                                    <th>리더</th>
                                    <th>인원</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="item in items" :key="item.id">
                                    <td>{{ item.id }}</td>
                                    <td>{{ item.nicname }}</td>
                                    <td>{{ item.level }}</td>
                                    <td>{{ item.tajolevel }}</td>
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
const items = [
    { id: 1, nicname: '닉네임1', level: '중수', tajolevel:'에메랄드' },
    { id: 2, nicname: '닉네임2', level: '고수', tajolevel:'다이아' },
    { id: 3, nicname: '닉네임3', level: '초보', tajolevel:'마스터' },
    { id: 4, nicname: '닉네임4', level: '초보', tajolevel:'실버' },
];
</script>

<style scoped>
.mainmain{
    padding: 30px;
}
</style>