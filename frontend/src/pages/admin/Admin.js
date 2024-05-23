import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Sidebar from "../../components/Sidebar/Sidebar";
import AdminTabs from "../../components/AdminTabs/AdminTabs";
import "./Admin.css";

const Admin = (props) => {
  return (
    <>
      <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword}/>
      <Sidebar />

      <div className="adminContainer">
        <AdminTabs />

      </div>
    </>
  );
};

export default Admin;
