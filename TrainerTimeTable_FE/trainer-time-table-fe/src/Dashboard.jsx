// Dashboard.jsx
import React, { useEffect, useState } from "react";
import Navbar from "./Navbar";
import TrainerCalendar from "./TrainerCalendar";

const Dashboard = () => {
  const [role, setRole] = useState("");

  useEffect(() => {
    const storedRole = localStorage.getItem("role");
    setRole(storedRole?.toUpperCase());
  }, []);

  if (!role) return <p>Loading...</p>;

  return (
    <div className="dashboard-wrapper">
    <div className="dashboard-container">
      <Navbar role={role} />
      <div style={{ marginTop: "10px", padding: "0 10px" }}>
        <TrainerCalendar />
      </div>
    </div>
   </div> 
  );
};

export default Dashboard;
