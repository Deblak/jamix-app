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
      path: '/login',
      name: 'login',
      component: () => import('../views/account/Login.vue')
    },
    {
      path: '/signUp',
      name: 'signUp',
      component: () => import('../views/account/SignUp.vue')
    },
    {
      path: '/detail/:id',
      name: 'detail',
      component: () => import('../views/offer/OfferDetails.vue'),
      props: true
    },
    {
      path: '/portfolio',
      name: 'portfolio',
      component: () => import('../views/portfolio/Portfolio.vue')
    },
    {
      path: '/my-offer',
      name: 'myOffer',
      component: () => import('../views/offer/UserOffers.vue')
    },
    {
      path: '/offer-create',
      name: 'offerCreate',
      component: () => import('../views/offer/OfferCreate.vue')
    },
    {
      path: '/results',
      name: 'results',
      component: () => import('../views/SearchResults.vue')
      // props: (route) => ({ keyword: route.query.keyword })
    }
  ]
})

export default router
