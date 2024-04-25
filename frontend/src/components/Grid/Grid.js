import React from 'react';
import './Grid.css'; // Grid bileşeni için stil dosyasını import edin

const Grid = ({ children }) => {
  return (
    <div className="gridContainer">
      {children}
    </div>
  );
}

export default Grid;