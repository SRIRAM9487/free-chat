import React from "react";
import { useContext, useEffect, useState } from "react";
import { Button, Space } from "antd";
import CustomTable from "../../../component/CustomTable";
import CustomToggleBtn from "../../../component/CustomToggleBtn";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { postService } from "../../../script/postService";
import { patchService } from "../../../script/patchService";
import InputField from "../../../component/InputField";

function Permission() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [permissionList, setPermissionList] = useState([]);
  const [title, setTitle] = useState("");

  const fetchPermisison = async () => {
    try {
      const response = await getService("v1/permission");
      // console.log(response);
      setPermissionList(response.data);
    } catch (error) {
      showError("Network error");
    }
  };

  useEffect(() => {
    fetchPermisison();
  }, []);

  const handleToggleBtn = async (record) => {
    try {
      const payload = {
        title: record.title,
        active: !record.active,
      };
      // console.log("UPDATE: ", payload);
      const response = await patchService(
        `v1/permission/update/${record.id}`,
        payload,
      );
      // console.log("SUCCESS: ", response);
      showSuccess(response.data, 800);
      setTitle("");
      fetchPermisison();
    } catch (error) {
      showError("Toggle failed");
    }
  };

  const handleCreateBtn = async () => {
    try {
      const payload = {
        title: title,
        active: true,
      };
      const response = await postService("auth/v1/permission/create", payload);
      showSuccess(response.data);
      setTitle("");
      fetchPermisison();
    } catch (error) {
      showError("Creation failed");
    }
  };

  const handlCancelBtn = () => {
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
    },
    {
      title: "Actions",
      key: "actions",
      width: 120,
      align: "right",
      render: (_, record) => (
        <Space>
          <CustomToggleBtn
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
