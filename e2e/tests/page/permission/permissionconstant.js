export const permission_url = "http://localhost:5173/permission";

export const permission_selectors = {
  permission_title: "permission-title-inp",
  create_btn: "permission-create-btn",
  cancel_btn: "permission-cancel-btn",
  success_notification: "success-notification",
  error_notification: "error-notification",
};

export const permission_success_messages = {
  create: "Permission Created",
  update: "Permission Updated",
  toggle: "Successfully",
  delete: "Permission Deleted",
};

export const permission_error_messages = {
  unique_title: "Permission already exists",
  title_missing: "Title missing",
  network_error: "Network error",
  toggle_failed: "Toggle failed",
};
