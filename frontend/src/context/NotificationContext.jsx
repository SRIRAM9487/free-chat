import { createContext } from "react";

export const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  return (
    <NotificationContext.Provider>
      <div>{children}</div>
    </NotificationContext.Provider>
  );
};
