import { defineCodeRunnersSetup } from '@slidev/types'
import { exec } from 'child_process'
import * as sanitizeHtml from 'sanitize-html';


export default defineCodeRunnersSetup(() => {
  return {
    async python(code, ctx) {
      // Somehow execute the code and return the result
      const { stdout, stderr } = await exec(code)
      return {
        text: stderr || stdout
      }
    },
    async java(code, ctx) {
      // Somehow execute the code and return the result
      const { stdout, stderr } = await exec(code)
      return {
        text: stderr || stdout
      }
    },
    async kotlin(code, ctx) {
      // Somehow execute the code and return the result
      const { stdout, stderr } = await exec(code)
      return {
        text: stderr || stdout
      }
    },
    async shell(code, ctx) {
      // Somehow execute the code and return the result
      const { stdout, stderr } = await exec(code)
      return {
        text: stderr || stdout
      }
    },
    html(code, ctx) {
      return {
        html: sanitizeHtml(code)
      }
    },
    // or other languages, key is the language id
  }
})