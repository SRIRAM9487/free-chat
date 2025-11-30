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

test("login successfull ", async ({ page }) => {
  await page.goto("http://localhost:5173/login");

  await page.getByTestId("login-username-inp").fill("sudo");
  await page.getByTestId("login-password-inp").fill("tester1234");

  await expect(page.getByTestId("login-username-inp")).toHaveValue("admin");
  await expect(page.getByTestId("login-password-inp")).toHaveValue(
    "tester1234",
  );

  await page.getByTestId("login-submit-btn").click();
});
