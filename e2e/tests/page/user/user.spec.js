import test from "@playwright/test";

test("User rendered", async ({ page }) => {
  await page.goto("http://localhost:5173/user");
});
