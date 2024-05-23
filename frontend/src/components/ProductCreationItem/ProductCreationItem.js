// ProductCreationItem.js

import React, { useState } from 'react';
import './ProductCreationItem.css';

const ProductCreationItem = ({userRole, username, onCancel}) => {
    console.log("username: ", username)
    console.log("userRole: ", userRole)

    const token = sessionStorage.getItem("token");

    const [product, setProduct] = useState({
        photolink: '',
        title: '',
        content: '',
        stock: '',
        user_name: username,
        price: '',
        subcategory: ''
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        setProduct({ ...product, [name]: value });
    };

    console.log("new product:", product);

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/product', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',

                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    photolink: product.photolink,
                    title: product.title,
                    content: product.content,
                    stock: product.stock,
                    user_name: product.user_name,
                    price: product.price,
                    subcategory: product.subcategory
                     }),
            });

            if (response.ok) {

                setProduct({
                    photolink: '',
                    title: '',
                    content: '',
                    stock: '',
                    user_name: username,
                    price: '',
                    subcategory: ''
                });

            } else {

                console.error('Failed to create product');
            }
        } catch (error) {

            console.error('Error creating product:', error);
        }
    };



    const handleCancel = () => {
        setProduct({
            photolink: '',
            title: '',
            content: '',
            stock: '',
            user_name: username,
            price: '',
            subcategory: ''
        });

        onCancel();
    };


    return (
        <form className="product-creation-container" onSubmit={handleSubmit}>
            <div className="product-creation-field">
                <label htmlFor="photolink">Photo Link</label>
                <input
                    type="text"
                    id="photolink"
                    name="photolink"
                    value={product.photolink}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="title">Title</label>
                <input
                    type="text"
                    id="title"
                    name="title"
                    value={product.title}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="content">Content</label>
                <textarea
                    id="content"
                    name="content"
                    value={product.content}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="stock">Stock</label>
                <input
                    type="number"
                    id="stock"
                    name="stock"
                    value={product.stock}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="user_name">User Name</label>
                <input
                    type="text"
                    id="user_name"
                    name="user_name"
                    value={product.user_name}
                    onChange={handleChange}
                    readOnly={userRole !== 'Admin'}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="price">Price</label>
                <input
                    type="number"
                    id="price"
                    name="price"
                    value={product.price}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="product-creation-field">
                <label htmlFor="subcategory">Subcategory</label>
                <input
                    type="text"
                    id="subcategory"
                    name="subcategory"
                    value={product.subcategory}
                    onChange={handleChange}
                    required
                />
            </div>
            <div className="button-group">
                <button type="submit" className="product-creation-submit">Create Product</button>
                <button type="button" className="product-creation-cancel" onClick={handleCancel}>Cancel</button>
            </div>
        </form>
    );
    };

    export default ProductCreationItem;