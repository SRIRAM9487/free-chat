import React from "react";
import { Input } from "antd";

function InputField({
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
    <div className="flex flex-col w-full space-y-1">
      {label && (
        <label
          data-testid="hell"
          className="text-base font-semibold text-gray-800 "
        >
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}

      <Input
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

      {error && <span className="text-xs text-red-500 mt-1">{error}</span>}
    </div>
  );
}

export default InputField;
