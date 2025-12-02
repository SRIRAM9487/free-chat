import test, { expect } from "@playwright/test";

test("User action renders", async ({ page }) => {
  await page.goto("http://localhost:5173/user");
  await expect(page.getByText("User ActionsSend Email")).not.toBeVisible();
  await page.getByTestId("icon-action-0").click();
  await expect(page.getByText("User ActionsSend Email")).toBeVisible();
});

test("Email reset", async ({ page }) => {
  await page.goto("http://localhost:5173/user");
  await expect(page.getByText("User ActionsSend Email")).not.toBeVisible();
  await page.getByTestId("icon-action-0").click();
  await expect(page.getByText("User ActionsSend Email")).toBeVisible();
  await page.getByTestId("email-verification-btn").click();
  await expect(page.getByTestId("success-notification")).toContainText(
    "Email Verification sent",
  );
});
