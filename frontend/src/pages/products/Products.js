import React, { useEffect, useState } from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import Grid from '../../components/Grid/Grid';
import ProductItem from '../../components/ProductItem/ProductItem';
import Like from '../../components/Like/Like';
import { useParams } from 'react-router-dom';
import "./Products.css";

// socialPlatform-main/frontend/src/pages/products/Products.js
const Products = (props) => {
    const { categoryId, subcategoryId } = useParams(); // URL'den categoryId ve subcategoryId parametrelerini al
    const [products, setProducts] = useState([]); // Ürünler için state
    const token = sessionStorage.getItem("token"); // Token'ı sessionStorage'dan al

    const opts = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // API için yetkilendirme header'ı
        },
    };

    useEffect(() => {
        const fetchProducts = async () => {
            try {
                console.log("categoryId:", categoryId);
                console.log("subcategoryId:", subcategoryId);
                const response = await fetch(`http://localhost:8080/api/category/${categoryId}/subcategory/${subcategoryId}`, opts);
                const data = await response.json();
                console.log("Products list:", data);
                setProducts(data);
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        };

        fetchProducts();

    }, [categoryId, subcategoryId]);

  return (
    <>
      <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword} />
      <div className="productsContainer">
        <Sidebar />
        <Grid>
          {products.map(product => (
            <ProductItem
              key={product.id}
              productId={product.id}
              name={product.productName}
              imageUrl={product.imageUrl}
              description={product.description}
              likeCount={product.likeCount}
              commentCount={product.commentCount}
            />
          ))}
        </Grid>
      </div>
    </>
  );
}

export default Products;
