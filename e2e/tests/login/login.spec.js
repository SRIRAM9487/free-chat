import test, { expect } from "@playwright/test";

test("page renders", async ({ page }) => {
  await page.goto("http://localhost:5173/login");
  await expect(page.getByTestId("login-username-inp")).toBeVisible();
  await expect(page.getByTestId("login-password-inp")).toBeVisible();
  await expect(page.getByTestId("login-username-inp")).toBeEditable();
  await expect(page.getByTestId("login-password-inp")).toBeEditable();
  await expect(page.getByTestId("login-submit-btn")).toBeEnabled();
  await expect(page.getByTestId("login-cancel-btn")).toBeEnabled();
});

test("Missing fields", async ({ page }) => {
  await page.goto("http://localhost:5173/login");

  // Missing password
  await page.getByTestId("login-username-inp").fill("sudo");
  await page.getByTestId("login-submit-btn").click();
  await expect(page.getByTestId("login-password-inp-error")).toBeVisible();

  // Missing username
  await page.getByTestId("login-username-inp").fill("");
  await page.getByTestId("login-password-inp").fill("tester1234");
  await page.getByTestId("login-submit-btn").click();
  await expect(page.getByTestId("login-username-inp-error")).toBeVisible();

  // Both missing
  await page.getByTestId("login-username-inp").fill("");
  await page.getByTestId("login-password-inp").fill("");
  await page.getByTestId("login-submit-btn").click();
  await expect(page.getByTestId("login-username-inp-error")).toBeVisible();
  await expect(page.getByTestId("login-password-inp-error")).toBeVisible();
});

test("Login failed", async ({ page }) => {
  await page.goto("http://localhost:5173/login");
  await page.getByTestId("login-username-inp").fill("sud");
  await page.getByTestId("login-password-inp").fill("tester1234");
  await page.getByTestId("login-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toBeVisible();
});

test("login successful", async ({ page }) => {
  await page.goto("http://localhost:5173/login");
  await page.getByTestId("login-username-inp").fill("admin");
  await page.getByTestId("login-password-inp").fill("tester1234");
  await expect(page.getByTestId("login-username-inp")).toHaveValue("admin");
  await expect(page.getByTestId("login-password-inp")).toHaveValue(
    "tester1234",
  );
  await page.getByTestId("login-submit-btn").click();
  await expect(page.getByTestId("success-notification")).toBeVisible();
  await expect(page).toHaveURL(/\/dashboard$/);
});
