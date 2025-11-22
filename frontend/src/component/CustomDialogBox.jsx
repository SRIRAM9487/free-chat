import { Modal, Button, Space } from "antd";

const CustomDialogBox = ({
  open,
  onCancel,
  onConfirm,
  title = "Confirmation",
  message = "Are you sure you want to perform this action?",
  confirmText = "Confirm",
  cancelText = "Cancel",
  confirmType = "primary",
  loading = false,
  width = "30%",
}) => {
  return (
    <Modal
      open={open}
      onCancel={onCancel}
      title={title}
      footer={null}
      width={width}
      centered
      styles={{ header: { textAlign: "center" } }}
    >
      <p style={{ marginBottom: "1.5rem", fontSize: "1rem" }}>{message}</p>

      <Space style={{ display: "flex", justifyContent: "flex-end" }}>
        <Button onClick={onCancel} disabled={loading}>
          {cancelText}
        </Button>
        <Button
          type={confirmType === "danger" ? "primary" : confirmType}
          danger={confirmType === "danger"}
          onClick={onConfirm}
          loading={loading}
        >
          {confirmText}
        </Button>
      </Space>
    </Modal>
  );
};

export default CustomDialogBox;
