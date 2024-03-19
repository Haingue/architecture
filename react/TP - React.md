# React

## Prérequis
<!--Installer Nodejs-->

## 1. Créer un projet React avec Vite.js
```shell
npm create vite@latest vitejs-react -- --template react-ts
cd vitejs-react
npm run dev
```

## X. Ajouter un titre

## X. Ajouter un bouton

## X. Tester votre application
```shell
npm install -D vitest jsdom @testing-library/react @testing-library/jest-dom
```

Configurer Vite.js
```ts
import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  test: {
    globals: true,
    environment: 'jsdom',
    css: true,
  },
})
```

Ajouter les scripts:
```json
{
  ...
  "scrips": {
    ...
    "test": "vitest --watch",
    "test:ui": "vite build && vitest --ui",
    "test:coverage": "vitest run --coverage"
  }
  ...
}
```

Tester la première page:
<details>
  <summary>Example</summary>

```tsx
import { fireEvent, render, screen } from '@testing-library/react'
import { describe, expect, it, test } from 'vitest'
import App from './App'

test('Vitest setup test', () => {
  expect(true).toBeTruthy()
})

describe('App', () => {
  it('Welcome message', () => {
    render(<App />)
    expect(screen.getByLabelText('welcome message')).toBeDefined()
  })

  it('Click counter', () => {
    render(<App />)
    const counter = screen.getByLabelText('add counter button')
    expect(counter).toBeDefined()
    const initialValue = parseInt(counter.textContent.replace('count is ', ''))
    expect(initialValue).toBe(0)
    fireEvent.click(counter)
    let newValue = parseInt(counter.textContent.replace('count is ', ''))
    expect(newValue).toBe(1)
    newValue = parseInt(counter.textContent.replace('count is ', ''))
    fireEvent.click(counter)
    expect(newValue).toBeGreaterThan(initialValue)
  })
})
```
</details>

## X. Ajouter un composant Compteur

## X. Ajouter Tailwind

## X. Ajouter React-router

## X. Ajouter une page

## X. Communiquer avec une API