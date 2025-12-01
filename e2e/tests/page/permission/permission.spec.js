import test, { expect } from "@playwright/test";

test("page renders", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await expect(page.getByTestId("permission-title-inp")).toBeVisible();
  await expect(page.getByTestId("permission-cancel-btn")).toBeVisible();
  await expect(page.getByTestId("permission-create-btn")).toBeVisible();
});

test("Permission cancel", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await page.getByTestId("permission-title-inp").fill("TEST_PERMISSION_CREATE");
  await page.getByTestId("permission-cancel-btn").click();
  await expect(page.getByTestId("permission-title-inp")).toHaveText("");
});

test("Permission missing title", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await page.getByTestId("permission-title-inp").fill("");
  await page.getByTestId("permission-create-btn").click();
  await expect(page.getByTestId("permission-title-inp")).toHaveText("");
  await expect(page.getByTestId("error-notification")).toContainText(
    "Title missing",
  );
});
test("Permission created successful", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await page.getByTestId("permission-title-inp").fill("TEST_PERMISSION_2");
  await page.getByTestId("permission-create-btn").click();

  // succes notification
  await expect(page.getByTestId("success-notification")).toBeVisible();
  await expect(page.getByTestId("permission-title-inp")).toHaveText("");
});

test("Permission created  failed", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await page.getByTestId("permission-title-inp").fill("TEST_PERMISSION_2");
  await page.getByTestId("permission-create-btn").click();

  // succes notification
  await expect(page.getByTestId("error-notification")).toContainText(
    "Permission already exists",
  );
});

test("Permission toggle status success", async ({ page }) => {
  await page.goto("http://localhost:5173/permission");
  await expect(page.getByTestId("toggle-btn-0")).toBeChecked();
  await page.getByTestId("toggle-btn-0").click();
  await expect(page.getByTestId("toggle-btn-0")).not.toBeChecked();
  await expect(page.getByTestId("success-notification")).toBeVisible();
});

test("Permission toggle status failed", async ({ page }) => {
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

  await page.goto("http://localhost:5173/permission");

  await expect(page.getByTestId("toggle-btn-0")).not.toBeChecked();
  await page.getByTestId("toggle-btn-0").click();

  await expect(page.getByTestId("error-notification")).toBeVisible();
});

test("Permission toggle status failed internal server error", async ({
  page,
}) => {
  await page.goto("http://localhost:5173/permission");

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

  await expect(page.getByTestId("toggle-btn-0")).not.toBeChecked();
  await page.getByTestId("toggle-btn-0").click();

  await expect(page.getByTestId("error-notification")).toContainText(
    "Toggle failed",
  );
});
