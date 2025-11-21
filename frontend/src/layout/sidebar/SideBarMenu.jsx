import { Menu } from "antd";
import { bottomMenuItems, sudoMenuItems } from "./SideBarMenuItems.jsx";
import { useEffect, useState } from "react";
import { AiOutlineUser } from "react-icons/ai";

function SideBarMenus({ darkTheme, collapsed }) {
  const [menuItems, setmenuItems] = useState(null);

  useEffect(() => {
    setmenuItems(sudoMenuItems);
  }, []);

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        height: "100%",
      }}
    >
      <div
        style={{
          display: "flex",
          alignItems: "center",
          justifyContent: collapsed ? "center" : "flex-start",
          gap: collapsed ? "0" : "10px",
          padding: "14px 16px",
          backgroundColor: darkTheme ? "#1f1f1f" : "#f5f5f5",
          color: darkTheme ? "#fff" : "#000",
          fontWeight: 500,
          fontSize: "15px",
          borderRadius: "8px",
          margin: "12px",
          transition: "all 0.3s ease",
        }}
      >
        <AiOutlineUser size={20} />

        {!collapsed && (
          <div style={{ transition: "opacity 0.3s ease" }}>Echo</div>
        )}
      </div>

      <Menu
        theme={darkTheme ? "dark" : "light"}
        mode="inline"
        items={menuItems}
      />

      <div style={{ flexGrow: 1 }} />

      <Menu
        theme={darkTheme ? "dark" : "light"}
        mode="inline"
        items={bottomMenuItems}
      />
    </div>
  );
}

export default SideBarMenus;
