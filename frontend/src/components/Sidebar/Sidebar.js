import React from "react";
import "./Sidebar.css";
import {
  Home,
  Campaign,
  AddTask,
  Logout,
  AdminPanelSettings,
  Categories,
  ManageProducts
} from "@mui/icons-material";
import Footer from "../Footer/Footer";
import { useHistory } from "react-router-dom";
import { useState } from "react";

const Sidebar = () => {
  const [activeSidebar, setActiveSidebar] = useState("closed");
  const user = JSON.parse(sessionStorage.getItem("user"));
  const role = sessionStorage.getItem("user_role")


  const history = useHistory();

  const redirectTo = (path) => {
    history.push(path);
    window.scrollTo(0, 0);
    history.go(0);
  };

  return (
    <div className="sidebar">
      <div className="sidebarWrapper">
        <ul className="sidebarList">
          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/home");
              window.scrollTo(0, 0);
            }}
          >
            <Home
              className={
                history.location.pathname === "/home"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon"
              }
            />
            <span
              className={
                history.location.pathname === "/home"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            >
              Home
            </span>
          </li>

            {/*
          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/announcements");
              window.scrollTo(0, 0);
            }}
          >
            <Campaign
              className={
                history.location.pathname === "/announcements"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon"
              }
            />
            <span
              className={
                history.location.pathname === "/announcements"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            > 
              Announcements
            </span>
          </li>
          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/jobs");
              window.scrollTo(0, 0);
            }}
          >
            <Work
              className={
                history.location.pathname === "/jobs"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon"
              }
            />
            <span
              className={
                history.location.pathname === "/jobs"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            >
              Scholarships/Jobs
            </span>
          </li>
          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/messages");
              window.scrollTo(0, 0);
            }}
          >
            <Message
              className={
                history.location.pathname === "/messages"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon"
              }
            />
            <span
              className={
                history.location.pathname === "/messages"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            >
              Messages
            </span>
            </li> */}

          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/category");
              window.scrollTo(0, 0);
            }}
          >
            <Home
              className={
                history.location.pathname === "/category"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon" 
              }
            />
            <span
              className={
                history.location.pathname === "/category"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            >
              Categories
            </span>
          </li>

          {role === "Merchant" && (
            <li
              className="sidebarListItem"
              onClick={() => {
                redirectTo("/manageProducts");
                window.scrollTo(0, 0);
              }}
            >
              <AddTask
                className={
                  history.location.pathname === "/manageProducts"
                    ? "sidebarIcon selectedIcon"
                    : "sidebarIcon"
                }
              />
              <span
                className={
                  history.location.pathname === "/manageProducts"
                    ? "sidebarListItemText selectedIcon"
                    : "sidebarIcon"
                }
              >
                Manage Products
              </span>
            </li>
          )}

          {role === "Admin" && (
            <li
              className="sidebarListItem"
              onClick={() => {
                redirectTo("/manageProducts");
                window.scrollTo(0, 0);
              }}
            >
              <AddTask
                className={
                  history.location.pathname === "/manageProducts"
                    ? "sidebarIcon selectedIcon"
                    : "sidebarIcon"
                }
              />
              <span
                className={
                  history.location.pathname === "/manageProducts"
                    ? "sidebarListItemText selectedIcon"
                    : "sidebarIcon"
                }
              >
                Manage Products
              </span>
            </li>
          )}
        
          {role === "Admin" && (
            <li
              className="sidebarListItem"
              onClick={() => {
                redirectTo("/admin");
                window.scrollTo(0, 0);
              }}
            >
              <AdminPanelSettings
                className={
                  history.location.pathname === "/admin"
                    ? "sidebarIcon selectedIcon"
                    : "sidebarIcon"
                }
              />
              <span
                className={
                  history.location.pathname === "/admin"
                    ? "sidebarListItemText selectedIcon"
                    : "sidebarIcon"
                }
              >
                Admin Panel
              </span>
            </li>
          )}
          
          <li
            className="sidebarListItem"
            onClick={() => {
              redirectTo("/editProfile");
              window.scrollTo(0, 0);
            }}
          > 
            <Campaign
              className={
                history.location.pathname === "/editProfile"
                  ? "sidebarIcon selectedIcon"
                  : "sidebarIcon"
              }
            />
            <span
              className={
                history.location.pathname === "/editProfile"
                  ? "sidebarListItemText selectedIcon"
                  : "sidebarIcon"
              }
            >
              Edit Profile
            </span>
          </li>

          <li
            className="sidebarListItem"
            onClick={() => {
              sessionStorage.removeItem("token");
              redirectTo("/auth/login");
              window.scrollTo(0, 0);
            }}
          >
            <Logout
              className="sidebarIcon"
              sx={{ transform: "rotate(180deg)" }}
            />
            <span className="sidebarListItemText">Log Out</span>
          </li>
        </ul>
        <Footer />
      </div>
    </div>
  );
};

export default Sidebar;
