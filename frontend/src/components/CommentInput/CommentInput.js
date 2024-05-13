// CommentInput.js
import React, {useEffect, useState} from 'react';
import './CommentInput.css'; // Bu CSS dosyasını aşağıda oluşturacağız.

const CommentInput = ({productId, userId, onCommentSubmit }) => {
    const [comment, setComment] = useState('');
    const token = sessionStorage.getItem("token");
    const username = sessionStorage.getItem("username");
    console.log("productId: ", productId)
    console.log("userId: ", userId)
    console.log("comment: ", comment)
    console.log("onCommentSubmit: ", onCommentSubmit)





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
                    userId: userId,
                    text: comment }),
            });
            if (response.ok) {
                // Yorum başarıyla gönderildi, formu temizle
                setComment('');
                // Yorumlar listesini güncellemek için burada bir şeyler yapabilirsiniz.
            } else {
                // Sunucu bir hata döndü
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