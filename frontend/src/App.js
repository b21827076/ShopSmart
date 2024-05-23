import React, { useState, useEffect } from "react";
import Home from "./pages/home/Home";
import Login from "./pages/login/Login";
import Register from "./pages/register/Register";
import Admin from "./pages/admin/Admin";
import Categories from "./pages/categories/Categories";
import ManageProducts from './pages/manageProducts/ManageProducts';
import Product from "./pages/productPage/ProductPage";
import Subcategories from "./pages/subcategories/Subcategories";
import Products from "./pages/products/Products";
import ProductPage from "./pages/productPage/ProductPage";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";

var stompClient = null;
const App = () => {
  const [isSearched, setSearched] = useState(false);
  const [keyword, setKeyword] = useState("");
  const [isNotify, setIsNotify] = useState(false);
  const [messageSender, setMessageSender] = useState("");
  const [stomp, setStomp] = useState(null);
  const userId = sessionStorage.getItem("id");

  useEffect(() => {
    console.log("TRIES TO CONNECT");
    connect();
  }, []);

  const connect = () => {
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:8080/ws");
    stompClient = Stomp.over(SockJS);
    setStomp(stompClient);
    stompClient.connect({}, onConnected, (err) => {
      console.log(err);
    });
    console.log("StompClient: ");
    console.log(stompClient);
  };

  const onConnected = () => {
    console.log("connected");
    console.log("Safa is current");
    stompClient.subscribe(
      "/user/" + userId + "/queue/messages",
      onMessageReceived
    );
  };

  const onMessageReceived = (msg) => {
    const notification = JSON.parse(msg.body);
    console.log(notification);
    setMessageSender(notification.senderName);
    setIsNotify(true);
  };


  return (
    <>
      <Router>
        <Switch>
          <Route path="/auth/login" render={(props) => <Login {...props} />} />
          <Route path="/auth/signup" render={(props) => <Register {...props} />} />
          <Route
            path="/home"
            render={(props) => (
              <Home
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route 
            path="/category/:categoryId/subcategory/:subcategoryId"
            render={(props) => (
              <Products
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route 
            path="/category/:categoryId"
            render={(props) => (
              <Subcategories
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route 
            path="/category"
            render={(props) => (
              <Categories
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route
            path = "/products/:productId"
            render={(props) => (
              <ProductPage
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route
            path="/admin"
            render={(props) => (
              <Admin
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route
            path="/manageProducts"
            render={(props) => (
              <ManageProducts
                {...props}
                keyword={keyword}
                setKeyword={setKeyword}
                isSearched={isSearched}
                setSearched={setSearched}
                isNotify={isNotify}
                setIsNotify={setIsNotify}
                messageSender={messageSender}
              />
            )}
          />

          <Route exact path="/">
            <Redirect to="/auth/login" />
          </Route>

        </Switch>
      </Router>
    </>
  );
};

export default App;
