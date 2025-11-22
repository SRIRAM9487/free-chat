import { Button, Modal } from "antd";
import InputField from "../../../component/InputField";
function CreateUser({ isModelOpen, handleModalClose, view, editRecord }) {
  const handleSubmit = () => {};
  return (
    <Modal
      style={{ top: 20 }}
      title={view ? "View User" : editRecord ? "Edit User" : "Create User"}
      open={isModelOpen}
      onCancel={handleModalClose}
      width={"20%"}
      footer
      styles={{
        header: { textAlign: "center" },
        body: { padding: 0 },
      }}
      destroyOnHidden={true}
    >
      <form onSubmit={handleSubmit} className="flex  max-h-[75vh] p-3">
        <div>
          <div>
            <InputField label="name" />
            <InputField label="password" />
            <InputField label="gender" />
            <InputField label="accout non expired" />
            <InputField label="Enabled" />
          </div>
          <div>
            <InputField label="User Name" />
            <InputField label="email" />
            <InputField label="accout non locked" />
            <InputField label="ROles" />
          </div>
        </div>
        <div>
          <Button />
          <Button />
        </div>
      </form>
    </Modal>
  );
}

export default CreateUser;
