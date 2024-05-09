import React, {useEffect, useState} from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import Grid from '../../components/Grid/Grid';
import CategoryItem from '../../components/CategoryItem/CategoryItem'; // Category bileşenini import edin
import "./Categories.css";
import { useParams, useHistory } from 'react-router-dom';


const Categories = (props) => {

  const [categories, setCategories] = useState([]);

  const token = sessionStorage.getItem("token");

  const opts = {
    method: "GET",
    headers: {
      "Content-type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        await fetch("http://localhost:8080/api/category", opts)
            .then((res) => {
              return res.json();
            }).then((data) => {
              console.log("data:",data);
              console.log("data[0]: ",data[0])
              setCategories(data);
              return data;
            })
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    };

    fetchCategories();
  }, []);

  return (
      <>
        <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword} />
        <div className="categoriesContainer">
          <Sidebar />
          <Grid >
            {categories.map((category) => (
              <CategoryItem
                categoryId={category.id}
                name= {category.title}
                imageUrl={category.imageUrl}
              />
            ))}
          </Grid>

        </div>
      </>
  );
}
export default Categories;