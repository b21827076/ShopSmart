import React from 'react';
import { useHistory } from 'react-router-dom';
import './SubcategoryItem.css';


const SubcategoryItem = ({ categoryId, subcategoryId, name, imageUrl }) => {
  const history = useHistory();

  const navigateToProducts = () => {
    // Bu fonksiyon kullanıcıyı ilgili alt kategorideki ürünlerin listelendiği sayfaya yönlendirir.
    history.push(`/category/${categoryId}/subcategory/${subcategoryId}`);
  };

  return (
    <div className="subcategoryItem" onClick={navigateToProducts}>
      <img src={imageUrl} alt={name} className="subcategoryItemImage" />
      <div className="subcategoryItemName">{name}</div>
    </div>
  );
}

export default SubcategoryItem