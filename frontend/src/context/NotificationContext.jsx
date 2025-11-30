import { createContext, useState } from "react";
import CustomNotification from "../component/CustomNotification";

export const NotificationContext = createContext();

export const NotificationProvider = ({ children }) => {
  const [alertOpen, setAlertOpen] = useState(false);
  const [alertMessage, setAlertMessage] = useState("");
  const [alertSeverity, setAlertSeverity] = useState("error");
  const [duration, setduration] = useState(null);
  const [dataTestId, setdataTestId] = useState(null);

  const showError = (message, time = 3000) => {
    setAlertMessage(message);
    setAlertSeverity("error");
    setAlertOpen(true);
    setdataTestId("error-notification");
    setduration(time);
  };

  const showSuccess = (message, time = 3000) => {
    setAlertMessage(message);
    setAlertSeverity("success");
    setAlertOpen(true);
    setdataTestId("success-notification");
    setduration(time);
  };

  return (
    <NotificationContext.Provider value={{ showError, showSuccess }}>
      <div>
        <CustomNotification
          dataTestId={dataTestId}
          severity={alertSeverity}
          alertMessage={alertMessage}
          open={alertOpen}
          setOpen={setAlertOpen}
          duration={duration}
        />
        {children}
      </div>
    </NotificationContext.Provider>
  );
};
