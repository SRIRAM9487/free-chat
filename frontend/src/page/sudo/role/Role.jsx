import { useContext, useEffect, useState } from "react";
import { Button, Space } from "antd";
import CustomTable from "../../../component/CustomTable";
import InputField from "../../../component/InputField";
import CustomToggleBtn from "../../../component/CustomToggleBtn";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { postService } from "../../../script/postService";
import { patchService } from "../../../script/patchService";
import CustomToggleBox from "../../../component/CustomToggleBox";

function Role() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [permissionList, setPermissionList] = useState([]);
  const [title, setTitle] = useState("");

  const fetchPermisison = async () => {
    try {
      const response = await getService("v1/role");
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
      //const response = await postService("v1/permission/create", payload);
      //showSuccess(response.data);
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
      sorter: (a, b) => a.title.localeCompare(b.title), // <- FIX
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
            placeholder="Permission"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            size="mid"
          />

          <Button
            type="button"
            color="primary"
            variant="outlined"
            onClick={handleCreateBtn}
          >
            Create
          </Button>

          <Button
            type="button"
            color="red"
            variant="outlined"
            onClick={handlCancelBtn}
          >
            Cancel
          </Button>
          <CustomToggleBox />
        </Space>
      </div>

      <div className="flex justify-end mb-4 p-1">
        <CustomTable data={permissionList} columns={columns} />
      </div>
    </div>
  );
}

export default Role;
