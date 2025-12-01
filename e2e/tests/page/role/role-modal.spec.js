import test, { expect } from "@playwright/test";

test("Role create success", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("role-create-btn").click();

  await page.locator(".ant-modal-content").isVisible();

  await page.getByTestId("role-title-inp").fill("TESTER5");

  await page.getByTestId("role-toggle-active").click();
  await page.getByTestId("permission-toggle-0").click();

  await page.getByTestId("permission-toggle-2").click();
  await page.getByTestId("permission-toggle-4").click();

  await page.getByTestId("role-modal-create-btn").click();

  await expect(page.getByTestId("success-notification")).toContainText(
    "Role Created",
  );
});

test("Role create failed", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("role-create-btn").click();

  await page.locator(".ant-modal-content").isVisible();

  await page.getByTestId("role-title-inp").fill("TESTER5");

  await page.getByTestId("role-toggle-active").click();
  await page.getByTestId("permission-toggle-0").click();

  await page.getByTestId("permission-toggle-2").click();
  await page.getByTestId("permission-toggle-4").click();

  await page.getByTestId("role-modal-create-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Role must be unique",
  );
});

test("Network error", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

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

  await page.getByTestId("role-create-btn").click();

  await page.locator(".ant-modal-content").isVisible();

  await page.getByTestId("role-title-inp").fill("TESTER5");
  await page.getByTestId("role-toggle-active").click();

  await page.getByTestId("permission-toggle-0").click();
  await page.getByTestId("permission-toggle-2").click();
  await page.getByTestId("permission-toggle-4").click();

  await page.getByTestId("role-modal-create-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Network error",
  );
});

test("Role view", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.goto("http://localhost:5173/role");

  await page.getByTestId("icon-view-0").click();

  await expect(page.getByTestId("role-title-inp")).toBeDisabled();

  await expect(page.getByTestId("role-toggle-active")).toBeDisabled();

  await expect(page.getByTestId("permission-toggle-0")).toBeDisabled();
  await expect(page.getByTestId("permission-toggle-1")).toBeDisabled();
  await expect(page.getByTestId("permission-toggle-2")).toBeDisabled();
  await expect(page.getByTestId("permission-toggle-3")).toBeDisabled();

  await expect(page.getByTestId("role-modal-create-btn")).not.toBeVisible();
  await expect(page.getByTestId("role-cancel-btn")).not.toBeVisible();
});

test("Role update success", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("icon-edit-0").click();

  await page.locator(".ant-modal-content").isVisible();

  await expect(page.getByTestId("role-title-inp")).not.toHaveValue("");

  await page.getByTestId("role-title-inp").fill("TESTER10");

  await page.getByTestId("role-toggle-active").click();

  await page.getByTestId("permission-toggle-0").click();

  await page.getByTestId("permission-toggle-2").click();
  await page.getByTestId("permission-toggle-4").click();

  await page.getByTestId("role-modal-create-btn").click();

  await expect(page.getByTestId("success-notification")).toContainText(
    "Role Updated",
  );
});

test("Role update conflict", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("icon-edit-0").click();

  await page.locator(".ant-modal-content").isVisible();

  await expect(page.getByTestId("role-title-inp")).not.toHaveValue("");

  await page.getByTestId("role-title-inp").fill("TESTER5");

  await page.getByTestId("role-toggle-active").click();

  await page.getByTestId("permission-toggle-0").click();

  await page.getByTestId("permission-toggle-2").click();
  await page.getByTestId("permission-toggle-4").click();

  await page.getByTestId("role-modal-create-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Role must be unique",
  );
});
