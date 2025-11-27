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
import UserActionbtn from "../../../component/btns/UserActionbtn";

function User() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [userList, setUserList] = useState([
    {
      id: "1",
      name: "Admin User",
      userName: "admin",
      email: "admin@example.com",
      emailVerified: true,
      gender: "MALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [],
    },
    {
      id: "2",
      name: "Manager User",
      userName: "manager",
      email: "manager@example.com",
      emailVerified: false,
      gender: "MALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [],
    },
    {
      id: "3",
      name: "Customer User",
      userName: "customer",
      email: "customer@example.com",
      emailVerified: false,
      gender: "FEMALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [],
    },
    {
      id: "4",
      name: "Chatter User",
      userName: "chatter",
      email: "chatter@example.com",
      emailVerified: false,
      gender: "MALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [],
    },
    {
      id: "5",
      name: "Sudo User",
      userName: "sudo",
      email: "sudo@example.com",
      emailVerified: true,
      gender: "MALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [],
    },
    {
      id: "6",
      name: "sriram",
      userName: "sriram",
      email: "sriram.a.2023.cse@ritchennai.edu.in",
      emailVerified: true,
      gender: "MALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: [
        {
          id: "1",
          title: "ADMIN",
        },
        {
          id: "3",
          title: "CUSTOMER",
        },
        {
          id: "2",
          title: "MANAGER",
        },
      ],
    },
  ]);
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

  const handleUserActionBtn = (record) => {
    setEditRecord(record);
    setIsActionBoxOpen(true);
  };
  const handleActionBoxClose = () => {
    setEditRecord(null);
    setIsActionBoxOpen(false);
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
          <UserActionbtn onClick={() => handleUserActionBtn(record)} />
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
          handleModalClose={handleActionBoxClose}
          editRecord={editRecord}
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
