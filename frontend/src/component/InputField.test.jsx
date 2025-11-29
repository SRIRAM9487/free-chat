import React from "react";
import { render, screen } from "@testing-library/react";
import { describe, expect, it } from "vitest";
import InputField from "./InputField";
import "@testing-library/jest-dom/vitest";

describe("Input field testing", () => {
  it("default", () => {
    render(<InputField />);
    expect(screen.getByPlaceholderText("Enter value")).toBeInTheDocument();
  });

  it("place holder", () => {
    const nameField = "Enter your name";
    render(<InputField placeholder={nameField} />);
    expect(screen.getByPlaceholderText(nameField)).toBeInTheDocument();
  });
});
