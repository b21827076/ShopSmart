import React, { useEffect, useState } from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import RatingStars from '../../components/RatingStars/RatingStars';
import './ProductPage.css'; // Stil dosyasını eklediğinizden emin olun
import { useParams } from 'react-router-dom';
import Comment from '../../components/Comment/Comment';
import CommentInput from '../../components/CommentInput/CommentInput';

const ProductPage = (props) => {
  const { productId } = useParams(); // URL'den productId parametresini al
  const [productDetails, setProductDetails] = useState(null); // Ürün detayları için state
  const token = sessionStorage.getItem("token"); // Token'ı sessionStorage'dan al
  const username = sessionStorage.getItem("username");
  const [userId, setUserId] = useState();
  console.log("username: ", username);
  console.log("userId: ", userId);




  const opts = {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`, // API için yetkilendirme header'ı
    },
  };

  useEffect(() => {
    fetch(`http://localhost:8080/api/profile/${username.toString()}`, opts)
        .then((res) => {
          console.log("res: ",res);
          return res.json();
        })
        .then((data) => {
          setUserId(data.user.id);
          console.log("data: ", data);
          return data;
        })
        .catch((error) => {
          console.error("There's an error", error);
        });
  }, [username]);



  useEffect(() => {
    const fetchProductDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/product/${productId}`, opts);
        const data = await response.json();
        console.log("Product data:", data);
        setProductDetails(data);
      } catch (error) {
        console.error("Error fetching product details:", error);
      }
    };

    if (productId) {
      fetchProductDetails();
    }
  }, [productId]);


  const commentData = {
    username: 'User123',
    timestamp: '1 hour ago',
    text: 'This is a static comment for testing purposes.',
  };

  return (
    <>
      <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword} />
      <div className="productPageContainer">
        <Sidebar />
        <div className="productDetails">
          <h1>{productDetails?.name}</h1>
          <RatingStars />
          <img src={productDetails?.img_url}/>
          <p>{productDetails?.description}</p>
          <p>Price: {productDetails?.price}</p>
          <p>Stock: {productDetails?.stock}</p>
          <p>Merchant: {productDetails?.user.user_name}</p>

          <CommentInput productId={productId} userId={userId} onCommentSubmit={(comment) => console.log(comment)} />
          <Comment
              username={commentData.username}
              timestamp={commentData.timestamp}
              text={commentData.text}
          />
          {/* <Comment /> */}
          {/* Burada diğer ürün detaylarına yer verebilirsiniz */}
        </div>
      </div>
    </>
  );
};

export default ProductPage;