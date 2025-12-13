import React from "react";
import { FiUsers } from "react-icons/fi";

export default function ChatHeader({ contact }) {
  const isGroup = contact.type === "group";

  return (
    <header className=" bg-white p-3 flex items-center gap-3 shadow-sm">
      <div
        className={`
          w-9 h-9 rounded-full flex items-center justify-center shadow-sm font-medium text-sm
          ${isGroup ? "bg-indigo-100 text-indigo-700" : "bg-blue-100 text-blue-700"}
        `}
      >
        {isGroup ? <FiUsers size={14} /> : contact.name[0]}
      </div>

      <div>
        <h3 className="font-semibold text-gray-900 text-sm">{contact.name}</h3>

        {isGroup ? (
          <p className="text-[10px] text-gray-500">
            {contact.members.length} members
          </p>
        ) : (
          <p className="text-[10px] text-green-600 flex items-center gap-1">
            <span className="text-base leading-none">•</span> Online
          </p>
        )}
      </div>
    </header>
  );
}
