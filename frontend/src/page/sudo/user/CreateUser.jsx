import { Button, Modal } from "antd";
import InputField from "../../../component/InputField";
import { useContext, useEffect, useState } from "react";
import { getService } from "../../../script/getService";
import CustomSelectBox from "../../../component/CustomSelectBox";
import { postService } from "../../../script/postService";
import { NotificationContext } from "../../../context/NotificationContext";
import { patchService } from "../../../script/patchService";

function CreateUser({ isModelOpen, handleModalClose, view, editRecord }) {
  const DEFAULT_FORM = {
    name: "",
    userName: "",
    password: "",
    email: "",
    gender: "",
    accountNonExpired: true,
    accountNonLocked: true,
    enabled: true,
    roles: [],
  };
  const [formData, setFormData] = useState(DEFAULT_FORM);

  const [roleList, setRoleList] = useState([]);
  const { showError, showSuccess } = useContext(NotificationContext);

  const clearFormData = () => {
    setFormData(() => DEFAULT_FORM);
  };

  const fetchRolesList = async () => {
    try {
      const response = await getService("v1/role/user/meta");
      const role = response.data.map((role) => ({
        value: role.id,
        label: role.title,
      }));
      setRoleList(role);
    } catch (error) {
      showError("Network error");
    }
  };

  const updateFormData = () => {
    if (!editRecord) {
      clearFormData();
      return;
    }
    fetchRolesList();

    const activeRoles = editRecord.roles.map((role) => role.id);
    const updatedForm = {
      name: editRecord.name,
      userName: editRecord.userName,
      password: "",
      email: editRecord.email,
      gender: editRecord.gender,
      accountNonExpired: editRecord.accountNonExpired ? "true" : "false",
      accountNonLocked: editRecord.accountNonLocked ? "true" : "false",
      enabled: editRecord.enabled ? "true" : "false",
      roles: activeRoles,
    };

    setFormData(updatedForm);
  };

  useEffect(() => {
    if (editRecord) {
      updateFormData();
    } else {
      clearFormData();
      fetchRolesList();
    }
  }, [editRecord]);

  const title = view ? "View User" : editRecord ? "Edit User" : "Create User";

  const validateForm = () => {
    if (!formData.name.trim()) {
      showError("Name is required");
      return false;
    }

    if (!formData.userName.trim()) {
      showError("Username is required");
      return false;
    }

    if (!formData.email.trim()) {
      showError("Email is required");
      return false;
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(formData.email)) {
      showError("Enter a valid email address");
      return false;
    }

    if (!editRecord) {
      if (!formData.password.trim()) {
        showError("Password is required");
        return false;
      }
      if (formData.password.length < 8) {
        showError("Password must be at least 8 characters");
        return false;
      }
    }

    if (!formData.gender) {
      showError("Gender is required");
      return false;
    }
    if (formData.roles.length === 0) {
      showError("At least one role must be selected");
      return false;
    }

    return true;
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;
    try {
      if (!editRecord) {
        const response = await postService("v1/user/create", formData);
        showSuccess(response.data);
      } else {
        const response = await patchService(
          `v1/user/update/${editRecord.id}`,
          formData,
        );
        showSuccess(response.data);
      }
      if (handleModalClose) handleModalClose();
    } catch (error) {
      showError(error?.response.data.message);
    }
  };

  return (
    <Modal
      style={{ top: 20 }}
      title={title}
      open={isModelOpen}
      onCancel={handleModalClose}
      width="50%"
      footer={null}
      styles={{
        header: { textAlign: "center" },
        body: { padding: 0 },
      }}
    >
      <form
        onSubmit={handleFormSubmit}
        className="flex flex-col gap-6 max-h-[75vh] overflow-y-auto p-6"
      >
        <div className="grid grid-cols-2 gap-6">
          <div className="flex flex-col gap-4">
            <InputField
              disabled={view}
              label="Name"
              value={formData.name}
              readOnly={view}
              onChange={(e) =>
                setFormData((prev) => ({
                  ...prev,
                  name: e.target.value,
                }))
              }
            />

            {!view && (
              <InputField
                label="Password"
                value={formData.password}
                onChange={(e) =>
                  setFormData((prev) => ({
                    ...prev,
                    password: e.target.value,
                  }))
                }
              />
            )}

            <CustomSelectBox
              disabled={view}
              label="Gender"
              options={[
                { value: "MALE", label: "MALE" },
                { value: "FEMALE", label: "FEMALE" },
              ]}
              value={formData.gender}
              readOnly={view}
              handleChange={(value) =>
                setFormData((prev) => ({
                  ...prev,
                  gender: value,
                }))
              }
            />

            <CustomSelectBox
              disabled={view}
              label="Account Non Expired"
              options={[
                { value: "false", label: "Expired" },
                { value: "true", label: "Active" },
              ]}
              value={formData.accountNonExpired}
              readOnly={view}
              handleChange={(value) =>
                setFormData((prev) => ({
                  ...prev,
                  accountNonExpired: value,
                }))
              }
            />
            <CustomSelectBox
              disabled={view}
              label="Enabled"
              options={[
                { value: "false", label: "Expired" },
                { value: "true", label: "Active" },
              ]}
              value={formData.enabled}
              readOnly={view}
              handleChange={(value) =>
                setFormData((prev) => ({
                  ...prev,
                  enabled: value,
                }))
              }
            />
          </div>

          <div className="flex flex-col gap-4">
            <InputField
              disabled={view}
              label="Username"
              value={formData.userName}
              readOnly={view}
              onChange={(e) =>
                setFormData((prev) => ({
                  ...prev,
                  userName: e.target.value,
                }))
              }
            />

            <InputField
              label="Email"
              value={formData.email}
              disabled={view}
              onChange={(e) =>
                setFormData((prev) => ({
                  ...prev,
                  email: e.target.value,
                }))
              }
            />
            <CustomSelectBox
              label="Role(s)"
              mode="multiple"
              options={roleList}
              value={formData.roles}
              readOnly={view}
              handleChange={(value) =>
                setFormData((prev) => ({
                  ...prev,
                  roles: value,
                }))
              }
            />

            <CustomSelectBox
              label="Account Non Locked"
              options={[
                { value: "false", label: "Expired" },
                { value: "true", label: "Active" },
              ]}
              value={formData.accountNonLocked}
              readOnly={view}
              handleChange={(value) =>
                setFormData((prev) => ({
                  ...prev,
                  accountNonLocked: value,
                }))
              }
            />
          </div>
        </div>

        <div className="flex justify-end gap-4 mt-4">
          <Button
            color="red"
            variant="solid"
            className="!rounded !px-6 !py-2.5"
            onClick={handleModalClose}
          >
            Cancel
          </Button>

          {!view && (
            <Button
              type="primary"
              htmlType="submit"
              color="primary"
              variant="solid"
              className="!rounded "
            >
              {editRecord ? "Update User" : "Create User"}
            </Button>
          )}
        </div>
      </form>
    </Modal>
  );
}

export default CreateUser;
