import { defineRoutesSetup } from '@slidev/types'

export default defineRoutesSetup((routes) => {
  return [
    ...routes,
    {
      path: '/project_design',
      component: () => import('../pages/project_design/index.md'),
    },
    {
      path: '/java',
      component: () => import('../pages/java/index.md'),
    },
  ]
})