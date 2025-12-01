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
    //console.log("Role fetch requested");
    try {
      const response = await getService("auth/v1/role");
      //console.log("Role fetched successfully", response);
      setRoleList(response.data);
    } catch (error) {
      //console.log("Role fetched error ", error);
      showError("Network error");
    }
  };

  useEffect(() => {
    //console.log("Modal mounted");
    fetchRole();
  }, []);

  const handleCreateBtn = async () => {
    //console.log("Create btn clicked");
    setIsModelOpen(true);
  };

  const handleToggleBtn = async (record) => {
    //console.log("Toggle btn clicked");
    try {
      const response = await patchService(`auth/v1/role/toggle/${record.id}`);
      //console.log("Toggle btn response ",response);
      showSuccess(response.data, 800);
      fetchRole();
    } catch (error) {
      //console.log("Toggle btn error ",error);
      showError("Toggle failed");
    }
  };
  const handleEditBtn = (record) => {
    //console.log("edit btn clicked");
    setEditRecord(record);
    setIsModelOpen(true);
  };

  const handleViewBtn = (record) => {
    //console.log("view btn clicked");
    setEditRecord(record);
    setIsModelOpen(true);
    setView(true);
  };

  const handleModalClose = () => {
    //console.log("Modal closed");
    setIsModelOpen(false);
    fetchRole();
    setEditRecord(null);
    setView(false);
  };

  const handlDeleteBtn = (record) => {
    //console.log("delete btn clicked");
    setIsDeleteBoxOpen(true);
    setEditRecord(record);
  };

  const handleDeleteBoxClose = () => {
    //console.log("delete box closed");
    setEditRecord(null);
    setIsDeleteBoxOpen(false);
  };

  const handleDeleteBoxConfirm = async () => {
    //console.log("Delete confirmed")
    try {
      const response = await deleteService(`auth/v1/role/${editRecord.id}`);
      showSuccess(response.data);
      fetchRole();
      //console.log("Delete successfull response ",response)
    } catch (error) {
      showError("Failed");
      //console.log("Delete successfull error ",error)
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
      defaultSortOrder: "ascend",
    },
    {
      title: "Action",
      key: "action",
      width: 120,
      align: "center",
      render: (_, record, index) => (
        <div className="flex justify-around ">
          <Deletebtn
            dataTestId={`icon-delete-${index}`}
            onClick={() => handlDeleteBtn(record)}
          />
          <EditBtn
            dataTestId={`icon-edit-${index}`}
            onClick={() => handleEditBtn(record)}
          />
          <Viewbtn
            dataTestId={`icon-view-${index}`}
            onClick={() => handleViewBtn(record)}
          />
        </div>
      ),
    },
    {
      title: "Active",
      key: "active",
      width: 120,
      align: "center",
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
        <CreateRole
          isModelOpen={isModelOpen}
          editRecord={editRecord}
          handleModalClose={handleModalClose}
          view={view}
        />

        <CustomDialogBox
          dataTestId="role-dialog"
          open={isDeleteBoxOpen}
          onCancel={handleDeleteBoxClose}
          onConfirm={handleDeleteBoxConfirm}
        />

        <Space>
          <Button
            data-testid="role-create-btn"
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
        <CustomTable dataTestId="role" data={roleList} columns={columns} />
      </div>
    </div>
  );
}

export default Role;
