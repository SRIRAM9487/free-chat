import test, { expect } from "@playwright/test";
import {
  permission_error_messages,
  permission_selectors,
  permission_success_messages,
} from "./permissionconstant";
import {
  mockPermissionUpdated404,
  mockPermissionUpdated500,
  permission_error_notification,
  permission_success_notification,
  toggle_btn,
} from "./permissionutils";

test.beforeEach(async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
});

test.describe("create", () => {
  test("Successful", async ({ page }) => {
    for (let i = 0; i < 5; i++) {
      const permission_title = `TESTER_PERMISSION_${Date.now()}`;
      await page
        .getByTestId(permission_selectors.permission_title)
        .fill(permission_title);
      await page.getByTestId(permission_selectors.create_btn).click();
      await permission_success_notification(
        page,
        permission_success_messages.create,
      );
    }
  });

  test("failed", async ({ page }) => {
    await page
      .getByTestId(permission_selectors.permission_title)
      .fill("PERMISSION_CREATE");

    await page.getByTestId(permission_selectors.create_btn).click();
    await permission_error_notification(
      page,
      permission_error_messages.unique_title,
    );
  });
});

test.describe("cleanup", () => {
  test("clear title", async ({ page }) => {
    const title = page.getByTestId(permission_selectors.permission_title);
    await title.fill("TEST_PERMISSION_CREATE");
    await page.getByTestId(permission_selectors.cancel_btn).click();
    await expect(title).toHaveValue("");
  });
});

test.describe("missing values", () => {
  test("Title", async ({ page }) => {
    const title = page.getByTestId(permission_selectors.permission_title);
    await title.fill("");
    await page.getByTestId(permission_selectors.create_btn).click();
    await expect(title).toHaveText("");
    await permission_error_notification(
      page,
      permission_error_messages.title_missing,
    );
  });
});

test.describe("toggle", () => {
  test("Success", async ({ page }) => {
    await toggle_btn(page);
    await permission_success_notification(
      page,
      permission_success_messages.update,
    );
  });

  test("Failed", async ({ page }) => {
    await mockPermissionUpdated404(page);
    await toggle_btn(page);
    await permission_error_notification(
      page,
      permission_error_messages.toggle_failed,
    );
  });

  test("Internal server error", async ({ page }) => {
    await mockPermissionUpdated500(page);
    await toggle_btn(page);
    await permission_error_notification(
      page,
      permission_error_messages.toggle_failed,
    );
  });
});
