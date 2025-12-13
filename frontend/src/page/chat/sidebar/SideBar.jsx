import React from "react";
import SideBarFloatingBtn from "./SideBarFloatingBtn";
import SideBarFooter from "./SideBarFooter.jsx";
import ChatterBox from "./ChatterBox.jsx";
import SideBarHeader from "./SideBarHeader.jsx";

export default function Sidebar({ contacts, activeId, setActiveId }) {
  return (
    <aside className="relative w-72 bg-gray-50 flex flex-col border-r border-gray-200">
      <SideBarHeader />

      <div className="flex-1 overflow-y-auto">
        {contacts.map((data) => {
          const isActive = activeId === data.id;
          return (
            <ChatterBox
              key={data.id}
              isActive={isActive}
              setActiveId={setActiveId}
              data={data}
            />
          );
        })}
      </div>

      <SideBarFooter />

      <SideBarFloatingBtn />
    </aside>
  );
}
