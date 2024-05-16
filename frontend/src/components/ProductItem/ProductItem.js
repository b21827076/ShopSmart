import React from 'react';
import { useHistory } from 'react-router-dom';
import './ProductItem.css';
import CommentIcon from '@mui/icons-material/Comment';
import FavoriteIcon from '@mui/icons-material/Favorite';

const ProductItem = ({ productId, name, imageUrl, description, likeCount, commentCount}) => {
  const history = useHistory();

  const navigateToProductDetail = () => {
    history.push(`/products/${productId}`);
  };

  

  return (
    <div>
      <div className="productItem" onClick={navigateToProductDetail}>
        <img src={imageUrl} alt={name} className="productItemImage" />
        <div>{name}</div>
        <div>{description}</div>
      </div>
      <div className="likeCount">
        <FavoriteIcon/>
        Likes: {likeCount}</div>
      <div className="commentCount">
      <CommentIcon/>
        Comments: {commentCount}</div>
    </div>
  );
};

export default ProductItem;