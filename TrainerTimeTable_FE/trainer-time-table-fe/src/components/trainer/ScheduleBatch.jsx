import React, { useState } from "react";

const ScheduleBatch = () => {
  const [formData, setFormData] = useState({
    batchId: "",
    trainerId: "",
    subModule: "",
    classLink: "",
    startDate: "",
    days: [],
  });

  const [dropdownOpen, setDropdownOpen] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const toggleDay = (day) => {
    const updatedDays = formData.days.includes(day)
      ? formData.days.filter((d) => d !== day)
      : [...formData.days, day];

    setFormData({ ...formData, days: updatedDays });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");

    try {
      const response = await fetch("http://localhost:3030/trainer/scheduleBatch", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        alert("✅ Batch scheduled successfully!");
        setFormData({
          batchId: "",
          trainerId: "",
          subModule: "",
          classLink: "",
          startDate: "",
          days: [],
        });
        setDropdownOpen(false);
      } else {
        alert("❌ Failed to schedule batch");
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
      padding: "40px",
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
      position: "relative",
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
    dropdownToggle: {
      padding: "10px",
      fontSize: "14px",
      borderRadius: "5px",
      border: "1px solid #ccc",
      backgroundColor: "#fff",
      cursor: "pointer",
    },
    dropdown: {
      position: "absolute",
      top: "100%",
      left: 0,
      width: "100%",
      backgroundColor: "#fff",
      border: "1px solid #ccc",
      borderRadius: "5px",
      padding: "10px",
      zIndex: 1000,
      boxShadow: "0 2px 8px rgba(0,0,0,0.15)",
      maxHeight: "200px",
      overflowY: "auto",
    },
    checkboxRow: {
      display: "flex",
      alignItems: "center",
      gap: "5px",
      padding: "6px",
      borderRadius: "5px",
      cursor: "pointer",
      transition: "background 0.3s",
    },
    checkboxInput: {
      width: "16px",
      height: "16px",
      cursor: "pointer",
    },
    button: {
      marginTop: "30px",
      width: "100%",
      padding: "14px",
      fontSize: "18px",
      backgroundColor: "#28a745",
      color: "#fff",
      border: "none",
      borderRadius: "5px",
      cursor: "pointer",
    },
  };

  const daysOfWeek = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

  return (
    <div style={styles.container}>
      <h2 style={styles.heading}>Schedule New Batch</h2>
      <form onSubmit={handleSubmit}>
        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Batch ID</label>
            <input
              style={styles.input}
              type="number"
              name="batchId"
              value={formData.batchId}
              onChange={handleChange}
              required
            />
          </div>

          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Trainer ID</label>
            <input
              style={styles.input}
              type="number"
              name="trainerId"
              value={formData.trainerId}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Sub Module</label>
            <input
              style={styles.input}
              type="text"
              name="subModule"
              value={formData.subModule}
              onChange={handleChange}
              required
            />
          </div>

          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Class Link</label>
            <input
              style={styles.input}
              type="text"
              name="classLink"
              value={formData.classLink}
              onChange={handleChange}
              required
            />
          </div>
        </div>

        <div style={styles.formRow}>
          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Start Date</label>
            <input
              style={styles.input}
              type="date"
              name="startDate"
              value={formData.startDate}
              onChange={handleChange}
              required
            />
          </div>

          <div style={styles.fieldWrapper}>
            <label style={styles.label}>Class Days</label>
            <div
              style={styles.dropdownToggle}
              onClick={() => setDropdownOpen(!dropdownOpen)}
            >
              {formData.days.length > 0
                ? formData.days.join(", ")
                : "Select Days"}
            </div>

            {dropdownOpen && (
              <div style={styles.dropdown}>
                {daysOfWeek.map((day) => (
                  <label key={day} style={styles.checkboxRow}>
                    <input
                      style={styles.checkboxInput}
                      type="checkbox"
                      value={day}
                      checked={formData.days.includes(day)}
                      onChange={() => toggleDay(day)}
                    />
                    {day}
                  </label>
                ))}
              </div>
            )}
          </div>
        </div>

        <button style={styles.button} type="submit">
          Schedule Batch
        </button>
      </form>
    </div>
  );
};

export default ScheduleBatch;
