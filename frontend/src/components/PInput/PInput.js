// PInput.js
import React from 'react';
import './PInput.css'; // PInput için oluşturduğunuz CSS dosyasını import edin

const PInput = ({ label, name, id, type, value, placeholder, onChange, explanation }) => {
  return (
    <div className="p-input-container">
      {label && (
        <label htmlFor={id} className="p-input-label">
          {label}
        </label>
      )}
      <input
        type={type}
        name={name}
        id={id}
        value={value}
        placeholder={placeholder}
        onChange={onChange}
        className="p-input-field"
      />
      {explanation && <span className="p-input-explanation">{explanation}</span>}
    </div>
  );
};

export default PInput;