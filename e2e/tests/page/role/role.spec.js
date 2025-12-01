import test, { expect } from "@playwright/test";

test("page rendes", async ({ page }) => {
  await page.goto("http://localhost:5173/role");
});

test("Role status toggled", async ({ page }) => {
  await page.goto("http://localhost:5173/role");
  await page.getByTestId("toggle-btn-0").click();
  await expect(page.getByTestId("success-notification")).toContainText(
    "Successfully",
  );
});

test("Role toggle failed", async ({ page }) => {
  await page.goto("http://localhost:5173/role");

  await page.route("**/auth/v1/role/toggle/**", (route) => {
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

  await page.getByTestId("toggle-btn-0").click();
  await expect(page.getByTestId("error-notification")).toContainText(
    "Toggle failed",
  );
});
