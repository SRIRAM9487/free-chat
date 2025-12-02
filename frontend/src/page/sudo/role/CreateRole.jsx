import { useContext, useEffect, useState } from "react";
import { Button, Modal } from "antd";
import InputField from "../../../component/InputField";
import CustomToggleBtn from "../../../component/CustomToggleBtn";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { postService } from "../../../script/postService";
import { patchService } from "../../../script/patchService";

function CreateRole({ isModelOpen, handleModalClose, view, editRecord }) {
  const DEFAULT_FORM = {
    title: "",
    active: false,
    rolePermissions: [],
  };

  const [formData, setFormData] = useState(DEFAULT_FORM);
  const { showError, showSuccess } = useContext(NotificationContext);

  const clearFormData = () => {
    //console.log("Form data cleared");
    setFormData(() => DEFAULT_FORM);
  };

  const fetchPermissions = async () => {
    //console.log("Fetching permissions");
    try {
      const response = await getService("auth/v1/permission");
      //console.log("Permsisions fetched successfully",response);
      const permissionData = response.data.map((perm) => ({
        permissionId: perm.id,
        active: false,
        title: perm.title,
      }));

      setFormData((prev) => ({
        ...prev,
        rolePermissions: permissionData,
      }));
    } catch (error) {
      //console.log("Permsisions fetched error",error);
      showError("Network error");
    }
  };

  const updateFormData = () => {
    if (!editRecord) {
      clearFormData();
      return;
    }

    const permission = editRecord.rolePermissions.map((perm) => ({
      permissionId: perm.permissionId,
      active: perm.active,
      title: perm.title,
    }));

    const formData = {
      title: editRecord.title,
      active: editRecord.active,
      rolePermissions: permission,
    };

    setFormData(formData);
  };

  useEffect(() => {
    if (editRecord) {
      updateFormData();
    } else {
      clearFormData();
      fetchPermissions();
    }
  }, [editRecord]);

  /*
  useEffect(() => {
    console.log("FORM DATA ", formData);
  }, [formData]);
  */

  const handleSubmitForm = async (e) => {
    //console.log("Form submission requested");
    e.preventDefault();
    try {
      if (!editRecord) {
        const response = await postService("auth/v1/role/create", formData);
        //console.log("Role create successfully  : ", response);
        showSuccess(response.data);
      } else {
        const response = await patchService(
          `auth/v1/role/update/${editRecord.id}`,
          formData,
        );
        console.log("Role updated successfully  : ", response);
        showSuccess(response.data);
      }
      clearFormData();
      handleModalClose?.();
    } catch (error) {
      console.log("Role submint Error", error);
      showError(error?.message ? error?.message : "Network error");
    }
  };

  return (
    <Modal
      style={{ top: 20 }}
      title={view ? "View Role" : editRecord ? "Edit Role" : "Create Role"}
      open={isModelOpen}
      onCancel={handleModalClose}
      footer
      styles={{
        header: { textAlign: "center" },
        body: { padding: 0 },
      }}
      destroyOnHidden={true}
      className="overflow-auto"
      data-testid="role-modal"
    >
      <form
        onSubmit={handleSubmitForm}
        className="flex flex-col max-h-[75vh] p-3"
      >
        <div className="space-y-4 pb-3">
          <InputField
            dataTestId="role-title-inp"
            disabled={view}
            label="Title"
            placeholder="Title"
            value={formData.title}
            onChange={(e) =>
              setFormData((prev) => ({ ...prev, title: e.target.value }))
            }
          />

          <div className="flex items-center justify-between bg-gray-50 px-4 py-3 rounded-md">
            <span className="text-gray-700 font-bold">Active</span>
            <CustomToggleBtn
              dataTestId="role-toggle-active"
              disabled={view}
              checked={formData.active}
              onChange={() =>
                setFormData((prev) => ({ ...prev, active: !formData.active }))
              }
            />
          </div>

          {!view && (
            <div className="flex justify-end gap-3 pt-2">
              <Button
                type="default"
                onClick={handleModalClose}
                data-testid="role-cancel-btn"
              >
                Cancel
              </Button>

              <Button
                type="primary"
                htmlType="submit"
                data-testid="role-modal-create-btn"
              >
                {editRecord ? "Update" : "Create"}
              </Button>
            </div>
          )}
        </div>

        <div className="flex items-center justify-between bg-gray-50 px-4 py-3 rounded-md">
          PERMISSION
        </div>
        <div className=" overflow-y-scroll">
          {formData?.rolePermissions?.map((permission, index) => (
            <div
              key={index}
              className="flex items-center justify-between px-4 py-3 rounded-md"
            >
              <span className="text-gray-700 font-bold">
                {permission.title}
              </span>
              <CustomToggleBtn
                dataTestId={`permission-toggle-${index}`}
                disabled={view}
                checked={permission.active}
                onChange={() =>
                  setFormData((prev) => ({
                    ...prev,
                    rolePermissions: prev.rolePermissions.map((p, i) =>
                      i === index ? { ...p, active: !p.active } : p,
                    ),
                  }))
                }
              />
            </div>
          ))}
        </div>
      </form>
    </Modal>
  );
}

export default CreateRole;
