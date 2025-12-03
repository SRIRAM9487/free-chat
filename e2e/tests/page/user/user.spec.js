import test, { expect } from "@playwright/test";
import {
  user_error_messages,
  user_selectors,
  user_success_messages,
} from "./userConstant";
import {
  select_account_non_exipred,
  select_account_non_locked,
  select_action_btn,
  select_delete_btn,
  select_edit_btn,
  select_enabled,
  select_gender,
  select_roles,
  select_view_btn,
  user_error_notification,
  user_success_notification,
  user_toggle_delete,
} from "./userutils";

test.beforeEach(async ({ page }) => {
  await page.goto("http://localhost:5173/user");
});

test.describe("Create ", () => {
  test("Successfull", async ({ page }) => {
    await page.getByTestId(user_selectors.create_btn).click();
    await page.getByText(user_selectors.create_user_modal).isVisible();
    const user_name = `TESTER_ROLE_${Date.now()}`;
    await page.getByTestId(user_selectors.create_name_inp).fill(user_name);
    await page.getByTestId(user_selectors.create_username_inp).fill(user_name);
    await page
      .getByTestId(user_selectors.create_password_inp)
      .fill("Tester2djfkl");
    await page
      .getByTestId(user_selectors.create_email_inp)
      .fill(user_name + "@gmail.com");
    await select_gender(page);
    await select_roles(page);
    await select_account_non_locked(page);
    await select_account_non_exipred(page);
    await select_enabled(page);
    await page.getByTestId(user_selectors.create_user_btn).click();
    await user_success_notification(page, user_success_messages.create);
  });
});

test.describe("missing values", () => {
  test("missing", async ({ page }) => {
    await page.getByTestId(user_selectors.create_btn).click();
    await page.getByText("Create UserNamePasswordGender").isVisible();
    const user_name = `TESTER_ROLE_${Date.now()}`;
    await test.step("Name", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.name_required);
      await page.getByTestId(user_selectors.create_name_inp).fill(user_name);
    });
    await test.step("User Name", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(
        page,
        user_error_messages.user_name_required,
      );
      await page
        .getByTestId(user_selectors.create_username_inp)
        .fill(user_name);
    });
    await test.step("Email required", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.email_required);
      await page.getByTestId(user_selectors.create_email_inp).fill(user_name);
    });
    await test.step("Email Invalid", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.email_invalid);
      await page
        .getByTestId(user_selectors.create_email_inp)
        .fill(user_name + "@gmail.com");
    });
    await test.step("password is required", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(
        page,
        user_error_messages.password_required,
      );
      await page.getByTestId(user_selectors.create_password_inp).fill("Tester");
    });
    await test.step("password too short", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.password_short);
      await page
        .getByTestId(user_selectors.create_password_inp)
        .fill("Tester1234");
    });
    await test.step("Gender", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.gender_required);
      await select_gender(page);
    });
    await test.step("Roles", async () => {
      await page.getByTestId(user_selectors.create_user_btn).click();
      await user_error_notification(page, user_error_messages.roles_required);
      await select_roles(page);
    });
  });
});

test.describe("Update", () => {
  test("Successfull", async ({ page }) => {
    await select_edit_btn(page);
    await page.getByText(user_selectors.create_user_modal).isVisible();

    const user_name = `TESTER_ROLE_${Date.now()}`;

    await expect(
      page.getByTestId(user_selectors.create_name_inp),
    ).not.toHaveValue("");
    await page.getByTestId(user_selectors.create_name_inp).fill(user_name);

    await expect(
      page.getByTestId(user_selectors.create_username_inp),
    ).not.toHaveValue("");
    await page.getByTestId(user_selectors.create_username_inp).fill(user_name);

    await expect(
      page.getByTestId(user_selectors.create_password_inp),
    ).toHaveValue("");

    await expect(
      page.getByTestId(user_selectors.create_email_inp),
    ).not.toHaveValue("");

    await page
      .getByTestId(user_selectors.create_email_inp)
      .fill(user_name + "@gmail.com");

    await expect(page.getByTestId(user_selectors.create_gender)).not.toHaveText(
      "",
    );
    await select_gender(page);

    await expect(page.getByTestId(user_selectors.select_role)).not.toHaveText(
      "",
    );
    await select_roles(page);

    await expect(
      page.getByTestId(user_selectors.select_account_non_locked),
    ).not.toHaveText("");
    await select_account_non_locked(page);

    await expect(
      page.getByTestId(user_selectors.select_account_non_exipred),
    ).not.toHaveText("");
    await select_account_non_exipred(page);

    await expect(
      page.getByTestId(user_selectors.select_enabled),
    ).not.toHaveText("");
    await select_enabled(page);

    await page.getByTestId(user_selectors.create_user_btn).click();
    await user_success_notification(page, user_success_messages.update);
  });
});

test.describe("view", () => {
  test("User view", async ({ page }) => {
    await select_view_btn(page);
    await page.getByText(user_selectors.create_user_modal).isVisible();
    await expect(
      page.getByTestId(user_selectors.create_name_inp),
    ).not.toHaveValue("");
    await expect(
      page.getByTestId(user_selectors.create_username_inp),
    ).not.toHaveValue("");
    await expect(
      page.getByTestId(user_selectors.create_password_inp),
    ).toHaveValue("");
    await expect(
      page.getByTestId(user_selectors.create_email_inp),
    ).not.toHaveValue("");
    await expect(page.getByTestId(user_selectors.create_gender)).not.toHaveText(
      "",
    );
    await expect(page.getByTestId(user_selectors.select_role)).not.toHaveText(
      "",
    );
    await expect(
      page.getByTestId(user_selectors.select_account_non_exipred),
    ).not.toHaveText("");
    await expect(
      page.getByTestId(user_selectors.select_account_non_locked),
    ).not.toHaveText("");
    await expect(
      page.getByTestId(user_selectors.select_enabled),
    ).not.toHaveText("");
  });
});

test.describe("Delete", () => {
  test("Cancel", async ({ page }) => {
    await select_delete_btn(page);
    await page.getByTestId(user_selectors.delete_box).isVisible();
    await page.getByTestId(user_selectors.delete_box_cancel_btn).click();
  });

  test("Successfull", async ({ page }) => {
    await select_delete_btn(page);
    await page.getByTestId(user_selectors.delete_box).isVisible();
    await page.getByTestId(user_selectors.delete_box_submit_btn).click();
    await user_success_notification(page, user_success_messages.delete);
  });

  test("Internal server error", async ({ page }) => {
    await page.route("**/auth/v1/user/**", (route) => {
      route.fulfill({
        status: 500,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          success: false,
          method: "DELETE",
          status: null,
          timeStamp: "2025-12-01T09:56:16.280286751",
          path: null,
          message: "Internal server error",
          code: "INTERNAL_SERVER_ERROR",
        }),
      });
    });
    await select_delete_btn(page);

    await page.getByTestId(user_selectors.delete_box).isVisible();
    await page.getByTestId(user_selectors.delete_box_submit_btn).click();
    await user_error_notification(page, user_error_messages.failed);
  });
});

test.describe("Action", () => {
  test("Email reset", async ({ page }) => {
    await select_action_btn(page);
    page.getByTestId(user_selectors.user_action_box_email).value;
    await expect(
      page.getByTestId(user_selectors.user_action_box_email),
    ).toBeVisible();
    // await page.getByTestId(user_selectors.user_action_box_email).click();
    // await user_success_notification(
    //   page,
    //   user_success_messages.email_verification,
    // );
  });
});
