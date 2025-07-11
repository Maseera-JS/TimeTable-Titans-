// TrainerDashboard.jsx
import React from "react";
import Navbar from "./Navbar"; // adjust path as needed
import { useNavigate } from "react-router-dom";

const TrainerDashboard = () => {

 
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/"); // 👈 goes back to login page
  };

  const role = localStorage.getItem("role");
  return (
    <div style={styles.container}>
      <h2>Trainer Dashboard</h2>
      <ul>
        <li>📅 View and manage batch calendar</li>
        <li>✅ Mark session as completed</li>
        <li>📝 Upload notes or feedback</li>
      </ul>
    </div>
  );
};

const styles = {
  container: {
    border: "2px solid #4caf50",
    borderRadius: "10px",
    padding: "20px",
    marginTop: "20px",
    backgroundColor: "#f0fff4",
  },
};

export default TrainerDashboard;
