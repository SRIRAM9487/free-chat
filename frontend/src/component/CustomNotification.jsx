import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import { Slide } from "@mui/material";

function SlideTransition(props) {
  return <Slide {...props} />;
}

function CustomNotification({
  severity,
  alertMessage,
  open,
  setOpen,
  duration,
}) {
  const vertical = "top";
  const horizontal = "center";

  const handleClose = (_, reason) => {
    if (reason !== "clickaway") {
      setOpen(false);
    }
  };

  return (
    <Snackbar
      open={open}
      autoHideDuration={duration}
      onClose={handleClose}
      anchorOrigin={{ vertical, horizontal }}
      TransitionComponent={SlideTransition}
    >
      <Alert
        onClose={handleClose}
        severity={severity}
        variant="filled"
        sx={{ width: "100%", fontWeight: "bold" }}
      >
        {alertMessage}
      </Alert>
    </Snackbar>
  );
}

export default CustomNotification;
