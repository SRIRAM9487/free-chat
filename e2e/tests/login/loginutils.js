import { expect } from "@playwright/test";
import { login_selectors } from "./loginconstant";

export async function login_success_notification(page, message) {
  const success = page.getByTestId(login_selectors.success_notification);
  await success.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(login_selectors.success_notification),
  ).toContainText(message);
}
export async function login_error_notification(page, message) {
  const error = page.getByTestId(login_selectors.error_notification);
  await error.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(login_selectors.error_notification),
  ).toContainText(message);
}
