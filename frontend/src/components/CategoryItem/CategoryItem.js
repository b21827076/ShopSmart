import React from 'react';
import { useHistory } from 'react-router-dom';
import './CategoryItem.css';

const CategoryItem = ({ categoryId, name, imageUrl }) => {
  const history = useHistory();

  const navigateToSubcategory = () => {
    history.push(`/category/${categoryId}`);
  };

  return (
    <div className="categoryItem" onClick={navigateToSubcategory}>
    {/*<img src={imageUrl} alt={name} className="categoryItemImage" />*/}
      <div className="categoryItemName">{name}</div>
    </div>
  );
} 

export default CategoryItem;