// CommentInput.js
import React, {useEffect, useState} from 'react';
import './CommentInput.css'; // Bu CSS dosyasını aşağıda oluşturacağız.

const CommentInput = ({productId, user, onCommentSubmit }) => {
    const [comment, setComment] = useState('');
    const token = sessionStorage.getItem("token");
    const username = sessionStorage.getItem("username");
    console.log("user:", user)
    console.log("productId: ", productId)
    console.log("comment: ", comment)





    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch(`http://localhost:8080/api/comments`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    productId: productId,
                    userId: user.id,
                    text: comment }),
            });
            if (response.ok) {

                setComment('');

            } else {

                console.error('Failed to post comment');
            }
        } catch (error) {
            console.error('Error posting comment:', error);
        }
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