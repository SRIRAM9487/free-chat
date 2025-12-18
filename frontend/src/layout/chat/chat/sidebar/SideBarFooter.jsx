import { Button } from "antd";
import { FiSettings } from "react-icons/fi";
import { MdOutlinePersonOutline } from "react-icons/md";
function SideBarFooter() {
  return (
    <div className="p-4 border-t border-gray-200 bg-white">
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-3 cursor-pointer hover:opacity-75 transition-opacity">
          <div className="w-8 h-8 rounded-full bg-gray-200 flex items-center justify-center">
            <MdOutlinePersonOutline size={18} className="text-gray-600" />
          </div>
          <span className="text-sm font-medium text-gray-700">Jane Doe</span>
        </div>

        <Button
          type="text"
          icon={<FiSettings size={18} />}
          className="text-gray-500 hover:text-blue-500"
        />
      </div>
    </div>
  );
}

export default SideBarFooter;
