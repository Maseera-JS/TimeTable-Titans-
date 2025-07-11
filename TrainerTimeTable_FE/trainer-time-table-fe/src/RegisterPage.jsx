import React, { useState } from 'react';
import './LoginPage.css'; // reuse existing form styles
import logo from './assets/anudip-logo.png'; // ✅ make sure logo path is correct

const RegisterPage = () => {
  const [formData, setFormData] = useState({
    userName: '',
    password: '',
    role: 'ROLE_TRAINER',
    email: '',
    contact: '',
    baseCentre: '',
  });

  const [image, setImage] = useState(null);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleImageChange = (e) => {
    setImage(e.target.files[0]);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const form = new FormData();
    form.append(
      'user',
      new Blob([JSON.stringify(formData)], { type: 'application/json' })
    );
    form.append('image', image);

    try {
      const response = await fetch('http://localhost:3030/user/register', {
        method: 'POST',
        body: form,
      });

      if (response.ok) {
        alert('✅ Registration successful!');
      } else {
        const msg = await response.text();
        alert('❌ Registration failed: ' + msg);
      }
    } catch (error) {
      console.error('Registration error:', error);
      alert('❌ Something went wrong.');
    }
  };

  return (
    <div className="page-container">
      <div className="form-container">
        <div style={{ textAlign: 'center', marginBottom: '20px' }}>
          <img
            src={logo}
            alt="Anudip Logo"
            style={{ height: '80px' }}
          />
        </div>
        <h2 style={{ marginBottom: '20px' }}>Register</h2>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="userName"
            placeholder="User Name"
            required
            onChange={handleChange}
          />
          <input
            type="text"
            name="role"
            placeholder="User Role"
            required
            onChange={handleChange}
          />
          <input
            type="password"
            name="password"
            placeholder="Password"
            required
            onChange={handleChange}
          />
          <input
            type="email"
            name="email"
            placeholder="Email"
            required
            onChange={handleChange}
          />
          <input
            type="text"
            name="contact"
            placeholder="Contact"
            required
            onChange={handleChange}
          />
          <input
            type="text"
            name="baseCentre"
            placeholder="Base Centre"
            required
            onChange={handleChange}
          />
          <input
            type="file"
            accept="image/*"
            required
            onChange={handleImageChange}
          />
          <button type="submit">Register</button>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
