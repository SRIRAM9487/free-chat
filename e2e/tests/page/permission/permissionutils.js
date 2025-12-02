import { expect } from "@playwright/test";
import { permission_selectors } from "./permissionconstant";

export async function permission_success_notification(page, message) {
  const success = page.getByTestId(permission_selectors.success_notification);
  await success.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(permission_selectors.success_notification),
  ).toContainText(message);
}
export async function permission_error_notification(page, message) {
  const error = page.getByTestId(permission_selectors.error_notification);
  await error.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(permission_selectors.error_notification),
  ).toContainText(message);
}

export async function toggle_btn(page) {
  const edits = page.getByTestId(/toggle-btn-/);
  const count = await edits.count();
  let randomIndex = Math.floor(Math.random() * count);
  await edits.nth(randomIndex).click();
}

export const mockPermissionUpdated404 = async (page) => {
  await page.route("**/auth/v1/permission/update/**", (route) => {
    route.fulfill({
      status: 404,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        success: false,
        method: "GET",
        status: null,
        timeStamp: "2025-12-01T09:56:16.280286751",
        path: null,
        message: "Permission not found",
        code: "PERMISSION_NOT_FOUND",
      }),
    });
  });
};
export const mockPermissionUpdated500 = async (page) => {
  await page.route("**/auth/v1/permission/update/**", (route) => {
    route.fulfill({
      status: 500,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        success: false,
        method: "PATCH",
        status: null,
        timeStamp: "2025-12-01T09:56:16.280286751",
        path: null,
        message: "Internal server error",
        code: "INTERNAL_SERVER_ERROR",
      }),
    });
  });
};
