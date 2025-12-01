import { defineConfig } from "@playwright/test";

export default defineConfig({
  testDir: "./tests",
  fullyParallel: false,
  reporter: "html",
  workers: 1,

  use: {
    headless: false,
    viewport: { width: 1920, height: 1080 },
    video: "on",

    launchOptions: {
      slowMo: 100,
      args: ["--start-maximized"],
    },
  },

  projects: [
    {
      name: "chromium",
      use: {
        headless: false,
        viewport: { width: 1920, height: 1080 },
      },
    },
  ],
});
