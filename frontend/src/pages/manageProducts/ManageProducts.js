import React, {useEffect, useState} from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import './ManageProducts.css';
import { useParams, useHistory } from 'react-router-dom';
import ProductListItem from "../../components/ProductListItem/ProductListItem";
import ProductCreationItem from "../../components/ProductCreationItem/ProductCreationItem";



const ManageProducts = () => {

  const [products, setProducts] = useState([]);
  const [showAddProductForm, setShowAddProductForm] = useState(false);
  const [editingProduct, setEditingProduct] = useState(null);
  const [img_url, setImageUrl] = useState('');
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');
  const [stock, setStock] = useState('');
  const [price, setPrice] = useState('');
  const [merchantName, setMerchantName] = useState('');
  const role = sessionStorage.getItem("user_role");
  const token = sessionStorage.getItem("token");
  const username = sessionStorage.getItem("username");
  console.log("role: ", role)
  console.log("username: ", username)



  const handleAddProductClick = () => {
    setShowAddProductForm(true);
  };

  console.log("user role: ", role)

  const opts = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };



  useEffect(() => {
    const fetchProductsFromDatabase = async () => {
      let url = 'http://localhost:8080/api/product';
      const userRole = sessionStorage.getItem("user_role");


      if (userRole === 'Merchant') {
        const userId = sessionStorage.getItem("user_id");
        url += `?merchantId=${userId}`;
      }

      try {
        const response = await fetch(url, opts);
        const data = await response.json();
        setProducts(data);
        console.log("products:", data);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };

    fetchProductsFromDatabase();

  }, []);

 
 
  // Render
  return (
    <div className="manageProducts">
      <Navbar />
      <div className="manageProductsContent">
        <Sidebar />
        <main className="manageProductsMain">
          <h1>Manage Products</h1>
          <button onClick={handleAddProductClick} className="addProductButton">Add New Product</button>
          {showAddProductForm && <ProductCreationItem userRole={role} username={username} onCancel={() => setShowAddProductForm(false)} />}
          <table className="manageProductsList">
            <thead>
            <tr>

              </tr>
            </thead>
            <tbody>
            {products
                .slice()
                .sort((a, b) => a.id - b.id) // Ürünleri id'ye göre sırala
                .filter(product => role !== 'Merchant' || product.user.user_name === username)
                .map(product => (
                    <tr key={product.id}>
                      <ProductListItem
                          product={product}
                      />
                    </tr>
                ))}
            </tbody>
          </table>
        </main>
      </div>
    </div>
  );
};

export default ManageProducts;