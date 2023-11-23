import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

import MyTajoview from '../views/MyTajoview.vue'
import TogetherTajoview from '../views/TogetherTajoview.vue'
import Wheregoview from '../views/Wheregoview.vue'

import LoginPage from '@/components/login/LoginPage.vue'
import RegistPage from '@/components/regist/RegistPage.vue'

import WheregoZZIM from '@/views/WheregoZZIM.vue'

import TogetherDetail from '@/components/같이타조/TogetherDetail.vue'
import Game from '@/views/Game.vue'

import WheretajoInfo from '@/components/어디가조/WheretajoInfo.vue'
import WhereInfodetail from '@/components/어디가조/WhereInfodetail.vue'
import WhereReview from '@/components/어디가조/WhereReview.vue'
import WheretajoHelp from '@/components/어디가조/WheretajoHelp.vue'


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
      component: TogetherTajoview,
      children:[
        {
          path: ':courseid/Detail',
          name: 'Togetherdetail',
          component: TogetherDetail,
        },
      ]
    },
    {
      path: '/Mytajo',
      name: 'Mytajo',
      component: MyTajoview
    },
    {
      path: '/Wherego',
      name: 'Wherego',
      component: Wheregoview,
      children:[
        {
          path: 'info',
          name: 'WheretajoInfo',
          component: WheretajoInfo
        },
        {
          path: ':courseid/Review',
          name: 'WhereReview',
          component: WhereReview
        },
        {
          path: ':courseid/Detail',
          name: 'WhereInfodetail',
          component: WhereInfodetail
        },
        {
          path: 'Help',
          name: 'WheretajoHelp',
          component: WheretajoHelp
        },
      ]
    },
    {
      path: '/Wheregozzim',
      name: 'Wheregozzim',
      component: WheregoZZIM
    },
    
    {
        path: '/Game',
        name: 'Game',
        component: Game
      },
    
  ]
})

export default router
