import test, { expect } from "@playwright/test";

test("User cancel", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("icon-del-0").click();

  await page.getByTestId("dialog-box").isVisible();

  await page.getByTestId("dialog-box-cancel-btn").click();
});

test("User delete", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

  await page.getByTestId("icon-del-0").click();

  await page.getByTestId("dialog-box").isVisible();

  await page.getByTestId("dialog-box-confirm-btn").click();

  await expect(page.getByTestId("success-notification")).toContainText(
    "User Deleted",
  );
});

test("Internal server error", async ({ page }) => {
  await page.goto("http://localhost:5173/user");

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
  await page.getByTestId("icon-del-0").click();

  await page.getByTestId("dialog-box").isVisible();

  await page.getByTestId("dialog-box-confirm-btn").click();

  await expect(page.getByTestId("error-notification")).toContainText("Failed");
});
