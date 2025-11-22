import { EditOutlined } from "@ant-design/icons";
function EditBtn({ onClick }) {
  return (
    <button
      className="
      p-1
      rounded-md
      text-gray-500
      hover:text-teal-600
      hover:bg-teal-50
      transition-all 
      duration-200
      focus:outline-none
      focus:ring-teal-900 
      focus:ring-opacity-50"
      aria-label="Edit item"
      onClick={onClick}
    >
      <EditOutlined className="h-5 w-5" />
    </button>
  );
}

export default EditBtn;
