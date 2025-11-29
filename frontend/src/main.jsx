import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import { NotificationProvider } from "./context/NotificationContext.jsx";
import { UserProvider } from "./context/UserContext.jsx";

createRoot(document.getElementById("root")).render(
  <NotificationProvider>
    <UserProvider>
      <App />
    </UserProvider>
  </NotificationProvider>,
);
