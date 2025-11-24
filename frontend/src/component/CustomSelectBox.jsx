import { Select } from "antd";
import "./CustomSelectBox.css";

function CustomSelectBox({
  label,
  value,
  placeholder,
  handleChange,
  options,
  size = "large",
  errors,
  readOnly = false,
  className = "",
  mode = undefined,
}) {
  return (
    <div className="flex flex-col w-full space-y-1">
      {label && (
        <label className="text-base font-semibold text-gray-800">
          {label} <span className="text-red-500">*</span>
        </label>
      )}

      <Select
        mode={mode}
        showSearch
        value={value}
        onChange={handleChange}
        placeholder={placeholder}
        options={options}
        size={size}
        disabled={readOnly}
        className={`sharp-select ${className}`}
        filterSort={(a, b) =>
          (a?.label ?? "")
            .toLowerCase()
            .localeCompare((b?.label ?? "").toLowerCase())
        }
      />

      {errors && <span className="text-xs text-red-500 mt-1">{errors}</span>}
    </div>
  );
}

export default CustomSelectBox;
