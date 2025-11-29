import React from "react";
import "@testing-library/jest-dom/vitest";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import { describe, it, expect, afterEach } from "vitest";
import { vi } from "vitest";
import Login from "./Login";
import { UserContext } from "../../context/UserContext";
import { NotificationContext } from "../../context/NotificationContext";
import { postService } from "../../script/postService";
import { cleanup } from "@testing-library/react";

afterEach(() => {
  vi.clearAllMocks();
  cleanup();
});

const mockNavigate = vi.fn();
vi.mock("react-router-dom", async () => {
  const actual = await vi.importActual("react-router-dom");
  return {
    ...actual,
    useNavigate: () => mockNavigate,
  };
});

vi.mock("../../script/postService", () => ({
  postService: vi.fn(),
}));

const mockLogin = vi.fn();
const mockShowError = vi.fn();
const mockShowSuccess = vi.fn();

function Wrapper({ children }) {
  return (
    <NotificationContext.Provider
      value={{ showError: mockShowError, showSuccess: mockShowSuccess }}
    >
      <UserContext.Provider value={{ login: mockLogin }}>
        {children}
      </UserContext.Provider>
    </NotificationContext.Provider>
  );
}

describe("Login Page Tests", () => {
  it("renders correctly", () => {
    render(<Login />, { wrapper: Wrapper });

    expect(
      screen.getByPlaceholderText("Enter your username"),
    ).toBeInTheDocument();
    expect(
      screen.getByPlaceholderText("Enter your password"),
    ).toBeInTheDocument();
  });

  it("shows validation errors when fields are empty", async () => {
    render(<Login />, { wrapper: Wrapper });

    fireEvent.click(screen.getByTestId("login-submit-btn"));

    expect(
      await screen.findByText("user name is required"),
    ).toBeInTheDocument();
    expect(await screen.findByText("password is required")).toBeInTheDocument();
  });

  it("successful login flow", async () => {
    postService.mockResolvedValue({
      data: {
        success: true,
        data: {
          userId: "1",
          userName: "admin",
          role: ["PERMISSION_CREATE", "PERMISSION_UPDATE"],
          token:
            "j261x_Nrov16DpQzNpDWMK8xDKDF1qFjdisigsYAuqM2dCvtt_ZIg2bYRXozWpPz8IrTwUtSBXmcKMLwoMt4XQ",
        },
        timeStamp: "2025-11-29T21:07:48.585167364",
      },
    });

    render(<Login />, { wrapper: Wrapper });

    fireEvent.change(
      screen.getByTestId("login-username-inp").querySelector("input"),
      {
        target: { value: "echo" },
      },
    );

    fireEvent.change(
      screen.getByTestId("login-password-inp").querySelector("input"),
      {
        target: { value: "1234" },
      },
    );

    fireEvent.click(screen.getByText("Submit"));

    await waitFor(() => {
      expect(postService).toHaveBeenCalledWith("auth/v1/user/login", {
        userId: "echo",
        password: "1234",
      });
    });

    expect(mockLogin).toHaveBeenCalledWith({
      success: true,
      data: {
        userId: "1",
        userName: "admin",
        role: ["PERMISSION_CREATE", "PERMISSION_UPDATE"],
        token:
          "j261x_Nrov16DpQzNpDWMK8xDKDF1qFjdisigsYAuqM2dCvtt_ZIg2bYRXozWpPz8IrTwUtSBXmcKMLwoMt4XQ",
      },
      timeStamp: "2025-11-29T21:07:48.585167364",
    });

    expect(mockNavigate).toHaveBeenCalledWith("/dashboard");
    expect(mockShowSuccess).toHaveBeenCalledWith("Login successfull", 1000);
  });

  it("login failed", async () => {
    postService.mockRejectedValue(new Error("Something went wrong"));

    render(<Login />, { wrapper: Wrapper });

    fireEvent.change(
      screen.getByTestId("login-username-inp").querySelector("input"),
      {
        target: { value: "admin" },
      },
    );

    fireEvent.change(
      screen.getByTestId("login-password-inp").querySelector("input"),
      {
        target: { value: "admin123" },
      },
    );

    fireEvent.click(screen.getByTestId("login-submit-btn"));

    await waitFor(() => {
      expect(postService).toHaveBeenCalledWith("auth/v1/user/login", {
        userId: "admin",
        password: "admin123",
      });
    });

    expect(mockLogin).not.toHaveBeenCalled();

    expect(mockNavigate).not.toHaveBeenCalled();

    expect(mockShowError).toHaveBeenCalledWith("Something went wrong");

    console.log("showError calls →", mockShowError.mock.calls);
  });
});
