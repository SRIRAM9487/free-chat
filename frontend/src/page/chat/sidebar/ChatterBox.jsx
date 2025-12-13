import { FiUsers } from "react-icons/fi";

function ChatterBox({ data, isActive, setActiveId }) {
  return (
    <button
      key={data.id}
      onClick={() => setActiveId(data.id)}
      className={` w-full flex items-center gap-3 px-4 py-3 transition-all text-left border-b border-gray-100 ${isActive ? "bg-blue-50 border-l-4 border-blue-500" : "hover:bg-gray-100"} `}
    >
      <div
        className={` w-10 h-10 rounded-full flex items-center justify-center text-sm font-semibold shadow-md shrink-0 ${data.type === "group" ? "bg-indigo-100 text-indigo-700" : "bg-blue-100 text-blue-700"} `}
      >
        {data.type === "group" ? <FiUsers size={18} /> : data.name[0]}
      </div>

      <div className="flex flex-col flex-1 min-w-0">
        <span
          className={`font-semibold text-sm truncate ${isActive ? "text-blue-800" : "text-gray-900"}`}
        >
          {data.name}
        </span>

        <span className="text-xs text-gray-500 truncate">{data.last}</span>
      </div>
    </button>
  );
}

export default ChatterBox;
