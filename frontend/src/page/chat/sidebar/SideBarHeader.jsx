import { Segmented } from "antd";
import InputField from "../../../component/InputField";

function SideBarHeader() {
  return (
    <div>
      <div className="px-4 py-4 bg-white border-b border-gray-200 flex items-center justify-between">
        <h1 className="text-xl font-semibold text-[#262626]">Chats</h1>
        <Segmented
          size="middle"
          options={[
            { label: "All", value: "all" },
            { label: "Unread", value: "unread" },
            { label: "Group", value: "group" },
          ]}
          onChange={(value) => console.log(value)}
        />
      </div>
      <InputField placeholder="Search contacts or messages..." />
    </div>
  );
}

export default SideBarHeader;
