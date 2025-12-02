import test, { expect } from "@playwright/test";

test("User create successfull", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("user-btn-create").click();
  await page.getByText("Create UserNamePasswordGender").isVisible();

  await page.getByTestId("create-name-inp").fill("Tester2");
  await page.getByTestId("create-username-inp").fill("Tester2");
  await page.getByTestId("create-password-inp").fill("Tester2djfkl");
  await page.getByTestId("create-email-inp").fill("tester2@gmail.com");
  await page.getByTestId("create-gender-inp").click();
  await page.getByText("FEMALE").nth(1).click();

  await page.getByTestId("create-roles-inp").click();
  await page.getByTitle("TESTER").nth(1).click();

  await page.getByTestId("create-accountnonexpired-inp").click();
  await page.getByText("Expired", { exact: true }).click();

  await page.getByTestId("create-accountnonlocked-inp").click();
  await page.getByTitle("Expired").nth(2).click();

  await page.getByTestId("create-enabled-inp").click();
  await page.getByTitle("Expired").nth(4).click();

  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("success-notification")).toHaveText(
    "User Created",
  );
});

test("User invalid password", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("user-btn-create").click();
  await page.getByText("Create UserNamePasswordGender").isVisible();

  await page.getByTestId("create-name-inp").fill("Tester1");
  await page.getByTestId("create-username-inp").fill("Tester1");
  await page.getByTestId("create-email-inp").fill("tester@gmail.com");
  await page.getByTestId("create-gender-inp").click();
  await page.getByText("FEMALE").nth(1).click();

  await page.getByTestId("create-roles-inp").click();
  await page.getByTitle("TESTER").nth(1).click();

  await page.getByTestId("create-accountnonexpired-inp").click();
  await page.getByText("Expired", { exact: true }).click();

  await page.getByTestId("create-accountnonlocked-inp").click();
  await page.getByTitle("Expired").nth(2).click();

  await page.getByTestId("create-enabled-inp").click();
  await page.getByTitle("Expired").nth(4).click();

  await page.getByTestId("create-password-inp").fill("Tester");
  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toContainText(
    "Password must be at least 8 characters",
  );
});

test("User missing values", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("user-btn-create").click();
  await page.getByText("Create UserNamePasswordGender").isVisible();

  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toContainText(
    "Name is required",
  );
  await page.getByTestId("create-name-inp").fill("Tester1");
  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Username is required",
  );
  await page.getByTestId("create-username-inp").fill("Tester1");
  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Email is required",
  );
  await page.getByTestId("create-email-inp").fill("tester@gmail.com");
  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Password is required",
  );
  await page.getByTestId("create-submit-btn").click();
  await page.getByTestId("create-password-inp").fill("Tester");
  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Password must be at least 8 characters",
  );
  await page.getByTestId("create-submit-btn").click();
  await page.getByTestId("create-password-inp").fill("Tester123asf4");

  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toContainText(
    "Gender is required",
  );
  await page.getByTestId("create-gender-inp").click();
  await page.getByText("FEMALE").nth(1).click();

  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toContainText(
    "At least one role must be selected",
  );

  await page.getByTestId("create-roles-inp").click();
  await page.getByTitle("TESTER").nth(1).click();
  await page.getByTestId("create-submit-btn").click();
});

test("User update successfull", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("icon-edit-0").click();
  await page.getByText("Create UserNamePasswordGender").isVisible();

  await expect(page.getByTestId("create-name-inp")).not.toHaveValue("");
  await page.getByTestId("create-name-inp").fill("Tester8");

  await expect(page.getByTestId("create-username-inp")).not.toHaveValue("");
  await page.getByTestId("create-username-inp").fill("Tester8");

  await expect(page.getByTestId("create-password-inp")).toHaveValue("");
  await page.getByTestId("create-password-inp").fill("Tester8djfkl");

  await expect(page.getByTestId("create-email-inp")).not.toHaveValue("");
  await page.getByTestId("create-email-inp").fill("tester8@gmail.com");

  await page.getByTestId("create-gender-inp").click();
  await page.getByText("FEMALE").nth(1).click();

  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("success-notification")).toHaveText(
    "User Updated",
  );
});

test("User update conflict", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("icon-edit-0").click();
  await page.getByText("Create UserNamePasswordGender").isVisible();

  await expect(page.getByTestId("create-name-inp")).not.toHaveValue("");
  await page.getByTestId("create-name-inp").fill("Tester8");

  await expect(page.getByTestId("create-username-inp")).not.toHaveValue("");
  await page.getByTestId("create-username-inp").fill("Tester8");

  await expect(page.getByTestId("create-password-inp")).toHaveValue("");
  await page.getByTestId("create-password-inp").fill("Tester8djfkl");

  await expect(page.getByTestId("create-email-inp")).not.toHaveValue("");
  await page.getByTestId("create-email-inp").fill("tester8@gmail.com");

  await page.getByTestId("create-gender-inp").click();
  await page.getByText("FEMALE").nth(1).click();

  await page.getByTestId("create-submit-btn").click();

  await expect(page.getByTestId("error-notification")).toHaveText(
    "User name already exists",
  );

  await page.getByTestId("create-username-inp").fill("Tester01");
  await page.getByTestId("create-submit-btn").click();
  await expect(page.getByTestId("error-notification")).toHaveText(
    "User Email already exists",
  );

  await page.getByTestId("create-submit-btn").click();
});

test("User view", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("icon-edit-0").click();

  await page.getByText("Create UserNamePasswordGender").isVisible();

  await expect(page.getByTestId("create-name-inp")).not.toHaveValue("");
  await expect(page.getByTestId("create-username-inp")).not.toHaveValue("");
  await expect(page.getByTestId("create-password-inp")).toHaveValue("");
  await expect(page.getByTestId("create-email-inp")).not.toHaveValue("");
  await expect(page.getByTestId("create-gender-inp")).not.toHaveText("");
  await expect(page.getByTestId("create-roles-inp")).not.toHaveText("");
  await expect(page.getByTestId("create-accountnonexpired-inp")).not.toHaveText(
    "",
  );
  await expect(page.getByTestId("create-accountnonlocked-inp")).not.toHaveText(
    "",
  );
  await expect(page.getByTestId("create-enabled-inp")).not.toHaveText("");
});
