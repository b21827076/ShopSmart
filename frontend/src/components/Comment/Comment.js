// Comment.js

import { useState } from 'react';
import React from 'react';
import './Comment.css';

const Comment = ({ id ,username, currentUsername, text}) => {
    const [isEditing, setIsEditing] = useState(false);
    const [editedText, setEditedText] = useState(text);
    const role = sessionStorage.getItem("user_role");
    const isAdmin = role === 'Admin';

    const handleDelete = () => {
        if(window.confirm('Are you sure you want to delete this comment?')) {
            const token = sessionStorage.getItem('token'); // Eğer auth gerekiyorsa
            fetch(`http://localhost:8080/api/comments/${id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    // Authorization header'ı ekleyin eğer auth gerekiyorsa
                    'Authorization': `Bearer ${token}`,
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    // Burada yorum listesini yenilemek veya kullanıcıya bir geri bildirim sağlamak için bir şeyler yapabilirsiniz.
                    console.log('Comment deleted successfully');
                })
                .catch(error => console.error('There has been a problem with your fetch operation:', error));
        }
    };

    const handleEdit = () => {
        setIsEditing(true);
    };


    const handleSave = () => {
        const token = sessionStorage.getItem('token');
        fetch(`http://localhost:8080/api/comments`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`,
            },
            body: JSON.stringify({
                id: id,
                text: editedText }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Save failed');
                }
                setIsEditing(false); // Exit editing mode
                // You might still want to update the local state to the new text
            })
            .catch(error => {
                console.error('Save error:', error);
            });
    };

    const handleCancel = () => {
        setEditedText(text); // Reset the edited text to the original text
        setIsEditing(false);
    };

    return (
        <div className="comment">
            <div className="commentUser">{username}</div>
            {isEditing ? (
                <>
                    <textarea className="commentEditTextArea"
                        value={editedText}
                        onChange={(e) => setEditedText(e.target.value)}
                    />
                    <div className="commentEditActions">
                        <button onClick={handleSave}>Save</button>
                        <button onClick={handleCancel}>Cancel</button>
                    </div>
                </>
            ) : (
                <>
                    <div className="commentText">{text}</div>
                    {(currentUsername === username || isAdmin) && (
                        <div className="commentActions">
                            <button onClick={handleEdit}>Edit</button>
                            <button onClick={handleDelete}>Delete</button>
                        </div>
                    )}
                </>
            )}
        </div>
    );


};

export default Comment;