import { AiOutlineDelete } from "react-icons/ai";

function Deletebtn({ onClick }) {
  return (
    <button
      className="
      p-1 
      rounded-md
      text-gray-500 
      hover:text-red-600 
      hover:bg-red-50 
      transition-all 
      duration-200 
      focus:outline-none 
      focus:ring-red-500 
      focus:ring-opacity-50"
      aria-label="Delete item"
      onClick={onClick}
    >
      <AiOutlineDelete className="h-5 w-5" />
    </button>
  );
}

export default Deletebtn;
