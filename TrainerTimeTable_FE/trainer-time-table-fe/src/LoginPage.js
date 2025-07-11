import React, { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import './LoginPage.css';
import logo from './assets/anudip-logo.png';
import bgImage from './assets/cmis.jpeg';

const LoginPage = () => {
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:3030/user/authenticate", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userName, password }),
      });

      if (!response.ok) {
        alert("❌ Login failed. Please check your credentials.");
        return;
      }

      const data = await response.json();
      localStorage.setItem("token", data.token);
      localStorage.setItem("role", data.role);

      navigate("/dashboard");
    } catch (err) {
      console.error("🔥 Login error:", err);
      alert("Something went wrong. Try again later.");
    }
  };

  return (
    <div className="login-wrapper">
      <div
        className="blurred-bg"
        style={{ backgroundImage: `url(${bgImage})` }}
      ></div>

      <div className="page-container">
        <div className="form-container">
          <div style={{ marginBottom: "20px" }}>
            <img src={logo} alt="Logo" className="login-logo" style={{ height: "80px", width:"150px" }} />
          </div>

          <form onSubmit={handleLogin}>
            <input
              type="text"
              placeholder="User Name"
              value={userName}
              required
              onChange={(e) => setUserName(e.target.value)}
            />
            <input
              type="password"
              placeholder="Password"
              value={password}
              required
              onChange={(e) => setPassword(e.target.value)}
            />
            <button type="submit">Login</button>

            <div style={{ marginTop: "20px", textAlign: "center" }}>
              <Link to="/forgot-password" style={{ fontSize: "20px", color: "#007bff" }}>
                Forgot Password?
              </Link>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
