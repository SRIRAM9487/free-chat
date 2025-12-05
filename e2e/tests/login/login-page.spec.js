import test, { expect } from "@playwright/test";
import {
  login_error_messages,
  login_selectors,
  login_url,
  password,
  userId,
} from "./loginconstant";
import { login_error_notification } from "./loginutils";

test.beforeEach(async ({ page }) => {
  await page.goto(login_url);
});

test("Missing fields", async ({ page }) => {
  await test.step("password missing", async () => {
    await page.getByTestId(login_selectors.username_inp).fill(userId);
    await page.getByTestId(login_selectors.submit_btn).click();
    await expect(
      page.getByTestId(login_selectors.password_inp_error),
    ).toBeVisible();
  });

  await test.step("Username missing", async () => {
    await page.getByTestId(login_selectors.username_inp).fill("");
    await page.getByTestId(login_selectors.password_inp).fill(password);
    await page.getByTestId(login_selectors.submit_btn).click();
    expect(page.getByTestId(login_selectors.username_inp)).toBeVisible();
  });

  await test.step("both missing", async () => {
    await page.getByTestId(login_selectors.username_inp).fill("");
    await page.getByTestId(login_selectors.password_inp).fill("");
    await page.getByTestId(login_selectors.submit_btn).click();
    await expect(page.getByTestId(login_selectors.username_inp)).toBeVisible();
    await expect(
      page.getByTestId(login_selectors.password_inp_error),
    ).toBeVisible();
  });
});

test.describe("Action", () => {
  test("successful", async ({ page }) => {
    await page.getByTestId(login_selectors.username_inp).fill(userId);
    await page.getByTestId(login_selectors.password_inp).fill(password);
    await page.getByTestId(login_selectors.submit_btn).click();
    await expect(page).toHaveURL(/\/dashboard$/);
  });
  test("user not found", async ({ page }) => {
    await page.getByTestId(login_selectors.username_inp).fill("sud");
    await page.getByTestId(login_selectors.password_inp).fill(password);
    await page.getByTestId(login_selectors.submit_btn).click();
    await login_error_notification(page, login_error_messages.user_not_found);
  });

  test("bad credential", async ({ page }) => {
    await page.getByTestId(login_selectors.username_inp).fill(userId);
    await page.getByTestId(login_selectors.password_inp).fill("tester12");
    await page.getByTestId(login_selectors.submit_btn).click();
    await login_error_notification(page, login_error_messages.bad_credential);
  });
});
