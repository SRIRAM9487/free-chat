import { useContext, useEffect, useState } from "react";
import CustomTable from "../../../component/CustomTable";
import { Button, Space } from "antd";
import CreateUser from "./CreateUser";
import { getService } from "../../../script/getService";
import { NotificationContext } from "../../../context/NotificationContext";
import Deletebtn from "../../../component/btns/Deletebtn";
import EditBtn from "../../../component/btns/EditBtn";
import Viewbtn from "../../../component/btns/Viewbtn";
import CustomDialogBox from "../../../component/CustomDialogBox";
import { deleteService } from "../../../script/deleteService";
import UserAction from "./UserAction";

function User() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [userList, setUserList] = useState([]);
  const [editRecord, setEditRecord] = useState(null);
  const [view, setView] = useState(false);
  const [isCreateModalOpen, setIsCreateModalOpen] = useState(false);
  const [isDeleteBoxOpen, setIsDeleteBoxOpen] = useState(false);
  const [isActionBoxOpen, setIsActionBoxOpen] = useState(false);

  const fetchUsers = async () => {
    try {
      const response = await getService("v1/user");
      console.log(response.data);
      setUserList(response.data);
    } catch (error) {
      showError("Network error");
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleCreateBtn = () => {
    setIsCreateModalOpen(true);
  };

  const handleCreateUserClose = () => {
    setIsCreateModalOpen(false);
    fetchUsers();
    setEditRecord(null);
    if (view) setView(false);
  };

  const handleEditBtn = (record) => {
    setEditRecord(record);
    setIsCreateModalOpen(true);
  };

  const handleViewBtn = (record) => {
    setEditRecord(record);
    setIsCreateModalOpen(true);
    setView(true);
  };

  const handleDeleteBtn = (record) => {
    setEditRecord(record);
    setIsDeleteBoxOpen(true);
  };

  const handleDeleteBtnCancel = () => {
    setEditRecord(null);
    setIsDeleteBoxOpen(false);
    fetchUsers();
  };

  const handleDeleteBtnConfirm = async () => {
    try {
      const response = await deleteService(`v1/user/${editRecord.id}`);
      showSuccess(response.data);
    } catch (error) {
      showError("Failed");
    } finally {
      handleDeleteBtnCancel();
    }
  };

  const handleActionBox = () => {};

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

        <CustomDialogBox
          open={isDeleteBoxOpen}
          onCancel={handleDeleteBtnCancel}
          onConfirm={handleDeleteBtnConfirm}
        />
        <UserAction
          isModelOpen={isActionBoxOpen}
          handleModalClose={handleActionBox}
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
      <CustomTable columns={columns} data={userList} />
    </div>
  );
}

export default User;
