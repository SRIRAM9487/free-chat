import { HiOutlineSun, HiOutlineMoon } from "react-icons/hi";
import { Button, Layout, theme } from "antd";
import { useState, useEffect, useContext } from "react";
import { MenuFoldOutlined, MenuUnfoldOutlined } from "@ant-design/icons";
import { Outlet, useLocation, Navigate } from "react-router-dom";
import SideBarMenus from "../sidebar/SideBarMenu";
import { UserContext } from "../../context/UserContext";
const { Header, Content, Sider } = Layout;

function AuthenticatedLayout() {
  const location = useLocation();
  const [themeToggle, setthemeToggle] = useState(false);
  const [collapsed, setcollapsed] = useState(true);
  const [isMobile, setIsMobile] = useState(false);
  const { user, loading } = useContext(UserContext);

  const routeTitleMap = {
    "/dashboard": "Dashboard",
    "/dailyEntry": "Daily Entry",
    "/billing": "Billing",
    "/customer": "Customer",
    "/user": "User Management",
    "/state": "State",
    "/district": "District",
    "/taluk": "Taluk",
    "/soceity": "Soceity",
    "/union": "Union",
    "/permission": "Permission",
    "/role": "Role",
  };

  const title = routeTitleMap[location.pathname] || "";

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 768);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (!user) {
    return <Navigate to="/login" replace />;
  }
  return (
    <Layout style={{ minHeight: "100vh" }}>
      <Sider
        collapsible
        collapsed={isMobile ? false : collapsed}
        trigger={null}
        width={230}
        theme={themeToggle ? "dark" : "light"}
        style={{
          position: isMobile ? "fixed" : "relative",
          height: "100vh",
          zIndex: isMobile ? 1000 : "auto",
          left: isMobile && collapsed ? -230 : 0,
          transition: "all 0.3s",
        }}
        onCollapse={(value) => setcollapsed(value)}
      >
        <SideBarMenus darkTheme={themeToggle} collapsed={collapsed} />
      </Sider>

      <Layout style={{ height: "100vh" }}>
        <Header
          style={{
            padding: "0 16px",
            height: "60px",
            background: colorBgContainer,
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
          }}
        >
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setcollapsed(!collapsed)}
            style={{
              fontSize: "16px",
              width: 64,
              height: 60,
            }}
          />
          <div>{title}</div>
          <Button
            type="text"
            onClick={() => setthemeToggle(!themeToggle)}
            style={{
              width: 50,
              height: 50,
              display: "flex",
              alignItems: "center",
              justifyContent: "center",
              backgroundColor: !themeToggle ? "#f5f5f5" : "#001529",
              color: !themeToggle ? "#000" : "#fff",
              transition: "all 0.3s ease",
              boxShadow: "0 2px 5px rgba(0,0,0,0.1)",
            }}
          >
            {themeToggle ? (
              <HiOutlineSun size={20} />
            ) : (
              <HiOutlineMoon size={20} />
            )}
          </Button>
        </Header>

        <Content
          style={{
            margin: isMobile ? "6px 8px" : "14px 16px",
            padding: 24,
            minHeight: 280,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
            overflow: "auto",
          }}
        >
          <Outlet />
        </Content>
      </Layout>
    </Layout>
  );
}

export default AuthenticatedLayout;
