import React, { useState, useEffect } from 'react';
import RequestTab from "./RequestTab";
import ReportManage from "./ReportManage";
import ApproveUsersTab from "./ApproveUsersTab";
import { useHistory } from "react-router-dom";
  import "./AdminTabs.css";

const AdminTabs = () => {
  const [allUsers, setAllUsers] = useState([]);
  const [pendingUsers, setPendingUsers] = useState([]);

  const username = sessionStorage.getItem("username");
  const userId = sessionStorage.getItem("id");
  const token = sessionStorage.getItem("token");
  const history = useHistory();

  const [searchQuery, setSearchQuery] = useState('');
  // Function to handle search input change
  const handleSearchChange = (event) => {
    setSearchQuery(event.target.value);
  };

  

  const handleApprove = (username) => {
    const url = `http://localhost:8080/api/auth/approve`; 
    const data = { user_name: username };
  
    fetch(url, {
     ...requestOptions,
      body: JSON.stringify(data),
    })
   .then(response => response.json())
   .then(data => {
      console.log('Success:', data);
    })
   .catch((error) => {
      console.error('Error:', error);
    });
  };

  const handleDeny = (username) => {
    const url = `http://localhost:8080/api/auth/deny`; 
    const data = { user_name: username }; 
  
    fetch(url, {
     ...requestOptions,
      body: JSON.stringify(data),
    })
   .then(response => response.json())
   .then(data => {
      console.log('Success:', data);
    })
   .catch((error) => {
      console.error('Error:', error);
    });
  };

  const handleDelete = (username) => {
    const url = `http://localhost:8080/api/user/${username}`;
    const requestOptions = {
      method: 'DELETE',
      headers: {
        'Authorization': `Bearer ${token}`,
      },
    };
  
    fetch(url, requestOptions)
   .then(response => response.ok? console.log('User deleted') : console.error('Error:', response.statusText))
   .catch((error) => {
      console.error('Error:', error);
    });
  };

  const opts = {
    method: "GET",
    headers: {
      "Content-type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };

  const requestOptions = {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    const fetchUsers = async () => {
      try {

        const [allUsersResponse, pendingUsersResponse] = await Promise.all([fetch(`http://localhost:8080/api/user`, opts), fetch(`http://localhost:8080/api/auth`, opts)]);

        const pendingUsersData = await pendingUsersResponse.json();
        const allUsersData = await allUsersResponse.json();

        setAllUsers(allUsersData);
        setPendingUsers(pendingUsersData);
      } catch (error) {
        console.error("Failed to fetch users", error);
      }
    };

    fetchUsers();
  }, []);



  return (
    <div className="admin-tabs">

      <div className="search-container">
        <input
          type="text"
          value={searchQuery}
          onChange={handleSearchChange}
          placeholder="Search users..."
        />
        <button onClick={() => handleApprove(searchQuery)}>Approve</button>
        <button onClick={() => handleDeny(searchQuery)}>Deny</button>
        <button onClick={() => handleDelete(searchQuery)}>Delete</button>
      </div>

      <h2>All Users</h2>
      <ul>
        {allUsers.map(user => (
          <li key={user.id}>{user.user_name}</li>
        ))}
      </ul>

      <h2>Pending Users</h2>
      <ul>
          {pendingUsers.map(user => (
            <li key={user.id}>{user.user_name}</li>
          ))}
      </ul> 
    </div>
  );
};

export default AdminTabs;