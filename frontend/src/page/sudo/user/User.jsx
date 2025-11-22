import { useState } from "react";
import CustomTable from "../../../component/CustomTable";
import { Button, Space } from "antd";
import CreateUser from "./CreateUser";

function User() {
  const [userData, setUserData] = useState(
    Array.from({ length: 10 }, (_, i) => ({
      id: `user-${i + 1}`,
      name: `Test${i + 1}`,
      userName: `test${i + 1}`,
      email: `test${i + 1}@example.com`,
      emailVerified: false,
      gender: i % 2 === 0 ? "MALE" : "FEMALE",
      accountNonExpired: true,
      accountNonLocked: true,
      enabled: true,
      roles: i % 3 === 0 ? ["Admin"] : ["Manager"],
    })),
  );

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
      title: "Roles",
      dataIndex: "roles",
      key: "roles",
      align: "center",
      render: (roles) => roles.join(", "),
    },
    {
      title: "Action",
      key: "action",
      width: 120,
      align: "center",
      render: (_, record) => (
        <div className="flex justify-around">
          <button className="text-blue-500 hover:underline">Edit</button>
          <button className="text-red-500 hover:underline">Delete</button>
        </div>
      ),
    },
  ];

  return (
    <div>
      <div className="flex justify-end mb-2 border border-gray-100 p-2">
        <CreateUser isModelOpen={true} />

        <div>
          <Space>
            <Button
              type="button"
              color="primary"
              variant="solid"
              className="!rounded !px-6 !py-2.5"
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
