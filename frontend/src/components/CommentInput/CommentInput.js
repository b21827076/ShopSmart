// CommentInput.js
import React, { useState } from 'react';
import './CommentInput.css'; // Bu CSS dosyasını aşağıda oluşturacağız.

const CommentInput = ({ onCommentSubmit }) => {
    const [comment, setComment] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        onCommentSubmit(comment);
        setComment(''); // Gönderdikten sonra input'u temizle
    };

    return (
        <form className="comment-input-container" onSubmit={handleSubmit}>
      <textarea
          className="comment-input-field"
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          placeholder="Write a comment..."
      />
            <button type="submit" className="comment-submit-button">Post</button>
        </form>
    );
};

export default CommentInput;