
# ReactJs

## JavaScript VS Type Script

## Components
### Components
### Functional components

## Hooks
### useState
To manage state of variable:

```TypeScript
import { useState } from "react";
...
const [isLoading, setIsLoading] = useState(false);
```

Return a couple of element, corresponding to [yourVariable, theWayToChangeValueAndUpdatingTheState].

That means when you will update the variable using `setIsLoading(true)`:

The value of your variable in your component will change and will also update the display if it is used in.

### useEffect
To handle function when variable is modified :

We previously saw `useState`, we can imagine adding this kind of script : 
```TypeScript
import { useEffect} from "react";
...
useEffect (() => {
  foo();
}, [isLoading]);
```
This will permit to execute the function `foo()` when variable `isLoading` change. 

However, it's also possible to not adding variable :
```TypeScript
import { useEffect} from "react";
...
useEffect (() => {
  foo();
}, []);
```

In this case, the function `foo()` will be execute when the component is mounted.

### useContext
To share state of a variable through different components.

## Setup project
### Create project
```shell
npm create vite@latest <project-name> -- --template react-ts
cd <project-name>
```

### Add testing tools
```shell
npm install -D vitest jsdom @testing-library/react @testing-library/jest-dom
```

<details>
<summary>Exemple de tests</summary>

```TypeScript
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
    expect(counter.textContent).toBeDefined()
    if(counter.textContent) {
      const initialValue = parseInt(counter.textContent.replace('count is ', ''))
      expect(initialValue).toBe(0)
      fireEvent.click(counter)
      let newValue = parseInt(counter.textContent.replace('count is ', ''))
      expect(newValue).toBe(1)
      newValue = parseInt(counter.textContent.replace('count is ', ''))
      fireEvent.click(counter)
      expect(newValue).toBeGreaterThan(initialValue)
    }
  })
})
```

```TypeScript
import { render, screen } from '@testing-library/react'
import { describe, expect, it } from 'vitest'
import CountCard from './CountCard'

describe('CountCard', () => {
  it('Test with value at 0', () => {
    render(<CountCard count={0}/>)
    const spanCounterCard = screen.getByLabelText('There is no value for the counter')
    expect(spanCounterCard).toBeDefined()
    try {
      screen.getByLabelText('Value of the counter')
    } catch (error) {
      expect(error).not.toBeNull()
    }
  })
  it('Test with value at 0', () => {
    render(<CountCard count={15}/>)
    const spanCounterCard = screen.getByLabelText('Value of the counter')
    expect(spanCounterCard).toBeDefined()
    try {
      screen.getByLabelText('There is no value for the counter')
    } catch (error) {
      expect(error).not.toBeNull()
    }
  })
})
```
</details>

### Configuration

- **package.js**
  ```js
    ...
    "scripts": {
      "dev": "vite --host",
      "build": "tsc && vite build",
      "lint": "eslint . --ext ts,tsx --report-unused-disable-directives --max-warnings 0",
      "preview": "vite preview",
      "test": "vitest --watch",
      "test:ui": "vite build && vitest --ui",
      "test:coverage": "vitest run --coverage"
    },
    ....
  ```
- **vite.config.ts**
  ```TypeScript
  import * as path from 'path'
  import { defineConfig } from 'vite'
  import react from '@vitejs/plugin-react'

  export default defineConfig({
    plugins: [react()],
    build: {
      outDir: './dist'
    },
    resolve: {
      alias: [{ find: '@', replacement: path.resolve(__dirname, 'src') }],
    },
    test: {
      globals: true,
      environment: 'jsdom',
      css: true,
    },
  })
  ```

- **tsconfig.json**
  ```json
    ...
    "compilerOptions": {
      ...
      /* Import */
      "paths": {
        "@/*": ["./src/*"]
      }
    }
    ...
  ```

### Test your project
```shell
npm run dev
npm run test
```

## File architecture
| File  | Description |
| ---   | ---         |
| /public         |   Dossier contenant toutes les resources statique de l’application |
| /src/assets     |   Dossier contenant touts les assets de l’application |
| /src/components |   Dossier contenant touts les composants générique de l’application |
| /src/helpers    |   Dossier contenant toutes les classes aidant aux développement |
| /src/hooks      |   Dossier contenant touts les hooks de l’application |
| /src/models     |   Dossier contenant touts les types d’objet |
| /src/pages      |   Dossier contenant toutes les pages de l’application |
| /src/sync       |   Dossier contenant touts les objets permettant de communiquer avec le serveur |
| /src/App.tsx    |   Fichier contenant le composant principale |
| /src/main.tsx   |   Fichier de point d’entré de l’application permettant de monter le |composant principale “App.tsx”
| /index.html     |   Fichier HTML servant à fabriquer le premier contenu HTML envoyé au client |
| /tsconfig.json  |   Fichier de configuration du compilateur TypeScript |
| /vite.config.ts |   Fichier de configuration de ViteJs |
| /package.json   |   Fichier de description de l’application |
| /README.md      |   |

