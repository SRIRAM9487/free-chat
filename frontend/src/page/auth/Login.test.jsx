// Login.test.jsx
import { render, screen, fireEvent } from "@testing-library/react";
import Login from "./Login";
import { test, vi } from "vitest";
import { UserContext } from "../../context/UserContext";
import { NotificationContext } from "../../context/NotificationContext";

// Mock modules
vi.mock("../../script/postService", () => ({
  postService: vi.fn(),
}));

function renderLogin() {
  return render(
    <NotificationContext.Provider
      value={{
        showError: vi.fn(),
        showSuccess: vi.fn(),
      }}
    >
      <UserContext.Provider value={{ login: vi.fn() }}>
        <Login />
      </UserContext.Provider>
    </NotificationContext.Provider>,
  );
}

test("shows validation errors if fields are empty", async () => {
  renderLogin();

  const submit = screen.getByRole("button", { name: /submit/i });
  fireEvent.click(submit);

  expect(await screen.findByText(/user name is required/i)).toBeTruthy();
  expect(await screen.findByText(/password is required/i)).toBeTruthy();
});
