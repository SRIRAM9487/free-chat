import { expect } from "@playwright/test";
import { role_selectors } from "./roleconstant";

export async function toggle_buttons(page) {
  const toggles = page.getByTestId(/permission-toggle-/);
  const count = await toggles.count();
  let indexes = Array.from({ length: count }, (_, i) => i);
  indexes.sort(() => Math.random() - 0.5);
  const randomCount = Math.floor(Math.random() * count) + 1;
  for (let i = 0; i < randomCount; i++) {
    await toggles.nth(indexes[i]).click();
  }
}

export async function toggle_active_btn(page) {
  const toggles = page.getByTestId(/toggle-btn-/);
  const count = await toggles.count();
  let randomIndex = Math.floor(Math.random() * count);
  await toggles.nth(randomIndex).click();
}

export async function toggle_edit(page) {
  const edits = page.getByTestId(/icon-edit-/);
  const count = await edits.count();

  if (count === 0) {
    throw new Error("No edit icons found on the page");
  }

  let randomIndex = Math.floor(Math.random() * count);

  await edits.nth(randomIndex).click();

  const titleField = page.getByTestId(role_selectors.title_input);
  await titleField.waitFor();
  const value = await titleField.inputValue();

  if (value === "SUDO" && count > 1) {
    let newIndex = Math.floor(Math.random() * count);
    while (newIndex === randomIndex) {
      newIndex = Math.floor(Math.random() * count);
    }

    await page.keyboard.press("Escape");
    await edits.nth(newIndex).click();
  }
}

export async function toggle_delete(page) {
  const edits = page.getByTestId(/icon-del-/);
  const count = await edits.count();
  let randomIndex = Math.floor(Math.random() * count);
  await edits.nth(randomIndex).click();
}

export async function toggle_view(page) {
  const view = page.getByTestId(/icon-view-/);
  const count = await view.count();
  let randomIndex = Math.floor(Math.random() * count);
  await view.nth(randomIndex).click();
}

export async function role_success_notification(page, message) {
  const success = page.getByTestId(role_selectors.success_notification);
  await success.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(role_selectors.success_notification),
  ).toContainText(message);
}
export async function role_error_notification(page, message) {
  const error = page.getByTestId(role_selectors.error_notification);
  await error.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(role_selectors.error_notification),
  ).toContainText(message);
}

export const mockRoleCreate500 = async (page) => {
  await page.route("**/auth/v1/role/create", (route) => {
    route.fulfill({
      status: 500,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        success: false,
        method: "POST",
        status: null,
        timeStamp: "2025-12-01T09:56:16.280286751",
        path: null,
        code: "INTERNAL_SERVER_ERROR",
      }),
    });
  });
};

export const mockRoleToggle500 = async (page) => {
  await page.route("**/auth/v1/role/toggle/**", (route) => {
    route.fulfill({
      status: 500,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        success: false,
        method: "POST",
        status: null,
        timeStamp: "2025-12-01T09:56:16.280286751",
        path: null,
        code: "INTERNAL_SERVER_ERROR",
      }),
    });
  });
};
