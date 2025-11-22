import { EyeOutlined } from "@ant-design/icons";
function Viewbtn({ onClick }) {
  return (
    <button
      className="
      p-1
      rounded-md 
      text-gray-500 
      hover:text-blue-600
      hover:bg-blue-50
      transition-all 
      duration-200 
      focus:outline-none
      focus:ring-blue-500
      focus:ring-opacity-50"
      aria-label="View item"
      onClick={onClick}
    >
      <EyeOutlined className="h-5 w-5" />
    </button>
  );
}

export default Viewbtn;
