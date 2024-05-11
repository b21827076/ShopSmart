// Comment.js
import React from 'react';
import './Comment.css';

const Comment = ({ username, timestamp, text }) => (
    <div className="comment">
      <div className="commentUser">{username}</div>
      <div className="commentTimestamp">{timestamp}</div>
      <div className="commentText">{text}</div>
    </div>
);

export default Comment;