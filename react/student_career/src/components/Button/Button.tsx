import type { ReactNode } from "react"
import style from './Button.module.css'

type ButtonProps = {
    children?: ReactNode,
    onClick?: () => void
}

const Button = ({ children, onClick }: ButtonProps) => {
  return (
    <button
     aria-label="This is simple button"
     onClick={onClick}
     className={`${style.btn} text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 me-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800`}
     >{children}</button>
  )
}

export default Button