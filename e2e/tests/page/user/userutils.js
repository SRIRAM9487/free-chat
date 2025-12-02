import { expect } from "@playwright/test";
import { user_selectors } from "./userConstant";

export const select_gender = async (page) => {
  await page.getByTestId(user_selectors.create_gender).click();
  const randomCount = Math.floor(Math.random() * 2) + 1;
  const gen = `.${user_selectors.create_gender}-option-${randomCount}`;
  await page.locator(gen).click();
};

export const select_account_non_locked = async (page) => {
  await page.getByTestId(user_selectors.select_account_non_locked).click();
  const randomCount = Math.floor(Math.random() * 2) + 1;
  await page
    .locator(
      `.${user_selectors.select_account_non_locked}-option-${randomCount}`,
    )
    .click();
};

export const select_account_non_exipred = async (page) => {
  await page.getByTestId(user_selectors.select_account_non_exipred).click();
  const randomCount = Math.floor(Math.random() * 2) + 1;
  await page
    .locator(
      `.${user_selectors.select_account_non_exipred}-option-${randomCount}`,
    )
    .click();
};
export const select_enabled = async (page) => {
  await page.getByTestId(user_selectors.select_enabled).click();
  const randomCount = Math.floor(Math.random() * 2) + 1;
  await page
    .locator(`.${user_selectors.select_enabled}-option-${randomCount}`)
    .click();
};

export const select_roles = async (page) => {
  await page.getByTestId(user_selectors.select_role).click();
  await page.getByTitle("SUDO").click();
  await page.getByTitle("MODERATOR").click();
  await page.getByTitle("CUSTOMER").click();
  await page.getByTestId(user_selectors.select_role).click();
};

export async function user_success_notification(page, message) {
  const success = page.getByTestId(user_selectors.success_notification);
  await success.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(user_selectors.success_notification),
  ).toContainText(message);
}
export async function user_error_notification(page, message) {
  const error = page.getByTestId(user_selectors.error_notification);
  await error.waitFor({ state: "visible", timeout: 10000 });
  await expect(
    page.getByTestId(user_selectors.error_notification),
  ).toContainText(message);
}

export async function select_edit_btn(page) {
  const edits = page.getByTestId(/icon-edit-/);
  const count = await edits.count();
  let randomIndex = Math.floor(Math.random() * count);
  await edits.nth(randomIndex).click();
}
export async function select_view_btn(page) {
  const edits = page.getByTestId(/icon-view-/);
  const count = await edits.count();
  let randomIndex = Math.floor(Math.random() * count);
  await edits.nth(randomIndex).click();
}
export async function select_delete_btn(page) {
  const edits = page.getByTestId(/icon-del-/);
  const count = await edits.count();
  let randomIndex = Math.floor(Math.random() * count);
  await edits.nth(randomIndex).click();
}
