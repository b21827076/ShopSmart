import React from 'react';
import { useHistory } from 'react-router-dom';
import './ProductItem.css';

const ProductItem = ({ productId, name, imageUrl, description, likeCount }) => {
  const history = useHistory();

  const navigateToProductDetail = () => {
    history.push(`/products/${productId}`);
  };

  

  return (
    <div className="productItem" onClick={navigateToProductDetail}>
      {<img src={imageUrl} alt={name} className="productItemImage" />}
      <div className="productItemName">{name}</div>
        {/*<div className="productItemLikeCount">Likes: {likeCount}</div>*/}
    </div>
  );
};

export default ProductItem;