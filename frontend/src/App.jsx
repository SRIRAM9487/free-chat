import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./page/auth/Login";
import Register from "./page/auth/Register";
import AuthenticatedLayout from "./layout/auth/AuthenticatedLayout";
import Dashboard from "./page/common/Dashboard";
import Permission from "./page/sudo/permisison/Permission";
import Role from "./page/sudo/role/Role";
import User from "./page/sudo/user/User";
import Logout from "./page/auth/Logout";
import NotFound from "./page/common/NotFound.jsx";
import ChatLayout from "./page/chat/layout/ChatLayout.jsx";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/logout" element={<Logout />} />
        <Route path="/register" element={<Register />} />
        <Route element={<AuthenticatedLayout />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/role" element={<Role />} />
          <Route path="/permission" element={<Permission />} />
          <Route path="/user" element={<User />} />
        </Route>

        {/* Chat app */}
        <Route element={<ChatLayout />}>
          <Route path="/chat" element={<User />} />
        </Route>

        {/* COMMON */}
        <Route path="/*" element={<NotFound />} />
      </Routes>
    </Router>
  );
}

export default App;
