import "./Navbar.css";
import { useState, useEffect } from "react";
import { Person, Chat, Notifications, HomeOutlined } from "@mui/icons-material";
import Searchbar from "../Searchbar/Searchbar";
import logo from "../../assets/whiteLogo.svg";
import { useHistory } from "react-router-dom";

const Navbar = ({ setSearched, setKeyword }) => {
  const username = sessionStorage.getItem("username");
  const token = sessionStorage.getItem("token");
  const history = useHistory();

  const [portal, setPortal] = useState(false);
  const [profileImage, setProfileImage] = useState("");

  const redirectTo = (path) => {
    history.push(path);
    window.scrollTo(0, 0);
  };

  const linkFormatter = (link) => {
    if (link.charAt(0) === ".") {
      link = link.substring(1);
    }

    return link;
  };

  const opts = {
    method: "GET",
    headers: {
      "Content-type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    fetch(`http://localhost:8080/api/profile/${username.toString()}`, opts)
      .then((res) => {
          console.log("res: ",res);
        return res.json();
      })
      .then((data) => {
        setProfileImage(linkFormatter(data.profilePicture));
        console.log("data: ", data);
        return data;
      })
      .catch((error) => {
        console.error("There's an error", error);
      });
  }, [username]);

  return (
    <div className="navbarContainer">
      <div className="navbarLeft">
        <span className="logoContainer">
          <img
            className="navbarLogo"
            src={logo}
            alt="logo"
            onClick={() => {
              setSearched(false);
              setPortal(false);
              setKeyword("");
              redirectTo("/home");
            }}
          ></img>
        </span>
      </div>
      <div className="navbarCenter">
        <Searchbar setSearched={setSearched} setKeyword={setKeyword} />
      </div>
      <div className="navbarRight">
        <div className="navbarIcons">
          <HomeOutlined
            style={{ height: "35px", width: "35px" }}
            className="homeNavbarIcon infoNavbarIcons"
            onClick={() => {
              redirectTo("/home");
              window.scrollTo(0, 0);
            }}
          />
          <div
            className="profileArea unselectable"
            onClick={() => redirectTo(`/profile/${username}`)}
          >
            <img src={profileImage} alt="" className="navbarProfilePicture" />
            <span className="profileAreaText">{username}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Navbar;
