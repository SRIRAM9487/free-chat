import { useContext } from "react";
import { UserContext } from "../context/UserContext";
import { Navigate, Outlet } from "react-router-dom";
import Loading from "../page/auth/Loading";

function AdminRoute() {
  const { user, loading } = useContext(UserContext);

  if (loading) return <Loading />;

  return user?.role == "ADMIN" || user?.role == "SUDO" ? (
    <Outlet />
  ) : (
    <Navigate to="/login" replace />
  );
}

export default AdminRoute;
