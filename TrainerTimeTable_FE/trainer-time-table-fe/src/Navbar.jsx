import React, { useState, useEffect } from "react";
import { FaBars } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const Navbar = ({ role: propRole, handleLogout }) => {
  const [menuOpen, setMenuOpen] = useState(false);
  const [showBatches, setShowBatches] = useState(false);
  const [role, setRole] = useState(propRole || "");
  const navigate = useNavigate();

  // Fallback to localStorage role if not passed via props
  useEffect(() => {
    if (!propRole) {
      const storedRole = localStorage.getItem("role");
      setRole((storedRole || "").toUpperCase());
    } else {
      setRole(propRole.toUpperCase());
    }
  }, [propRole]);

  const styles = {
    navbar: {
      display: "flex",
      justifyContent: "space-between",
      alignItems: "center",
      padding: "10px 65px",
      backgroundColor: "#27a745",
      color: "white",
      border: "3px solid green",
      position: "sticky",
      top: 0,
      zIndex: 150,
    },
    title: {
      position: "absolute",
      left: "50%",
      transform: "translateX(-50%)",
      fontSize: "35px",
      fontWeight: "bold",
      color: "white",
    },
    
    hamburger: {
      display: "flex",
      flexDirection: "column",
      justifyContent: "space-between",
      height: "20px",
      cursor: "pointer",
      padding: "10px",
      left: "70px",
    },
    dropdown: {
      position: "absolute",
      top: "60px",
      left: "50px",
      backgroundColor: "white",
      border: "1px solid black",
      padding: "10px",
      borderRadius: "5px",
      zIndex: 999,
      maxHeight: "400px",
      overflowY: "auto", // scroll support
    },
    menuButton: {
      display: "block",
      margin: "5px 0",
      backgroundColor: "#27a745",
      color: "white",
      border: "1px solid black",
      padding: "8px 15px",
      cursor: "pointer",
      width: "180px",
      textAlign: "left",
    },
    logoutButton: {
      backgroundColor: "#27a745",
      border: "1px solid black",
      color: "white",
      padding: "10px 15px",
      cursor: "pointer",
      width: "100%",
      marginTop: "10px",
    },
    subDropdown: {
      marginLeft: "10px",
      marginTop: "5px",
      paddingLeft: "10px",
      borderLeft: "2px solid #ccc",
    },
  };

  return (
    <div style={styles.navbar}>
      <div style={styles.hamburger} onClick={() => setMenuOpen(!menuOpen)}>
        <FaBars size={30} />
      </div>

      <div style={styles.title}>ANUDIP - CMIS</div>

      {menuOpen && (
        <div style={styles.dropdown}>
          {role === "TRAINER" && (
            <>
              <button
                style={styles.menuButton}
                onClick={() => navigate("/trainer/schedule-batch")}
              >
                Schedule New Batch
              </button>
              <button
                style={styles.menuButton}
                onClick={() => navigate("/trainer/edit-batch")}
              >
                Edit Scheduled Batch
              </button>
              <button
                style={styles.menuButton}
                onClick={() => navigate("/trainer/utilization")}
              >
                Trainer Utilization Report
              </button>
            </>
          )}

          {role === "ADMIN" && (
            <>
              <button
                style={styles.menuButton}
                onClick={() => setShowBatches(!showBatches)}
              >
                Batches ▾
              </button>

              {showBatches && (
                <div style={styles.subDropdown}>
                  <button
                    style={styles.menuButton}
                    onClick={() => navigate("/assign-batch")}
                  >
                    Assign Batch
                  </button>
                  <button
                    style={styles.menuButton}
                    onClick={() => navigate("/batch-details")}
                  >
                    View Batch Details
                  </button>
                </div>
              )}

              <button
                style={styles.menuButton}
                onClick={() => navigate("/reports")}
              >
                Reports
              </button>
              <button
                style={styles.menuButton}
                onClick={() => navigate("/register")}
              >
                Register User
              </button>
            </>
          )}

          <button
            style={styles.logoutButton}
            onClick={() => {
              localStorage.clear();
              navigate("/");
            }}
          >
            Logout
          </button>
        </div>
      )}
    </div>
  );
};

export default Navbar;
