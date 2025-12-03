import { Button, Table, Input } from "antd";
import { useState, useEffect, useRef } from "react";
import { FilePdfOutlined, FileExcelOutlined } from "@ant-design/icons";

const { Search } = Input;

function CustomTable({
  dataTestId = "table",
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
              data-testid={`${dataTestId}-search`}
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
              data-testid={`${dataTestId}-pdf`}
              type="primary"
              danger
              icon={<FilePdfOutlined />}
              onClick={handleExportPDF}
              className="!rounded-none"
            >
              Export PDF
            </Button>

            <Button
              data-testid={`${dataTestId}-excel`}
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
      <div className="" style={{ flex: 1, width: "100%", overflowX: "auto" }}>
        <Table
          data-testid={`${dataTestId}-table`}
          columns={columns}
          dataSource={data}
          rowKey={(record) => record.id || record.key}
          scroll={{ x: "max-content", y: "max-content" }}
          bordered={bordered}
          pagination={{
            responsive: true,
            size: isMobile ? "small" : "default",
          }}
          size={isMobile ? "small" : "middle"}
          onRow={(record, index) => {
            return {
              "data-testid": `${dataTestId}-row-${index}`,
            };
          }}
        />
      </div>
    </div>
  );
}

export default CustomTable;
