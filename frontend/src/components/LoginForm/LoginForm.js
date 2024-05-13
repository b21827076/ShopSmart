import React from "react";
import { useState } from "react";
import { Link, Redirect, useHistory } from "react-router-dom";
import jwt_decode from "jwt-decode";
import Input from "../Input/Input";
import Button from "../Button/Button";
import Loader from "../Loader/Loader";
import "./LoginForm.css";

const LoginForm = () => {
  const [showError, setShowError] = useState(false);
  const [loading, setLoading] = useState(false);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const token = sessionStorage.getItem("token");

  const history = useHistory();

  const homeRedirect = (path) => {
    history.push(path);
  };

  const fetchUserName = (userId, token) => {
    return fetch("http://localhost:8080/api/user/" + userId, {
      method: "GET",
      headers: {
        "Content-type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        return data.user_name;
      })
      .catch((error) => {
        console.error("There's an error", error);
      });
  };
  const handleSubmit = (event) => {
    event.preventDefault();

    // Loader start
    setLoading(true);

    const opts = {
      method: "POST",
      headers: {
        "Content-type": "application/json",
      },
      body: JSON.stringify({
        user_name: username,
        password: password,
      }),
    };

    fetch("http://localhost:8080/api/auth/login", opts)
      .then((res) => {
        if (res.status === 200) {
          if (showError === true) {
            setShowError(!showError);
          }
          return res.json();
        } else {
          if (showError === false) {
            setShowError(!showError);
          }
          setLoading(false); // Loader stops
          throw new Error("Promise Chain Cancelled");
        }
      })
      .then((data) => {
        console.log(data);
        // JWT Token gets stored
        sessionStorage.setItem("token", data.accessToken);
        sessionStorage.setItem("id", data.userId);
        sessionStorage.setItem("username", data.username);
        sessionStorage.setItem("user_role", data.role);
        console.log("user_id: ",sessionStorage.getItem("id"));
        console.log("token: ",sessionStorage.getItem("token"));
        console.log("username: " ,sessionStorage.getItem("username"));
        setLoading(false); // Loader stops
        homeRedirect("/home");
        })
      .catch((error) => {
        console.error("There's an error", error);
      });
  };

  return (
    <form onSubmit={handleSubmit}>
      {token && token !== "" && token !== undefined ? (
        <Redirect to="/home" />
      ) : (
        <></>
      )}
      {showError && (
        <h4 className="alert-text">Invalid Username or Password!</h4>
      )}
      <Input
        type="username"
        id="username"
        placeholder="Username"
        onChange={(event) => setUsername(event.target.value)}
        value={username}
      />
      <Input
        type="password"
        id="password"
        placeholder="Password"
        onChange={(event) => setPassword(event.target.value)}
        value={password}
      />
      <Button
        text="Continue"
        type="submit"
        buttonType="btn"
        onSubmit={handleSubmit}
      />
      {loading && <Loader />}
      <h4>
        Don’t have an account?{" "}
        <Link className="link-2" to="/signup">
          Sign Up
        </Link>{" "}
      </h4>
    </form>
  );
};

export default LoginForm;
