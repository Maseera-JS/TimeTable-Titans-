// 

// src/components/admin/BatchDetails.jsx
import React, { useState } from "react";

const BatchDetails = () => {
  const [batchId, setBatchId] = useState("");
  const [batch, setBatch] = useState(null);
  const [loading, setLoading] = useState(false);

  const styles = {
    wrapper: { padding: "30px", fontFamily: "Segoe UI, sans-serif" },
    inputRow: { marginBottom: "20px" },
    input: {
      padding: "10px",
      marginRight: "10px",
      fontSize: "18px",
      width: "400px",
    },
    button: {
      padding: "10px 16px",
      fontSize: "18px",
      backgroundColor: "#007bff",
      color: "white",
      border: "none",
      borderRadius: "5px",
      cursor: "pointer",
    },
    table: { width: "100%", borderCollapse: "collapse", marginTop: "20px" },
    th: {
      backgroundColor: "#28a745",
      color: "white",
      padding: "10px",
      textAlign: "left",
      border: "1px solid #ccc",
    },
    td: {
      padding: "10px",
      border: "1px solid #ccc",
    },
  };

  const fetchBatchById = async () => {
    if (!batchId) return alert("Please enter a batch ID");

    setLoading(true);
    const token = localStorage.getItem("token");

    try {
      const response = await fetch(
        `http://localhost:3030/batches/getBatchByBatchCode/${batchId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      const data = await response.json();
      setBatch(data);
    } catch (error) {
      alert("Batch not found.");
      setBatch(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={styles.wrapper}>
      <div style={styles.inputRow}>
        <input
          type="text"
          placeholder="Enter Batch ID"
          value={batchId}
          onChange={(e) => setBatchId(e.target.value)}
          style={styles.input}
        />
        <button style={styles.button} onClick={fetchBatchById}>
          Fetch Batch
        </button>
      </div>

      {loading ? (
        <p>Loading...</p>
      ) : batch ? (
        <table style={styles.table}>
          <thead>
            <tr>
              <th style={styles.th}>Batch Code</th>
              <th style={styles.th}>Program</th>
              <th style={styles.th}>Domain Trainer</th>
              <th style={styles.th}>Soft Skill Trainer</th>
              <th style={styles.th}>Start Date</th>
              <th style={styles.th}>End Date</th>
              <th style={styles.th}>Start Time</th>
              <th style={styles.th}>End Time</th>
            </tr>
          </thead>
          <tbody>
  <tr>
    <td style={styles.td}>{batch.batchCode}</td>
    <td style={styles.td}>{batch.program?.programName || "N/A"}</td>
    <td style={styles.td}>{batch.domainTrainer || "N/A"}</td>
    <td style={styles.td}>{batch.softskillTrainer || "N/A"}</td>
    <td style={styles.td}>{batch.batchStartDate}</td>
    <td style={styles.td}>{batch.batchEndDate}</td>
    <td style={styles.td}>{batch.classStartTime}</td>
    <td style={styles.td}>{batch.classEndTime}</td>
  </tr>
</tbody>

        </table>
      ) : null}
    </div>
  );
};

export default BatchDetails;
