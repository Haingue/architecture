import { defineConfig, mergeConfig } from 'vitest/config'
import viteConfig from './vite.config'

export default mergeConfig(viteConfig, defineConfig({
    test: {
      include: ['src/**/*.{test,spec}.?(c|m)[jt]s?(x)'],
      exclude: [
        'src/tests/e2e/**',
      ],
      globals: true,
      environment: 'jsdom',
      css: true,
      browser: {
        provider: 'playwright',
        enabled: true,
        instances: [
          { browser: 'chromium' },
        ],
      },
    },
  })
)