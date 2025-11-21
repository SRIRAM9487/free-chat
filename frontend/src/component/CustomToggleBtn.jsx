import "./CustomToggleBtn.css";

function CustomToggleBtn({
  checked = false,
  onChange = () => {},
  disabled = false,
  name = "",
  id = "",
}) {
  return (
    <div className="checkbox-wrapper-25">
      <input
        type="checkbox"
        checked={checked}
        onChange={(e) => onChange(e.target.checked)}
        disabled={disabled}
        name={name}
        id={id}
      />
    </div>
  );
}

export default CustomToggleBtn;