<details>
<summary>Exemple de composants</summary>

- /src/components/SendEmail/SendEmail.tsx: fichier du composant
- /src/components/SendEmail/SendEmail.css: fichier contenant le style du composant
- /src/components/SendEmail/SendEmail.test.tsx: fichiers contenant les tests sur le composant
</details>

<details>
<summary>Exemple de page Home</summary>

- /src/pages/Home/Home.tsx: fichier de la page
- /src/pages/Home/Home.css: fichier contenant le style de la page
- /src/pages/Home/Home.test.tsx: fichiers contenant les tests sur la page
</details>

## Libraries
### Tailwind (style framework)
Use this library first for styling.

[Install Tailwind](https://tailwindcss.com/docs/installation)

#### Installation

```shell
npm install -D tailwindcss postcss autoprefixer
npx tailwindcss init -p
```

#### Setup tailwind.config.js

```js
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

Add this to your main css file (oftently named App.css)

```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

Add this line in your package.json in scripts (replace App.css by the name of your main css file if you change it)

```shell
npx tailwindcss -i ./src/App.css -o ./src/output.css --watch
```

When you run your app using 

```shell
npm run dev
```

Also run

```shell
npm run style
```
It will permit to update output.css file with hot-reload, like npm run dev command

Don’t forget to import your css file in all your component

```TypeScript
import '@/output.css'
```

### Redux
Usefull (not a hook!) link to clearly understand Redux : [React-Redux : Guide du débutant](https://dev.to/ericlecodeur/react-redux-guide-du-debutant-12ck) 

Redux permit state of all variable of your project, that means all states of all your components are centralized in one place. 

It permit to avoid to pass props between component, callback function to child component, and so on (basically avoiding props drilling effect).

#### Installation

```shell
npm install @reduxjs/toolkit react-redux
```
> \>= npm v18.18.0 required

#### Initialization
Create Redux store → src/app/store.js file : 

```TypeScript
import { configureStore } from '@reduxjs/toolkit'

export default configureStore({
  reducer: {},
})
```

Make Redux store available to other components → main.tsx (or index.js) : 

```TypeScript
...
import store from './app/store'
import { Provider } from 'react-redux'

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,
  document.getElementById('root')
)
```

Now the store is available for all your components, but you can’t interact with it yet.

The logic of Redux is to create different **slice**, this idea is that we will divide the state of our application in different parts. For example creating a slice about current user connected information.

Create a slice about what you want (here the current user connected information) : 

```TypeScript
import { createSlice } from '@reduxjs/toolkit';
import User from "@/models/user";
import Role from "@/models/role";

const initialState = {
    currentUser:  JSON.stringify(new User("Empty", "Empty", [], new Role("Empty", [])))
};
export const userSlice = createSlice({
    name: 'user',
    initialState,

    reducers: {
        updateCurrentUser: (state, action) => {
            state.currentUser = JSON.stringify(new User(action.payload.login, action.payload.costCenter, action.payload.additionalPages, action.payload.role))
        },
        resetCurrentUser: (state) => {
            state.currentUser = JSON.stringify(new User("Empty", "Empty", [], new Role("Empty", [])))
        },
    },
});

export const { updateCurrentUser, resetCurrentUser } = userSlice.actions;

export const selectUser = (state) => state.user.currentUser;

export default userSlice.reducer;
```

> **If you want to store objects, you have to stringify them.**

In this example you have two differents methods :
- updateCurrentUser
- resetCurrentUser 

This will be methods which will can be used by your components.
After creating a slice, you have to add it in the file `store.js` previously created : 

```TypeScript
import { configureStore } from '@reduxjs/toolkit'
import userReducer from '@/app/userSlice.js'

export default configureStore({
    reducer: {
        user: userReducer,
    },
})
```
Now the slice is ready to be used by your component. 

Here an example to execute a function, using `dispatch`:

```TypeScript
import { resetCurrentUser } from '@/app/userSlice'
import { useDispatch } from 'react-redux'
...
const dispatch = useDispatch();
dispatch(resetCurrentUser());
```

Here an example to access to the data : 

```TypeScript
import { useSelector } from 'react-redux'
import { selectUser } from '@/app/userSlice'
...
const user = JSON.parse(useSelector(selectUser))
```

Something important, like said before the state is directly managed thanks to the store. It means that you don’t have to use `useState`, when the value is modified in the store by a component thanks to `dispatch(foo())`, modifications will be directly sent to all components which are using this variable thanks to `const user = JSON.parse(useSelector(selectUser))`.

It’s also possible to manage sessionStorage with Redux, but some modifications are required.

### React-router
### i18n