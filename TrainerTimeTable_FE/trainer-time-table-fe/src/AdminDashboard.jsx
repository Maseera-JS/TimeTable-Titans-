import React, { useState, useRef } from "react";
import AssignBatch from "./components/admin/AssignBatch";
import BatchDetails from "./components/admin/BatchDetails";
import UserList from "./components/admin/UserList";
import RegisterUser from "./components/admin/RegisterUser";
import TrainerCalendar from "./components/trainer/TrainerCalendar"; // ✅ Adjust path if needed

const AdminDashboard = () => {
  const [activeComponent, setActiveComponent] = useState("assign");
  const calendarRef = useRef(); // ✅ calendar ref

  const renderComponent = () => {
    switch (activeComponent) {
      case "assign":
        return <AssignBatch refreshCalendar={() => calendarRef.current?.refreshCalendar()} />;
      case "batch":
        return <BatchDetails />;
      case "users":
        return <UserList />;
      case "register":
        return <RegisterUser />;
      default:
        return <AssignBatch refreshCalendar={() => calendarRef.current?.refreshCalendar()} />;
    }
  };

  return (
    <div style={{ background: "#fff", minHeight: "100vh" }}>
      <div style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between",
        borderBottom: "2px solid lightgreen",
        padding: "10px 20px",
        position: "sticky",
        top: 0,
        zIndex: 1000,
      }}>
        <div>
          <div
            style={{ cursor: "pointer", fontSize: "24px", fontWeight: "bold" }}
            onClick={() => setActiveComponent("assign")}
          >
            ☰
          </div>
        </div>
        <div style={{ fontWeight: "bold", fontSize: "22px" }}>ANUDIP - CMIS</div>
        <div style={{ display: "flex", gap: "10px" }}>
          <button style={navButtonStyle} onClick={() => setActiveComponent("assign")}>Assign Batch</button>
          <button style={navButtonStyle} onClick={() => setActiveComponent("batch")}>Batch Details</button>
          <button style={navButtonStyle} onClick={() => setActiveComponent("users")}>User List</button>
          <button style={navButtonStyle} onClick={() => setActiveComponent("register")}>Register User</button>
        </div>
      </div>

      <div style={{ padding: "20px" }}>
        {/* ✅ Calendar remains always visible */}
        <TrainerCalendar ref={calendarRef} />
        {/* ✅ Component changes based on button click */}
        {renderComponent()}
      </div>
    </div>
  );
};

const navButtonStyle = {
  backgroundColor: "#90ee90",
  color: "black",
  border: "1px solid green",
  padding: "5px 10px",
  cursor: "pointer",
  fontWeight: "bold",
  borderRadius: "5px",
};

export default AdminDashboard;
