import React from "react";
import { Input } from "antd";
function InputField({
  dataTestId = "",
  label = "",
  placeholder = "Enter value",
  error = "",
  required = false,
  value,
  onChange,
  type = "text",
  disabled = false,
  size = "large",
  readOnly = false,
  className = "!rounded-none",
}) {
  return (
    <div
      data-testid={`${dataTestId}-div`}
      className="flex flex-col w-full space-y-1"
    >
      {label && (
        <label
          className="text-base font-semibold text-gray-800 "
          data-testid={`${dataTestId}-label`}
        >
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}

      <Input
        data-testid={dataTestId}
        type={type}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        status={error ? "error" : ""}
        disabled={disabled}
        size={size}
        readOnly={readOnly}
        className={className}
      />

      {error && (
        <span
          data-testid={`${dataTestId}-error`}
          className="text-xs text-red-500 mt-1"
        >
          {error}
        </span>
      )}
    </div>
  );
}

export default InputField;
