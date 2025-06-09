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
      path: '/results',
      name: 'results',
      component: () => import('../views/offer/OfferSearchResults.vue')
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
      path: '/my-offers',
      name: 'myOffer',
      component: () => import('../views/offer/UserOffers.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/offer-create',
      name: 'offerCreate',
      component: () => import('../views/offer/OfferCreate.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/edit-offer/:id',
      name: 'updateOffer',
      component: () => import('../views/offer/OfferUpdate.vue'),
      meta: { requiresAuth: true }
    }
  ]
})

router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('jwt')
  const isAuthenticated = !!token
  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
