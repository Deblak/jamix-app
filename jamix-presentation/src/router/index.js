import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import { useAuth } from '@/stores/useAuthStore'

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
      component: () => import('../views/account/LoginView.vue')
    },
    {
      path: '/sign-up',
      name: 'signUp',
      component: () => import('../views/account/SignUpView.vue')
    },
    {
      path: '/offer-results',
      name: 'offerResults',
      component: () => import('../views/offer/OfferSearchResults.vue')
    },
    {
      path: '/portfolio-results',
      name: 'portfolioResults',
      component: () => import('../views/portfolio/PortfolioSearchResults.vue')
    },
    {
      path: '/detail/:id',
      name: 'detail',
      component: () => import('../views/offer/OfferDetails.vue'),
      props: true
    },
    {
      path: '/portfolios/:id',
      name: 'portfolio',
      component: () => import('../views/portfolio/Portfolio.vue'),
      props: true
    },
    {
      path: '/owned-portfolio',
      name: 'ownedPortfolio',
      component: () => import('../views/portfolio/MusicianPortfolio.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/portfolio-create',
      name: 'portfolioCreate',
      component: () => import('../views/portfolio/PortfolioCreate.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/edit-portfolio',
      name: 'updatePortfolio',
      component: () => import('../views/portfolio/PortfolioUpdate.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/owned-offers',
      name: 'ownedOffers',
      component: () => import('../views/offer/MusicianOffers.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/offer-create',
      name: 'offerCreate',
      component: () => import('../views/offer/OfferCreate.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/contact/:offerId',
      name: 'contactForm',
      component: () => import('../views/offer/ContactForm.vue'),
      props: true
    },
    {
      path: '/edit-offer/:id',
      name: 'updateOffer',
      component: () => import('../views/offer/OfferUpdate.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/:notFound(.*)',
      name: 'not-found',
      component: () => import('../views/errors/PageNotFoundView.vue')
    },
    {
      path: '/:forbidden',
      name: 'forbidden',
      component: () => import('../views/errors/PageForbiddenView.vue')
    },
    {
      path: '/error',
      name: 'unexpected-error',
      component: () => import('../views/errors/UnexpectedErrorView.vue')
    },
    {
      path: '/legal-notices',
      name: 'legalNotices',
      component: () => import('../views/legalities/LegalNotices.vue')
    },
    {
      path: '/terms-of-use',
      name: 'termsOfUse',
      component: () => import('../views/legalities/TermsOfUse.vue')
    },
    {
      path: '/privacy-policy',
      name: 'privacyPolicy',
      component: () => import('../views/legalities/PrivacyPolicy.vue')
    }
  ]
})

router.beforeEach((to, from, next) => {
  const authStore = useAuth()
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    next({ name: 'login' })
  } else {
    next()
  }
})

export default router
