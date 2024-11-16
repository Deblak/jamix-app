import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/Homepage.vue'

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
      component: () => import('../views/Connection.vue')
    },
    {
      path: '/inscription',
      name: 'inscription',
      component: () => import('../views/Inscription.vue')
    },
    {
      path: '/detail/:id',
      name: 'detail',
      component: () => import('../views/OfferDetail.vue'),
      props: true
    },
    {
      path: '/portfolio',
      name: 'portfolio',
      component: () => import('../views/Portfolio.vue')
    },
    {
      path: '/my-offer',
      name: 'myOffer',
      component: () => import('../views/UserOffers.vue')
    },
    {
      path: '/new-offer',
      name: 'newOffer',
      component: () => import('../views/NewOffer.vue')
    },
    {
      path: '/results',
      name: 'results',
      component: () => import('../views/SearchResults.vue')
    }
  ]
})

export default router
