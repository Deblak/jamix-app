import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/connection',
      name: 'connection',
      component: () => import('../views/ConnectionView.vue')
    },
    {
      path: '/inscription',
      name: 'inscription',
      component: () => import('../views/InscriptionView.vue')
    },
    {
      path: '/detail',
      name: 'detail',
      component: () => import('../views/DetailView.vue')
    },
    {
      path: '/portfolio',
      name: 'portfolio',
      component: () => import('../views/PortfolioView.vue')
    },
    {
      path: '/my-ad',
      name: 'myAd',
      component: () => import('../views/MyAdView.vue')
    },
    {
      path: '/results',
      name: 'results',
      component: () => import('../views/ResultsView.vue')
    }
  ]
})

export default router
