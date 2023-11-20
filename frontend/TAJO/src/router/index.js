import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

import MyTajoview from '../views/MyTajoview.vue'
import TogetherTajoview from '../views/TogetherTajoview.vue'
import Wheregoview from '../views/Wheregoview.vue'

import LoginPage from '@/components/login/LoginPage.vue'
import RegistPage from '@/components/regist/RegistPage.vue'

import WheregoZZIM from '@/views/WheregoZZIM.vue'

import TogetherDetail from '@/views/TogetherDetail.vue'
import Game from '@/views/Game.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  scrollBehavior(){
    return { top: 0 }
  },
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/regist',
      name: 'regist',
      component: RegistPage
    },
    {
      path: '/riding',
      name: 'riding',
      component: RegistPage
    },
    {
      path: '/Together',
      name: 'Together',
      component: TogetherTajoview
    },
    {
      path: '/Mytajo',
      name: 'Mytajo',
      component: MyTajoview
    },
    {
      path: '/Wherego',
      name: 'Wherego',
      component: Wheregoview
    },
    {
      path: '/Wheregozzim',
      name: 'Wheregozzim',
      component: WheregoZZIM
    },
    {
      path: '/Togetherdetail',
      name: 'Togetherdetail',
      component: TogetherDetail
    },
    {
        path: '/Game',
        name: 'Game',
        component: Game
      },
    
  ]
})

export default router
