import { Button, Table, Input } from "antd";
import { useState, useEffect, useRef } from "react";
import { FilePdfOutlined, FileExcelOutlined } from "@ant-design/icons";

const { Search } = Input;

function CustomTable({
  dataTestId,
  columns,
  searchColumnName = "name",
  data,
  loading = false,
  bordered = true,
}) {
  const [searchText, setSearchText] = useState("");
  const [isMobile, setIsMobile] = useState(false);
  const tableRef = useRef(null);

  useEffect(() => {
    const handleResize = () => setIsMobile(window.innerWidth < 768);
    handleResize();
    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, []);

  // TODO:
  const handleSearch = () => {
    console.log("PDF EXPORTED");
  };

  // TODO:
  const handleExportPDF = () => {
    console.log("PDF EXPORTED");
  };

  // TODO:
  const handleExportExcel = () => {
    console.log("EXCEL EXPORT");
  };

  return (
    <div
      ref={tableRef}
      className="flex flex-col border border-gray-200 rounded-md overflow-hidden h-full "
      style={{ width: "100%" }}
    >
      {!isMobile && (
        <div className="flex flex-wrap items-center justify-between gap-3 p-2 bg-gray-50 border-b border-gray-200">
          <div className="flex-1 min-w-[200px] ">
            <Search
              placeholder={`Search by ${searchColumnName}...`}
              allowClear
              onSearch={handleSearch}
              onChange={(e) => setSearchText(e.target.value)}
              size="large"
              className="w-full "
              value={searchText}
            />
          </div>
          <div className="flex flex-wrap items-center gap-2 justify-end">
            <Button
              type="primary"
              danger
              icon={<FilePdfOutlined />}
              onClick={handleExportPDF}
              className="!rounded-none"
            >
              Export PDF
            </Button>

            <Button
              type="primary"
              icon={<FileExcelOutlined />}
              style={{ backgroundColor: "#52c41a", borderColor: "#52c41a" }}
              onClick={handleExportExcel}
              className="!rounded-none"
            >
              Export Excel
            </Button>
          </div>
        </div>
      )}

      {isMobile && (
        <div className="p-2 bg-gray-50 border-b border-gray-200 ">
          <Search
            placeholder={`Search by ${searchColumnName}...`}
            allowClear
            onSearch={handleSearch}
            onChange={(e) => setSearchText(e.target.value)}
            size="middle"
            className="w-full "
            value={searchText}
          />
          <div className="flex gap-2 p-2 justify-start">
            <Button
              type="primary"
              danger
              icon={<FilePdfOutlined />}
              onClick={handleExportPDF}
            />

            <Button
              type="primary"
              icon={<FileExcelOutlined />}
              style={{ backgroundColor: "#52c41a", borderColor: "#52c41a" }}
              onClick={handleExportExcel}
            />
          </div>
        </div>
      )}

      <div className="" style={{ flex: 1, width: "100%", overflowX: "auto" }}>
        <Table
          columns={columns}
          dataSource={data}
          loading={loading}
          rowKey={(record) => record.id || record.key}
          scroll={{ x: "max-content", y: "max-content" }}
          bordered={bordered}
          pagination={{
            responsive: true,
            size: isMobile ? "small" : "default",
          }}
          size={isMobile ? "small" : "middle"}
        />
      </div>
    </div>
  );
}

export default CustomTable;
