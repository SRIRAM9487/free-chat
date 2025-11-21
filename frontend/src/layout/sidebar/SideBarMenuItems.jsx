import { RxDashboard } from "react-icons/rx";
import {
  MdOutlinePayments,
  MdEditNote,
  MdOutlineSecurity,
} from "react-icons/md";
import { IoKeyOutline } from "react-icons/io5";
import { RiIdCardLine } from "react-icons/ri";
import { GrLogout } from "react-icons/gr";
import { Link } from "react-router-dom";
import { FaUserGroup, FaDatabase } from "react-icons/fa6";
import { FaMap, FaMapMarkerAlt } from "react-icons/fa";

const topMenuItems = [
  {
    key: "dashboard",
    icon: <RxDashboard />,
    label: <Link to="/dashboard">Dashboard</Link>,
  },
  {
    key: "customer",
    icon: <FaUserGroup />,
    label: <Link to="/customer">Customer</Link>,
  },
  {
    key: "daily",
    icon: <MdEditNote />,
    label: <Link to="/dailyEntry">Daily Entry</Link>,
  },
  {
    key: "billing",
    icon: <MdOutlinePayments />,
    label: <Link to="/billing">Billing</Link>,
  },
];

const adminMenuItemsExtras = [
  {
    key: "user",
    icon: <MdOutlineSecurity />,
    label: <Link to="/user">User Management</Link>,
  },
];

const sudoMenuItemsExtras = [
  {
    key: "Permission",
    icon: <IoKeyOutline />,
    label: <Link to="/permission">Permission</Link>,
  },
  {
    key: "Role",
    icon: <RiIdCardLine />,
    label: <Link to="/role">Role</Link>,
  },
  {
    key: "master Data",
    icon: <FaDatabase />,
    label: "Master Data",
    children: [
      {
        key: "State",
        icon: <FaMap />,
        label: <Link to="/state">State</Link>,
      },
      {
        key: "District",
        icon: <FaMapMarkerAlt />,
        label: <Link to="/district">District</Link>,
      },
      {
        key: "Taluk",
        icon: <MdEditNote />,
        label: <Link to="/taluk">Taluk</Link>,
      },
      {
        key: "Society",
        icon: <FaUserGroup />,
        label: <Link to="/society">Society</Link>,
      },
      {
        key: "Village",
        icon: <FaDatabase />,
        label: <Link to="/village">Village</Link>,
      },
      {
        key: "Union",
        icon: <FaDatabase />,
        label: <Link to="/union">Union</Link>,
      },
    ],
  },
];

export const managerMenuItems = [...topMenuItems];

export const adminMenuItems = [...managerMenuItems, ...adminMenuItemsExtras];

export const sudoMenuItems = [...adminMenuItems, ...sudoMenuItemsExtras];

export const bottomMenuItems = [
  {
    key: "logout",
    icon: <GrLogout />,
    label: <Link to="/logout">Logout</Link>,
  },
];
