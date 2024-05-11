import React, { useState } from 'react';
import './ProductListItem.css';

const ProductListItem = ({ product, onProductUpdate }) => {
    const [isEditing, setIsEditing] = useState({
        name: false,
        description: false,
        price: false,
        imageUrl: false,
        stock: false,
        merchantName: false
    });
    const [editableProduct, setEditableProduct] = useState({ ...product });
    const token = sessionStorage.getItem("token");

    const opts = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
        }
    };


    const handleDoubleClick = (field) => {
        setIsEditing({ ...isEditing, [field]: true });
    };

    const handleBlur = async (field) => {
        setIsEditing({ ...isEditing, [field]: false });
        console.log("editableProduct: ", editableProduct);

        try {
            const response = await fetch(`http://localhost:8080/api/product/${editableProduct.id}`, {

                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}` // Eğer auth gerekiyorsa
                },
                body: JSON.stringify(editableProduct)
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const data = await response.json();
            onProductUpdate(data); // Güncellenen veriyi üst komponente bildir
        } catch (error) {
            console.error('Update failed:', error);
            // Hata durumunda bir işlem yapılabilir, örneğin kullanıcıya bilgi vermek
        }
    };

    const handleInputChange = (field, value) => {
        setEditableProduct({ ...editableProduct, [field]: value });
    };

    const handleKeyDown = (e, field) => {
        if (e.key === 'Enter') {
            handleBlur(field);
        }
    };

    return (
        <div className="productListItem">
            <p className="productId">ID: {editableProduct.id}</p>
            <div className="productImageWrapper">
                {isEditing.img_url ? (
                    <input
                        type="text"
                        value={editableProduct.img_url}
                        onChange={(e) => handleInputChange('img_url', e.target.value)}
                        onBlur={() => handleBlur('img_url')}
                        onKeyDown={(e) => handleKeyDown(e, 'img_url')}
                        className="editableProductField"
                        autoFocus
                    />
                ) : (
                    <img
                        src={editableProduct.img_url}
                        alt={editableProduct.name}
                        onDoubleClick={() => handleDoubleClick('img_url')}
                        className="productImage"
                    />
                )}
            </div>
            {isEditing.name ? (
                <input
                    type="text"
                    value={editableProduct.name}
                    onChange={(e) => handleInputChange('name', e.target.value)}
                    onBlur={() => handleBlur('name')}
                    onKeyDown={(e) => handleKeyDown(e, 'name')}
                    className="editableProductField"
                    autoFocus
                />
            ) : (
                <p onDoubleClick={() => handleDoubleClick('name')} className="productName">
                    Name: {editableProduct.name}
                </p>
            )}
            {isEditing.description ? (
                <textarea
                    value={editableProduct.description}
                    onChange={(e) => handleInputChange('description', e.target.value)}
                    onBlur={() => handleBlur('description')}
                    onKeyDown={(e) => handleKeyDown(e, 'description')}
                    className="editableProductField"
                    autoFocus
                />
            ) : (
                <p onDoubleClick={() => handleDoubleClick('description')} className="productDescription">
                    Description: {editableProduct.description}
                </p>
            )}
            {isEditing.price ? (
                <input
                    type="text"
                    value={editableProduct.price}
                    onChange={(e) => handleInputChange('price', e.target.value)}
                    onBlur={() => handleBlur('price')}
                    onKeyDown={(e) => handleKeyDown(e, 'price')}
                    className="editableProductField"
                    autoFocus
                />
            ) : (
                <p onDoubleClick={() => handleDoubleClick('price')} className="productPrice">
                    Price: ${editableProduct.price}
                </p>
            )}
            {isEditing.stock ? (
                <input
                    type="number"
                    min="0"
                    value={editableProduct.stock}
                    onChange={(e) => handleInputChange('stock', e.target.value)}
                    onBlur={() => handleBlur('stock')}
                    onKeyDown={(e) => handleKeyDown(e, 'stock')}
                    className="editableProductField"
                    autoFocus
                />
            ) : (
                <p onDoubleClick={() => handleDoubleClick('stock')} className="productStock">
                    Stock: {editableProduct.stock}
                </p>
            )}
            {isEditing.merchantName ? (
                <input
                    type="text"
                    value={editableProduct.merchantName}
                    onChange={(e) => handleInputChange('', e.target.value)}
                    onBlur={() => handleBlur('merchantName')}
                    onKeyDown={(e) => handleKeyDown(e, 'merchantName')}
                    className="editableProductField"
                    autoFocus
                />
            ) : (
                <p onDoubleClick={() => handleDoubleClick('merchantName')} className="productMerchantName">
                    Merchant: {editableProduct.merchantName}
                </p>
            )}
        </div>
    );
};

export default ProductListItem;