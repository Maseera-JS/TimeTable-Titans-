// src/components/admin/Reports.jsx
import React, { useState, useEffect } from "react";

const Reports = () => {
  const [batches, setBatches] = useState([]);

  const styles = {
    wrapper: { padding: "30px", fontFamily: "Segoe UI, sans-serif" },
    button: {
      padding: "12px 20px",
      backgroundColor: "#28a745",
      color: "white",
      fontSize: "16px",
      border: "none",
      borderRadius: "5px",
      cursor: "pointer",
      marginBottom: "20px",
    },
    table: {
      width: "100%",
      borderCollapse: "collapse",
    },
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

  useEffect(() => {
    const fetchData = async () => {
      const token = localStorage.getItem("token");
      try {
        const response = await fetch("http://localhost:3030/batches/getAllBatches", {
          headers: { Authorization: `Bearer ${token}` },
        });
        const data = await response.json();
        setBatches(data);
      } catch (error) {
        console.error("Error fetching batches:", error);
      }
    };

    fetchData();
  }, []);

  const downloadCSV = () => {
    const csvContent =
      "data:text/csv;charset=utf-8," +
      [
        [
          "Batch Code",
          "Program",
          "Domain Trainer",
          "Soft Skill Trainer",
          "Start Date",
          "End Date",
          "Start Time",
          "End Time",
        ],
        ...batches.map((b) => [
          b.batchCode,
          b.program?.programName || "N/A",
          b.domainTrainer || "N/A",
          b.softskillTrainer || "N/A",
          b.batchStartDate,
          b.batchEndDate,
          b.classStartTime,
          b.classEndTime,
        ]),
      ]
        .map((e) => e.join(","))
        .join("\n");

    const encodedUri = encodeURI(csvContent);
    const link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "all_batches_report.csv");
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  return (
    <div style={styles.wrapper}>
      <button style={styles.button} onClick={downloadCSV}>
        Download Report
      </button>

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
          {batches.map((batch, index) => (
            <tr key={index}>
              <td style={styles.td}>{batch.batchCode}</td>
              <td style={styles.td}>{batch.program?.programName || "N/A"}</td>
              <td style={styles.td}>{batch.domainTrainer || "N/A"}</td>
              <td style={styles.td}>{batch.softskillTrainer || "N/A"}</td>
              <td style={styles.td}>{batch.batchStartDate}</td>
              <td style={styles.td}>{batch.batchEndDate}</td>
              <td style={styles.td}>{batch.classStartTime}</td>
              <td style={styles.td}>{batch.classEndTime}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Reports;
