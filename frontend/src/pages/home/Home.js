import React, { useEffect, useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Sidebar from "../../components/Sidebar/Sidebar"; 


import "./Home.css";

const Home = (props) => {
  const [followPing, setFollowPing] = useState(true);

  const user = JSON.parse(sessionStorage.getItem("user"));
  
  return (
    <>
      <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword} />

      <div className="homeContainer">
        <Sidebar />

      </div>
    </>
  );
};

export default Home;
