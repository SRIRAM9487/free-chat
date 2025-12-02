import test, { expect } from "@playwright/test";
import {
  role_error_messages,
  role_selectors,
  role_success_messages,
} from "./roleconstant";
import {
  role_error_notification,
  mockRoleCreate500,
  mockRoleToggle500,
  role_success_notification,
  toggle_active_btn,
  toggle_buttons,
  toggle_delete,
  toggle_edit,
  toggle_view,
} from "./roleutils";

test.beforeEach(async ({ page }) => {
  await page.goto("http://localhost:5173/role");
});

test.describe("create", () => {
  test("success", async ({ page }) => {
    await page.getByTestId(role_selectors.create_btn).click();
    await page.locator(role_selectors.modal).isVisible();
    const role_name = `TESTER_ROLE_${Date.now()}`;
    await page.getByTestId(role_selectors.title_input).fill(role_name);
    await page.getByTestId(role_selectors.active_toggle).click();
    await toggle_buttons(page);
    await page.getByTestId(role_selectors.save_btn).click();
    await role_success_notification(page, role_success_messages.create);
  });

  test("Unique title", async ({ page }) => {
    await page.getByTestId(role_selectors.create_btn).click();
    await page.locator(role_selectors.modal).isVisible();
    await page.getByTestId(role_selectors.title_input).fill("SUDO");
    await page.getByTestId(role_selectors.active_toggle).click();
    await toggle_buttons(page);
    await page.getByTestId(role_selectors.save_btn).click();
    await role_error_notification(page, role_error_messages.unique_title);
  });
});

test.describe("Update", () => {
  test("success", async ({ page }) => {
    await toggle_edit(page);
    await page.locator(role_selectors.modal).isVisible();
    const title = page.getByTestId(role_selectors.title_input);
    await expect(title).not.toHaveValue("");
    const role_name = `TESTER_ROLE_${Date.now()}`;
    await page.getByTestId(role_selectors.title_input).fill(role_name);
    await page.getByTestId(role_selectors.active_toggle).click();
    await toggle_buttons(page);
    await page.getByTestId(role_selectors.save_btn).click();
    await role_success_notification(page, role_success_messages.update);
  });

  test("Uniuqe title", async ({ page }) => {
    await toggle_edit(page);
    await page.locator(role_selectors.modal).isVisible();
    const title = page.getByTestId(role_selectors.title_input);
    await expect(title).not.toHaveValue("");
    await title.fill("SUDO");
    await page.getByTestId(role_selectors.active_toggle).click();
    await toggle_buttons(page);
    await page.getByTestId(role_selectors.save_btn).click();
    await role_error_notification(page, role_error_messages.unique_title);
  });
});

test.describe("view", () => {
  test("default", async ({ page }) => {
    await toggle_view(page);
    await expect(page.getByTestId(role_selectors.title_input)).toBeDisabled();
    await expect(page.getByTestId(role_selectors.active_toggle)).toBeDisabled();
    const toggles = page.getByTestId(/permission-toggle-/);
    for (let i = 0; i < toggles.count(); i++) {
      await expect(toggles[i]).toBeDisabled();
    }
    await expect(page.getByTestId(role_selectors.save_btn)).not.toBeVisible();
    await expect(page.getByTestId(role_selectors.cancel_btn)).not.toBeVisible();
  });
});

test.describe("Network", () => {
  test("Network error", async ({ page }) => {
    await mockRoleCreate500(page);
    await page.getByTestId(role_selectors.create_btn).click();
    await page.locator(role_selectors.modal).isVisible();
    await page.getByTestId(role_selectors.title_input).fill("TESTER5");
    await page.getByTestId(role_selectors.active_toggle).click();
    toggle_buttons(page);
    await page.getByTestId(role_selectors.save_btn).click();
    await role_error_notification(page, role_error_messages.network_error);
  });
});

test.describe("toggle", () => {
  test("Status toggled", async ({ page }) => {
    await toggle_active_btn(page);
    await role_success_notification(page, role_success_messages.toggle);
  });

  test("Status toggle failed", async ({ page }) => {
    await mockRoleToggle500(page);
    await toggle_active_btn(page);
    await role_error_notification(page, role_error_messages.toggle_failed);
  });
});

test.describe("delete", () => {
  test("cancel", async ({ page }) => {
    toggle_delete(page);
    await page.getByTestId(role_selectors.delete_modal).isVisible();
    await page.getByTestId(role_selectors.delete_btn_cancel).click();
  });
  test("success", async ({ page }) => {
    await toggle_delete(page);
    await page.getByTestId(role_selectors.delete_modal).isVisible();
    await page.getByTestId(role_selectors.delete_btn).click();
    await role_success_notification(page, role_success_messages.delete);
  });
});
