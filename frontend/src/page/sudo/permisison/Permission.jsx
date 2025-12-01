import React from "react";
import { useContext, useEffect, useState } from "react";
import { Button, Space } from "antd";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { postService } from "../../../script/postService";
import { patchService } from "../../../script/patchService";
import CustomTable from "../../../component/CustomTable";
import CustomToggleBtn from "../../../component/CustomToggleBtn";
import InputField from "../../../component/InputField";

function Permission() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [permissionList, setPermissionList] = useState([]);
  const [title, setTitle] = useState("");
  const [error, setError] = useState("");

  const fetchPermisison = async () => {
    //console.log("Permission fetch requested.");
    try {
      const response = await getService("auth/v1/permission");
      // console.log("Fetch response ",response);
      setPermissionList(response.data);
    } catch (error) {
      // console.log("Fetch error ",error)
      showError("Network error");
    }
  };

  useEffect(() => {
    //console.log("Modal mounted");
    fetchPermisison();
  }, []);

  const handleToggleBtn = async (record) => {
    //console.log("Update permission requested")

    try {
      const payload = {
        title: record.title,
        active: !record.active,
      };
      // console.log("Permission update payload ", payload);
      const response = await patchService(
        `auth/v1/permission/update/${record.id}`,
        payload,
      );
      // console.log("Permission update success ", response);
      showSuccess(response.data, 800);
      setTitle("");
      fetchPermisison();
    } catch (error) {
      // console.log("Permission update error ", error);
      showError("Toggle failed");
    }
  };

  const handleCreateBtn = async () => {
    //console.log("Create permission requested")
    if (!title) {
      showError("Title missing");
      setError(true);
      return;
    }
    try {
      const payload = {
        title: title,
        active: true,
      };
      // console.log("Permission update payload ", payload);
      const response = await postService("auth/v1/permission/create", payload);
      showSuccess(response.data);
      setTitle("");
      // console.log("Permission create success ", response);
      fetchPermisison();
    } catch (error) {
      console.log("Permission create error ", error);
      showError(error.message ? error.message : "Creation failed");
    }
  };

  const handlCancelBtn = () => {
    //console.log("Cancel btn clicked")
    setTitle("");
  };

  const columns = [
    {
      title: "S.No",
      render: (_, __, index) => index + 1,
      key: "sno",
      width: 70,
    },
    {
      title: "Title",
      dataIndex: "title",
      key: "title",
      align: "center",
      sorter: (a, b) => a.title.localeCompare(b.title),
      defaultSortOrder: "ascend",
    },
    {
      title: "Actions",
      key: "actions",
      width: 120,
      align: "right",
      render: (_, record, index) => (
        <Space>
          <CustomToggleBtn
            dataTestId={`toggle-btn-${index}`}
            checked={record.active}
            onChange={() => handleToggleBtn(record)}
          />
        </Space>
      ),
    },
  ];

  return (
    <div>
      <div className="flex justify-end mb-2 border border-gray-100 p-1">
        <Space>
          <InputField
            dataTestId="permission-title-inp"
            placeholder="Permission"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            size="mid"
            error={error}
          />

          <Button
            data-testid="permission-create-btn"
            type="button"
            color="primary"
            variant="outlined"
            onClick={handleCreateBtn}
          >
            Create
          </Button>

          <Button
            data-testid="permission-cancel-btn"
            type="button"
            color="red"
            variant="outlined"
            onClick={handlCancelBtn}
          >
            Cancel
          </Button>
        </Space>
      </div>

      <div className="flex justify-end mb-4 p-1">
        <CustomTable data={permissionList} columns={columns} />
      </div>
    </div>
  );
}

export default Permission;
