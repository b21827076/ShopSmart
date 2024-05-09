import React, { useEffect, useState } from "react";
import FavoriteIcon from "@mui/icons-material/Favorite";
import { pink } from "@mui/material/colors";
import "./Like.css";

const Like = ({ productId }) => {
 const [likeCount, setLikeCount] = useState(0);

 useEffect(() => {
    const fetchLikeCount = async () => {
      const response = await fetch(`http://localhost:8080/api/likes/count/${productId}`);
      const data = await response.json();
      setLikeCount(data);
    };

    fetchLikeCount();
 }, [productId]); 

 return (
    <div>
      <FavoriteIcon style={{ color: pink[500] }} />
      <span>{likeCount}</span>
    </div>
 );
};

export default Like;