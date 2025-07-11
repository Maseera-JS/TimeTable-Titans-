import React, { useState } from "react";

const AssignBatch = ({ refreshCalendar }) => {
  const [formData, setFormData] = useState({
    batchCode: "",
    domainTrainer: "",
    softskillTrainer: "",
    program: "",
    batchStartDate: "",
    batchEndDate: "",
    classStartTime: "",
    classEndTime: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    try {
      const response = await fetch("http://localhost:3030/batches/assignNewBatch/", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("✅ Batch assigned successfully!");

        // 🔄 Refresh the calendar if function is provided
        if (typeof refreshCalendar === "function") {
          refreshCalendar();
        }

        setFormData({
          batchCode: "",
          domainTrainer: "",
          softskillTrainer: "",
          program: "",
          batchStartDate: "",
          batchEndDate: "",
          classStartTime: "",
          classEndTime: "",
        });
      } else {
        alert("❌ Failed to assign batch");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("❌ Something went wrong");
    }
  };

  const styles = {
    container: {
      maxWidth: "2000px",
      margin: "40px auto",
      padding: "100px",
      backgroundColor: "#ffffff",
      borderRadius: "12px",
      boxShadow: "0 4px 20px rgba(0,0,0,0.1)",
      fontFamily: "Segoe UI, sans-serif",
    },
    heading: {
      textAlign: "center",
      marginBottom: "30px",
      fontSize: "26px",
      fontWeight: "bold",
      color: "#333",
    },
    formRow: {
      display: "flex",
      gap: "50px",
      marginBottom: "20px",
    },
    fieldWrapper: {
      flex: 1,
      display: "flex",
      flexDirection: "column",
      
    },
    label: {
      marginBottom: "6px",
      fontWeight: 600,
      fontSize: "18px",
    },
    input: {
      padding: "10px",
      fontSize: "17px",
      borderRadius: "5px",
      border: "1px solid #ccc",
    },
    button: {
      marginTop: "30px",
      width: "100%",
      padding: "14px",
      fontSize: "19px",
      backgroundColor: "#28a745",
      color: "#fff",
      border: "3px green solid",
      borderRadius: "5px",
      cursor: "pointer",
    },
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Assign New Batch</h2>
      <form onSubmit={handleSubmit}>
        {/* First Row */}
        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Batch Code</label>
            <input
              style={styles.input}
              type="text"
              name="batchCode"
              value={formData.batchCode}
              onChange={handleChange}
              required
            />
          </div>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Domain Trainer ID</label>
            <input
              style={styles.input}
              type="number"
              name="domainTrainer"
              value={formData.domainTrainer}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        {/* Second Row */}
        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Soft Skill Trainer ID</label>
            <input
              style={styles.input}
              type="number"
              name="softskillTrainer"
              value={formData.softskillTrainer}
              onChange={handleChange}
              required
            />
          </div>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Program ID</label>
            <input
              style={styles.input}
              type="number"
              name="program"
              value={formData.program}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        {/* Third Row */}
        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Batch Start Date</label>
            <input
              style={styles.input}
              type="date"
              name="batchStartDate"
              value={formData.batchStartDate}
              onChange={handleChange}
              required
            />
          </div>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Batch End Date</label>
            <input
              style={styles.input}
              type="date"
              name="batchEndDate"
              value={formData.batchEndDate}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        {/* Fourth Row */}
        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Class Start Time</label>
            <input
              style={styles.input}
              type="time"
              name="classStartTime"
              value={formData.classStartTime}
              onChange={handleChange}
              required
            />
          </div>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Class End Time</label>
            <input
              style={styles.input}
              type="time"
              name="classEndTime"
              value={formData.classEndTime}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <button style={styles.button} type="submit">
          Assign Batch
        </button>
      </form>
    </div>
  );
};

export default AssignBatch;
