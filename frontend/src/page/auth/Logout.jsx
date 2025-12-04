import { useContext, useEffect, useState } from "react";
import { UserContext } from "../../context/UserContext";
import { Navigate } from "react-router-dom";

function Logout() {
  const { logout } = useContext(UserContext);
  const [redirect, setRedirect] = useState(false);

  useEffect(() => {
    logout();
    setRedirect(true);
  }, []);

  if (redirect) return <Navigate to="/login" replace />;

  return <div>Logging out...</div>;
}

export default Logout;
