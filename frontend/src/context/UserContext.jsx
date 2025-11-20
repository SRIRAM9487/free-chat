import { createContext } from "react";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
  return (
    <UserContext.Provider>
      <div>{children}</div>
    </UserContext.Provider>
  );
};
