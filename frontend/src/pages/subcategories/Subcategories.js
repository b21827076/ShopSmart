import React, {useEffect, useState} from 'react';
import Navbar from '../../components/Navbar/Navbar';
import Sidebar from '../../components/Sidebar/Sidebar';
import Grid from '../../components/Grid/Grid';
import SubcategoryItem from '../../components/SubcategoryItem/SubcategoryItem';
import { useParams } from 'react-router-dom';
import "./Subcategories.css";

const Subcategories = (props) => {
    const { categoryId } = useParams(); // URL'den categoryId parametresini al
    const [subcategories, setSubcategories] = useState([]); // Alt kategoriler için state
    const token = sessionStorage.getItem("token"); // Token'ı sessionStorage'dan al

    const opts = {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`, // API için yetkilendirme header'ı
        },
    };

    useEffect(() => {
        const fetchSubcategories = async () => {
            try {
                console.log("categoryId:", categoryId);
                await fetch("http://localhost:8080/api/category/" + categoryId, opts)
                    .then((res) => {
                        return res.json();
                    }).then((d) => {
                        console.log("d:",d)
                        setSubcategories(d);
                        return d;
                    })
            } catch (error) {
                console.error("Error fetching subcategories:", error);
            }
        };

        fetchSubcategories();

    }, [categoryId]);

    return (
    <>
      <Navbar setSearched={props.setSearched} setKeyword={props.setKeyword} />
      <div className="subcategoriesContainer">
        <Sidebar />
        <Grid>
          {subcategories.map(subcategory => (
            <SubcategoryItem
              subcategoryId={subcategory.id}
              name={subcategory.title}
              imageUrl={subcategory.imageUrl}
            />
          ))}
        </Grid>
      </div>
    </>
  );
};

export default Subcategories;