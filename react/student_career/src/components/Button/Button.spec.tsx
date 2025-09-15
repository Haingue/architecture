import { fireEvent, render, screen } from '@testing-library/react'
import { describe, expect, it } from 'vitest'
import Button from './Button'

describe('Tests on Button', () => {
    it('Should have a HTML button', () => {
        render(<Button />)
        const btn = screen.getByLabelText('This is simple button')
        expect(btn).toBeDefined()
    })
    it('Should define the label', () => {
        const label = 'Hello world !'
        render(<Button>{label}</Button>)
        const btn = screen.getByLabelText('This is simple button')
        expect(btn.textContent).toBe(label)
    })
    it('Should invoke click function', () => {
        render(<Button></Button>)
        const btn = screen.getByLabelText('This is simple button')
        fireEvent(
            btn,
            new MouseEvent('click', {
                bubbles: true,
                cancelable: true,
            }),
        )
    })
})