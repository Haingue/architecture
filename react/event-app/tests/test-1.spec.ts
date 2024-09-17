import { test, expect } from '@playwright/test';

test('test', async ({ page }) => {
  await page.goto('http://localhost:5173/');
  await expect(page.getByRole('main')).toContainText('Welcome to your best Event app !');
  await page.getByRole('link', { name: 'Events' }).click();
  await expect(page.locator('h1')).toContainText('Event list');
  await page.getByRole('link', { name: 'IMT - Cours architectur 0' }).click();
  await expect(page.getByRole('heading')).toContainText('Update event');
});