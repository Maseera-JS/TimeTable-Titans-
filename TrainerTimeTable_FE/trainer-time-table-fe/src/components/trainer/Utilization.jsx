import React, { useEffect, useState } from "react";

const Utilization = () => {
  const [batches, setBatches] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchBatches = async () => {
      const token = localStorage.getItem("token");

      try {
        const response = await fetch("http://localhost:3030/trainer/getAllScheduledBatch", {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        if (!response.ok) throw new Error("Failed to fetch");

        const data = await response.json();
        console.log("✅ API response:", data);

        // ✅ Correct structure: data.data should be an array
        if (Array.isArray(data.data)) {
          setBatches(data.data);
        } else {
          console.warn("⚠️ Unexpected response structure");
          setBatches([]);
        }
      } catch (error) {
        console.error("❌ Error fetching batches:", error);
        alert("❌ Failed to fetch utilization data");
        setBatches([]); // fallback to empty
      } finally {
        setLoading(false);
      }
    };

    fetchBatches();
  }, []);

  const styles = {
    wrapper: {
      padding: "30px 40px",
      backgroundColor: "#fff",
      minHeight: "100vh",
      fontFamily: "Segoe UI, sans-serif",
    },
    heading: {
      fontSize: "24px",
      marginBottom: "20px",
      fontWeight: "600",
      color: "#333",
    },
    table: {
      width: "100%",
      borderCollapse: "collapse",
      marginBottom: "20px",
    },
    th: {
      backgroundColor: "#28a745",
      color: "#fff",
      padding: "10px",
      border: "1px solid #ddd",
      fontSize: "14px",
    },
    td: {
      padding: "10px",
      fontSize: "13px",
      border: "1px solid #ddd",
    },
    row: {
      backgroundColor: "#f9f9f9",
    },
  };

  return (
    <div style={styles.wrapper}>
      <h2 style={styles.heading}>Utilization Report</h2>

      {loading ? (
        <p>Loading...</p>
      ) : (
        Array.isArray(batches) && batches.length > 0 ? (
          <table style={styles.table}>
            <thead>
              <tr>
                <th style={styles.th}>Schedule ID</th>
                <th style={styles.th}>Sub Module</th>
                <th style={styles.th}>Class Link</th>
                <th style={styles.th}>Start Date</th>
                <th style={styles.th}>Days</th>
                <th style={styles.th}>Created On</th>
              </tr>
            </thead>
            <tbody>
              {batches.map((batch, index) => (
                <tr key={index} style={styles.row}>
                  <td style={styles.td}>{batch.scheduleId}</td>
                  <td style={styles.td}>{batch.subModule}</td>
                  <td style={styles.td}>
                    <a href={batch.classLink} target="_blank" rel="noopener noreferrer">
                      Join Link
                    </a>
                  </td>
                  <td style={styles.td}>{batch.startDate}</td>
                  <td style={styles.td}>{batch.days?.join(", ")}</td>
                  <td style={styles.td}>{batch.createdOn}</td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p>No scheduled batches found.</p>
        )
      )}
    </div>
  );
};

export default Utilization;
