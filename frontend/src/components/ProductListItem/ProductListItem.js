// frontend/src/components/ProductListItem/ProductListItem.js
import React, { useState } from 'react';
import './ProductListItem.css';

const ProductListItem = ({ product}) => {
    const [isEditing, setIsEditing] = useState(false);
    const [editableProduct, setEditableProduct] = useState({ ...product });

    const token = sessionStorage.getItem('token');

    const handleEditClick = () => {
        setIsEditing(true);
    };

    const handleDeleteClick = async () => {
        if (window.confirm(`Are you sure you want to delete product ${product.id}?`)) {
            const response = await fetch(`http://localhost:8080/api/product/${product.id}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.ok) {
                console.log(`Product ${product.id} deleted successfully.`);

            } else {
                console.error(`Failed to delete product ${product.id}.`);
            }
        }
    };

    const handleInputChange = (event) => {
        const { name, value } = event.target;
        setEditableProduct({ ...editableProduct, [name]: value });
    };

    const handleSaveClick = async () => {
        //onProductUpdate(editableProduct);
        await updateProduct(editableProduct);
        setIsEditing(false);
    };

    const handleCancelClick = () => {
        setIsEditing(false);
        setEditableProduct({ ...product }); // Revert changes
    };


    const updateProduct = async (updatedProduct) => {
        console.log('Updated product:', updatedProduct);
        console.log('Product ID:', product.id);
        console.log("imageURL: ", updatedProduct.img_url)
        console.log("token: ", token)

        try {
            const response = await fetch(`http://localhost:8080/api/product/${product.id}`, {

                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({
                    name : updatedProduct.name,
                    description : updatedProduct.description,
                    price : updatedProduct.price,
                    subcategory_name : updatedProduct.subcategory.title,
                    imgUrl : updatedProduct.img_url,
                    stock : updatedProduct.stock,
                    user_name : updatedProduct.user.user_name
                })
            });

            if (!response.ok) {
                throw new Error('Product update failed');
            }


            console.log('Product updated successfully');
        } catch (error) {
            console.error(error);
        }
    }

    const renderEditView = () => {
        return (
            <>
                <span className="productId">ID: {product.id}</span>
                <input
                    type="text"
                    name="img_url"
                    value={editableProduct.img_url}
                    onChange={handleInputChange}
                    className="productImage"
                />
                <div className="productInfo">
                    <input
                        type="text"
                        name="name"
                        value={editableProduct.name}
                        onChange={handleInputChange}
                        className="productName"
                    />
                    {/* Subcategory ve Merchant düzenlenemiyor varsayılarak kaldırılmıştır. */}
                    <input
                        type="text"
                        name="description"
                        value={editableProduct.description}
                        onChange={handleInputChange}
                        className="productDescription"
                    />
                    <input
                        type="number"
                        name="price"
                        value={editableProduct.price}
                        onChange={handleInputChange}
                        className="productPrice"
                    />
                    <input
                        type="number"
                        name="stock"
                        value={editableProduct.stock}
                        onChange={handleInputChange}
                        className="productStock"
                    />
                    <input
                        type="text"
                        readOnly
                        value={product.user.user_name}
                        className="productMerchant"
                    />

                </div>
                <div className="productActions">
                    <button onClick={handleSaveClick}>Save</button>
                    <button onClick={handleCancelClick}>Cancel</button>
                </div>
            </>
        );
    };
    const renderDefaultView = () => {
        return (
            <>
                <span className="productId">ID: {product.id}</span>
                <img src={product.img_url} className="productImage"/>
                <div className="productInfo">
                    <span className="productName">Name: {product.name}</span>
                    <span className="productSubcategory">Category: {product.subcategory.title}</span>
                    <span className="productDescription">Description: {product.description}</span>
                    <span className="productPrice">Price: {product.price}</span>
                    <span className="productStock">Stock: {product.stock}</span>
                    <span className="productMerchant">Merchant: {product.user.user_name}</span>
                </div>
                <div className="productActions">
                    <button onClick={handleEditClick}>Edit</button>
                    <button onClick={handleDeleteClick}>Delete</button>
                </div>
            </>
        );
    };

    return (
        <div className={`productListItem ${isEditing ? "editing" : ""}`}>
            {isEditing ? renderEditView() : renderDefaultView()}
        </div>
    );
};

export default ProductListItem;