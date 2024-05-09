import React, { useState } from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import PInput from '../../components/PInput/PInput';
import './ManageProducts.css';

const ManageProducts = () => {
  // State hooks for managing products
  const [products, setProducts] = useState([
    {
        id: '1', 
        name: 'Test Ürünü', 
        description: 'Test Açıklaması', 
        imageUrl: 'https://via.placeholder.com/150', 
        price: '100'
      },
    {
        id: '2', 
        name: 'Test Ürünü', 
        description: 'Test Açıklaması', 
        imageUrl: 'https://via.placeholder.com/150', 
        price: '100'
      }
  ]); // replace with your actual state
  const [showAddProductForm, setShowAddProductForm] = useState(false);
  const [editingProduct, setEditingProduct] = useState(null);

  // Function to show the add product form
  const handleAddProductClick = () => {
    setShowAddProductForm(true);
  };

  // Function to hide the add product form
  const handleCancelAddProduct = () => {
    setShowAddProductForm(false);
  };

  // Function to handle the deletion of a product
  const handleDelete = (productId) => {
    // Implement product deletion logic
  };

  // Function to show the edit form for a product
  const handleEditClick = (productId) => {
    const productToEdit = products.find(product => product.id === productId);
    setEditingProduct(productToEdit);
  };



  // Function to save the edited product
  const handleSave = (e, productId) => {
    e.preventDefault();
    const updatedProducts = products.map(product => 
      product.id === productId ? editingProduct : product
    );
    setProducts(updatedProducts);
    setEditingProduct(null); // Reset editing product after saving changes
  };

  // Function to cancel editing
  const handleCancelEdit = () => {
    setEditingProduct(null); // Clear the editing product
  };

 
 
  // Render
  return (
    <div className="manageProducts">
      <Navbar />
      <div className="manageProductsContent">
        <Sidebar />
        <main className="manageProductsMain">
          <h1>Ürünleri Yönet</h1>
          <button onClick={handleAddProductClick} className="addProductButton">Yeni Ürün Ekle</button>
          {showAddProductForm && (
            // Form to add a new product
            <form>
              <PInput label="photolink" placeholder="photolink" />
              <PInput label="title" placeholder="title" />
              <PInput label="content" placeholder="content" />
              <PInput label="stock" placeholder="stock" />
              <PInput label="price" placeholder="price" />
              <PInput label="user_name" placeholder="user_name" />
              <PInput label="subcategory" placeholder="subcategory" />
              
              <button type="submit">Kaydet</button>
              <button type="button" onClick={handleCancelAddProduct}>Vazgeç</button>
            </form>
          )}
          <table className="manageProductsList">
            <thead>
              <tr>
                {/* Table headers */}
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th>Subcategory</th>
                <th>Photo</th>
                <th>Stock</th>
                <th>User Name</th>
                <th>Operations</th>
              </tr>
            </thead>
            <tbody>
              {products.map(product => (
                <>
                  {/* Product row */}
                  <tr key={product.id}>
                    <td>{product.name}</td>
                    <td>{product.description}</td>
                    <td>{product.price}</td>
                    <td>{product.subcategory_name}</td>
                    <td><img src={product.imageUrl}/></td>
                    <td>{product.stock}</td>
                    <td>{product.user_name}</td>
                    <td>
                      <button onClick={() => handleEditClick(product.id)}>Duzenle</button>
                      <button onClick={() => handleDelete(product.id)}>Sil</button>
                    </td>
                  </tr>
                  {/* Edit form row */}
                  {editingProduct && editingProduct.id === product.id && (
                    <tr>
                      <td colSpan="5">
                        <form onSubmit={(e) => handleSave(e, editingProduct.id)}>
                            <PInput label="photolink" placeholder="photolink" />
                            <PInput label="title" placeholder="title" />
                            <PInput label="content" placeholder="content" />
                            <PInput label="stock" placeholder="stock" />
                            <PInput label="price" placeholder="price" />
                            <PInput label="user_name" placeholder="user_name" />
                            <PInput label="subcategory" placeholder="subcategory" />


                            <button type="submit">Kaydet</button>
                            <button type="button" onClick={handleCancelEdit}>Vazgeç</button>
                        </form>
                      </td>
                    </tr>
                  )}
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