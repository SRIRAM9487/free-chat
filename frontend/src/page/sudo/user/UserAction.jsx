import { Button, Modal, Card, Space, Typography } from "antd";
import { TfiEmail } from "react-icons/tfi";
import { IoKey } from "react-icons/io5";
import { LuLock } from "react-icons/lu";
import { useContext } from "react";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { UserContext } from "../../../context/UserContext";

const { Text } = Typography;

function UserAction({ isModelOpen, handleModalClose, editRecord }) {
  const { showError, showSuccess } = useContext(NotificationContext);
  const { user } = useContext(UserContext);

  const handleEmailVerificationRequest = async () => {
    try {
      const response = await getService(
        `auth/v1/user/email/verify/${editRecord.id}`,
        user?.token,
      );
      console.log("RESPONSE : ", response);
      showSuccess(response.data);
    } catch (error) {
      console.log("ERROR : ", error);
      showError("Failed");
    }
  };

  const handleResetPassword = async () => {};
  const handleLockUser = async () => {};

  return (
    <Modal
      data-testid="user-action-modal"
      title="User Actions"
      open={isModelOpen}
      onCancel={handleModalClose}
      width="40%"
      footer={null}
      styles={{
        header: { textAlign: "center" },
        body: { padding: "12px 0" },
      }}
    >
      <Space direction="vertical" size="small" style={{ width: "100%" }}>
        <Card
          hoverable
          className="rounded-xl shadow-sm"
          bodyStyle={{
            padding: "16px 20px",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Text strong>Send Email Verification</Text>
          <Button
            data-testid="email-verification-btn"
            icon={<TfiEmail />}
            type="button"
            color="green"
            variant="solid"
            className="!rounded !px-6 !py-2.5"
            onClick={handleEmailVerificationRequest}
          />
        </Card>
        <Card
          hoverable
          className="rounded-xl shadow-sm"
          bodyStyle={{
            padding: "16px 20px",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Text strong>Reset Password</Text>
          <Button
            data-testid="password-reset-btn"
            icon={<IoKey />}
            type="button"
            color="blue"
            variant="solid"
            className="!rounded !px-6 !py-2.5"
          />
        </Card>
        <Card
          hoverable
          className="rounded-xl shadow-sm"
          bodyStyle={{
            padding: "16px 20px",
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
          }}
        >
          <Text strong>Lock User</Text>
          <Button
            data-testid="lock-user-btn"
            icon={<LuLock />}
            type="button"
            color="red"
            variant="solid"
            className="!rounded !px-6 !py-2.5"
          />
        </Card>
      </Space>
    </Modal>
  );
}

export default UserAction;
