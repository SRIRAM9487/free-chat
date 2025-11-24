import { useContext, useEffect, useState } from "react";
import { Button, Space } from "antd";
import EditBtn from "../../../component/btns/EditBtn";
import Viewbtn from "../../../component/btns/Viewbtn";
import Deletebtn from "../../../component/btns/Deletebtn";
import CreateRole from "./CreateRole";
import CustomTable from "../../../component/CustomTable";
import CustomDialogBox from "../../../component/CustomDialogBox";
import CustomToggleBtn from "../../../component/CustomToggleBtn";
import { NotificationContext } from "../../../context/NotificationContext";
import { getService } from "../../../script/getService";
import { patchService } from "../../../script/patchService";
import { deleteService } from "../../../script/deleteService";

function Role() {
  const { showError, showSuccess } = useContext(NotificationContext);
  const [roleList, setRoleList] = useState([]);
  const [editRecord, setEditRecord] = useState(false);
  const [view, setView] = useState(false);
  const [isModelOpen, setIsModelOpen] = useState(false);
  const [isDeleteBoxOpen, setIsDeleteBoxOpen] = useState(false);

  const fetchRole = async () => {
    try {
      const response = await getService("v1/role");
      setRoleList(response.data);
    } catch (error) {
      showError("Network error");
    }
  };

  useEffect(() => {
    fetchRole();
  }, []);

  const handleCreateBtn = async () => {
    setIsModelOpen(true);
  };

  const handleToggleBtn = async (record) => {
    try {
      const response = await patchService(`v1/role/toggle/${record.id}`);
      showSuccess(response.data, 800);
      fetchRole();
    } catch (error) {
      showError("Toggle failed");
    }
  };
  const handleEditBtn = (record) => {
    setEditRecord(record);
    setIsModelOpen(true);
  };

  const handleViewBtn = (record) => {
    setEditRecord(record);
    setIsModelOpen(true);
    setView(true);
  };

  const handleModalClose = () => {
    setIsModelOpen(false);
    fetchRole();
    setEditRecord(null);
    setView(false);
  };

  const handlDeleteBtn = (record) => {
    setIsDeleteBoxOpen(true);
    setEditRecord(record);
  };

  const handleDeleteBoxClose = () => {
    setEditRecord(null);
    setIsDeleteBoxOpen(false);
  };

  const handleDeleteBoxConfirm = async () => {
    try {
      const response = await deleteService(`v1/role/${editRecord.id}`);
      showSuccess(response.data);
      fetchRole();
    } catch (error) {
      showError("Failed");
    } finally {
      handleDeleteBoxClose();
    }
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
      title: "Action",
      key: "action",
      width: 120,
      align: "center",
      render: (_, record) => (
        <div className="flex justify-around ">
          <Deletebtn onClick={() => handlDeleteBtn(record)} />
          <EditBtn onClick={() => handleEditBtn(record)} />
          <Viewbtn onClick={() => handleViewBtn(record)} />
        </div>
      ),
    },
    {
      title: "Active",
      key: "active",
      width: 120,
      align: "center",
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
        <CreateRole
          isModelOpen={isModelOpen}
          editRecord={editRecord}
          handleModalClose={handleModalClose}
          view={view}
        />

        <CustomDialogBox
          open={isDeleteBoxOpen}
          onCancel={handleDeleteBoxClose}
          onConfirm={handleDeleteBoxConfirm}
        />

        <Space>
          <Button
            type="button"
            color="primary"
            variant="solid"
            onClick={handleCreateBtn}
            className="!rounded !px-6 !py-2.5"
          >
            Create
          </Button>
        </Space>
      </div>

      <div className="flex justify-end mb-4 p-1">
        <CustomTable data={roleList} columns={columns} />
      </div>
    </div>
  );
}

export default Role;
