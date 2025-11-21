import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./page/auth/Login";
import Register from "./page/auth/Register";
import AuthenticatedLayout from "./layout/auth/AuthenticatedLayout";
import Dashboard from "./page/common/Dashboard";
import Permission from "./page/sudo/permisison/Permission";
import Role from "./page/sudo/role/Role";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route element={<AuthenticatedLayout />}>
          <Route path="/dashboard" element={<Dashboard />} />
          <Route path="/role" element={<Role />} />
          <Route path="/permission" element={<Permission />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;
