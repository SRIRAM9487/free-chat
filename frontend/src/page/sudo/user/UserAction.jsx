import { Modal } from "antd";

function UserAction({ isModelOpen, handleModalClose }) {
  return (
    <Modal
      title="ACTIONS"
      open={isModelOpen}
      onCancel={handleModalClose}
      width="90%"
      footer={null}
      styles={{
        header: { textAlign: "center" },
        body: { padding: 0 },
      }}
    >
      <div>MODAl</div>
    </Modal>
  );
}

export default UserAction;
