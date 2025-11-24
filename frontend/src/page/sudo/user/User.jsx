import { useContext, useEffect, useState } from "react";
import CustomTable from "../../../component/CustomTable";
import { Button, Space } from "antd";
import CreateUser from "./CreateUser";
import { getService } from "../../../script/getService";
import { NotificationContext } from "../../../context/NotificationContext";
import Deletebtn from "../../../component/btns/Deletebtn";
import EditBtn from "../../../component/btns/EditBtn";
import Viewbtn from "../../../component/btns/Viewbtn";

function User() {
  const [userData, setUserData] = useState([]);

  const { showError, showSuccess } = useContext(NotificationContext);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [view, setView] = useState(false);
  const [editRecord, setEditRecord] = useState(null);

  const fetchUsers = async () => {
    try {
      const response = await getService("v1/user");
      setUserData(response.data);
    } catch (error) {}
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleCreateBtn = () => {
    setIsCreateModalOpen(true);
  };

  const handleCreateUserClose = () => {
    setIsCreateModalOpen(false);
  };

  const handleEditBtn = (record) => {
    setEditRecord(record);
    setIsCreateModalOpen(true);
  };

  const handleDeleteBtn = (record) => {
    setEditRecord(record);
  };

  const handleViewBtn = (record) => {
    setEditRecord(record);
  };

  const columns = [
    {
      title: "S.No",
      render: (_, __, index) => index + 1,
      key: "sno",
      width: 70,
    },
    {
      title: "Name",
      dataIndex: "name",
      key: "name",
      align: "center",
      sorter: (a, b) => a.name.localeCompare(b.name),
    },
    {
      title: "User Name",
      dataIndex: "userName",
      key: "userName",
      align: "center",
      sorter: (a, b) => a.userName.localeCompare(b.userName),
    },
    {
      title: "Email",
      dataIndex: "email",
      key: "email",
      align: "center",
      sorter: (a, b) => a.email.localeCompare(b.email),
    },
    {
      title: "Action",
      key: "action",
      width: 120,
      align: "center",
      render: (_, record) => (
        <div className="flex justify-around ">
          <Deletebtn onClick={() => handleDeleteBtn(record)} />
          <EditBtn onClick={() => handleEditBtn(record)} />
          <Viewbtn onClick={() => handleViewBtn(record)} />
        </div>
      ),
    },
  ];

  return (
    <div>
      <div className="flex justify-end mb-2 border border-gray-100 p-2">
        <CreateUser
          isModelOpen={isCreateModalOpen}
          handleModalClose={handleCreateUserClose}
          editRecord={editRecord}
          view={view}
        />

        <div>
          <Space>
            <Button
              type="button"
              color="primary"
              variant="solid"
              className="!rounded !px-6 !py-2.5"
              onClick={handleCreateBtn}
            >
              Create
            </Button>
            <Button
              type="default"
              size="middle"
              className="!rounded !px-6 !py-2.5"
            >
              Upload
            </Button>
            <Button
              type="default"
              size="middle"
              className="!rounded !px-6 !py-2.5"
            >
              Download Template
            </Button>
          </Space>
        </div>
      </div>
      <CustomTable columns={columns} data={userData} />
    </div>
  );
}

export default User;
