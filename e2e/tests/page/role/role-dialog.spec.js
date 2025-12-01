import test, { expect } from "@playwright/test";

test("Role cancel", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("icon-delete-0").click();

  await page.getByTestId("role-dialog").isVisible();

  await page.getByTestId("role-dialog-cancel-btn").click();
});

test("Role delete", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.getByTestId("icon-delete-0").click();

  await page.getByTestId("role-dialog").isVisible();

  await page.getByTestId("role-dialog-confirm-btn").click();

  await expect(page.getByTestId("success-notification")).toContainText(
    "Role Deleted",
  );
});

test("Internal server error", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.route("**/auth/v1/role/**", (route) => {
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
  await page.getByTestId("icon-delete-0").click();

  await page.getByTestId("role-dialog").isVisible();

  await page.getByTestId("role-dialog-confirm-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText("Failed");
});
