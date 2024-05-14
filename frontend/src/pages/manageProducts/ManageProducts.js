import React, {useEffect, useState} from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import './ManageProducts.css';
import { useParams, useHistory } from 'react-router-dom';
import ProductListItem from "../../components/ProductListItem/ProductListItem";
import ProductCreationItem from "../../components/ProductCreationItem/ProductCreationItem";



const ManageProducts = () => {
  // State hooks for managing products
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


  // Function to show the add product form
  const handleAddProductClick = () => {
    setShowAddProductForm(true);
  };









  console.log("user role: ", role)




  // Function to handle saving the edited product
  const handleSaveEdit = async (e, productId) => {
    e.preventDefault();

    // Assuming you have state hooks for each input field
    const updatedProduct = {
      id: productId,
      img_url, // from state
      name, // from state
      description, // from state
      price, // from state
      stock, // from state
    };

    const opts = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        // Include other headers such as Authorization if needed
      },
      body: JSON.stringify(updatedProduct),
    };

    try {
      const response = await fetch(`http://localhost:8080/api/product/${productId}`, opts);
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      const data = await response.json();
      console.log('Updated product:', data);

      // You might want to refresh the list of products or update the state
      setProducts(products.map(product => (product.id === productId ? data : product)));
      setEditingProduct(null); // Hide the edit form after successful update
    } catch (error) {
      console.error('Error updating product:', error);
    }
  };

  const opts = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`, // API için yetkilendirme header'ı
    },
  };



  useEffect(() => {
    const fetchProductsFromDatabase = async () => {
      let url = 'http://localhost:8080/api/product';
      const userRole = sessionStorage.getItem("role"); // Or however you retrieve the user role

      // If the user is a merchant, modify the URL to fetch only their products
      if (userRole === 'merchant') {
        const userId = sessionStorage.getItem("user_id"); // Assuming you store user ID in session storage
        url += `?merchantId=${userId}`; // Adjust the URL according to your API's requirements
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

              {products.map(product => (
                  <>
                    <tr key = {product.id}>
                      <ProductListItem
                          product={product}

                          //onProductUpdate={handleProductUpdate}
                      />
                    </tr>


                  </>
              ))}
            </tbody>
          </table>
        </main>
      </div>
    </div>
  );
};

export default ManageProducts;