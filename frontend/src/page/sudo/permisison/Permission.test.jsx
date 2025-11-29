import {
  cleanup,
  fireEvent,
  render,
  screen,
  waitFor,
} from "@testing-library/react";
import { afterEach, describe, expect, it, vi } from "vitest";
import { postService } from "../../../script/postService";
import { getService } from "../../../script/getService";
import React from "react";
import { NotificationContext } from "../../../context/NotificationContext";
import Permission from "./Permission";
import "@testing-library/jest-dom/vitest";
import { patchService } from "../../../script/patchService";

afterEach(() => {
  vi.clearAllMocks();
  cleanup();
});

vi.mock("../../../script/postService", () => ({
  postService: vi.fn(),
}));

vi.mock("../../../script/getService.js", () => ({
  getService: vi.fn(),
}));

vi.mock("../../../script/patchService", () => ({
  patchService: vi.fn(),
}));

vi.mock("../../../component/CustomTable", () => ({
  default: () => <div>mock table</div>,
}));

const mockShowError = vi.fn();
const mockShowSuccess = vi.fn();

function Wrapper({ children }) {
  return (
    <NotificationContext.Provider
      value={{ showError: mockShowError, showSuccess: mockShowSuccess }}
    >
      {children}
    </NotificationContext.Provider>
  );
}

describe("Permission", () => {
  it("page renders", () => {
    render(<Permission />, { wrapper: Wrapper });
    expect(screen.getByTestId("permission-create-btn")).toBeInTheDocument();
    expect(screen.getByTestId("permission-cancel-btn")).toBeInTheDocument();
    expect(screen.getByTestId("permission-title-inp")).toBeInTheDocument();
  });
});

describe("Permission success", async () => {
  it("Create succes", async () => {
    render(<Permission />, { wrapper: Wrapper });

    getService.mockResolvedValue({
      data: {
        success: true,
        data: [
          {
            id: 1,
            title: "ROLE_CREATE",
            active: true,
          },
          {
            id: 2,
            title: "ROLE_READ",
            active: true,
          },
        ],
        timeStamp: "2025-11-29T21:08:02.046770302",
      },
    });

    fireEvent.change(
      screen.getByTestId("permission-title-inp").querySelector("input"),
      {
        target: { value: "ADMIN_ROLE" },
      },
    );

    fireEvent.click(screen.getByTestId("permission-create-btn"));

    await waitFor(() => {
      expect(postService).toHaveBeenCalledWith("auth/v1/permission/create", {
        title: "ADMIN_ROLE",
        active: true,
      });
    });

    fireEvent.change;
  });
});
