import { test, expect } from '@playwright/test';

test('Test JobOffer', async ({ page }) => {
  await page.goto('http://localhost:5173/job-offer/form');
  await expect(page.getByRole('banner')).toMatchAriaSnapshot(`
    - link "Home":
      - /url: /
    - link "Offer0":
      - /url: /job-offer
    - text: "|"
    - link "Demand":
      - /url: /job-demand
    `);
  
    await page.getByRole('link', { name: 'Demand' }).click();
    await page.getByRole('link', { name: 'Offer0' }).click();
    await expect(page.getByRole('heading')).toContainText('JobOfferHome');
    await page.getByText('Publish new JobOffer').click();
    await page.locator('#title').click();
    await page.locator('#title').fill('Ceci est un test');
    await page.locator('#owner').click();
    await page.locator('#owner').fill('OK');

    await page.getByRole('button', { name: 'This is simple button' }).click();
    
});

test('New test JobOffer', async ({ page }) => {
  await page.goto('http://localhost:5173/');
  await expect(page.getByText('HomeOffer0|Demand')).toBeVisible();
  await page.getByRole('link', { name: 'Demand' }).click();
  await expect(page.getByRole('main')).toContainText('JobDemandHome');
  await page.getByRole('link', { name: 'Offer0' }).click();
  await expect(page.getByRole('heading')).toContainText('JobOfferHome');
  await page.getByText('Publish new JobOffer').click();
  await page.locator('#title').click();
  await page.locator('#title').fill('Test');
  await page.locator('#description').click();
  await page.locator('#description').fill('description');
  await page.locator('#owner').click();
  await page.locator('#owner').fill('OK');
  await page.getByRole('button', { name: 'This is simple button' }).click();
  await expect(page.getByRole('banner')).toContainText('1');
  await page.getByText('refresh').click();
  await expect(page.getByRole('cell', { name: 'Test', exact: true })).toBeVisible();
});