import { select_account_non_exipred } from "./userutils";

export const user_selectors = {
  create_btn: "user-btn-create",
  create_user_modal: "create-user-modal",
  create_name_inp: "create-name-inp",
  create_username_inp: "create-username-inp",
  create_password_inp: "create-password-inp",
  create_email_inp: "create-email-inp",
  create_gender: "create-gender-inp",
  create_user_btn: "create-submit-btn",
  success_notification: "success-notification",
  error_notification: "error-notification",
  select_role: "create-roles-inp",
  select_account_non_locked: "create-accountnonlocked-inp",
  select_account_non_exipred: "create-accountnonexpired-inp",
  select_enabled: "create-enabled-inp",

  delete_box: "dialog-box",
  delete_box_submit_btn: "dialog-box-confirm-btn",
  delete_box_cancel_btn: "dialog-box-cancel-btn",
};

export const user_success_messages = {
  create: "User Created",
  update: "User Updated",
  delete: "User Deleted",
  toggle: "Successfully",
};

export const user_error_messages = {
  name_required: "Name is required",
  user_name_required: "Username is required",
  password_required: "Password is required",
  password_short: "Password must be at least 8 characters",
  email_required: "Email is required",
  email_invalid: "Enter a valid email address",
  gender_required: "Gender is required",
  roles_required: "At least one role must be selected",
  unique_title: "Role must be unique",
  network_error: "Network error",
  username_exists: "User name already exists",
  email_exists: "User Email already exists",
  failed: "Failed",
};
