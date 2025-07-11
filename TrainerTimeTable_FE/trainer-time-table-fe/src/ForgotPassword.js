import React, { useState } from "react";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleReset = async (e) => {
    e.preventDefault();
    if (!email || !newPassword) {
      setMessage("Please enter both email and new password.");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:3030/user/forgotPassword?email=${encodeURIComponent(email)}&newPassword=${encodeURIComponent(newPassword)}`,
        {
          method: "POST",
        }
      );

      if (response.ok) {
        const result = await response.text(); // because backend returns a plain string
        setMessage(result);
        setEmail("");
        setNewPassword("");
      } else {
        setMessage("Failed to reset password. Try again.");
      }
    } catch (error) {
      console.error("Error:", error);
      setMessage("Something went wrong.");
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.title}>Forgot Password</h2>
      <form onSubmit={handleReset} style={styles.form}>
        <input
          style={styles.input}
          type="email"
          placeholder="Enter your email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />
        <input
          style={styles.input}
          type="password"
          placeholder="Enter new password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          required
        />
        <button style={styles.button} type="submit">Reset Password</button>
      </form>
      {message && <p style={styles.message}>{message}</p>}
    </div>
  );
};

const styles = {
  container: {
    maxWidth: "2000px",
    margin: "60px auto",
    padding: "50px",
    border: "1px solid green",
    borderRadius: "10px",
    textAlign: "center",
    fontFamily: "Segoe UI, sans-serif",
    background: "#f9f9f9",
  },
  title: {
    marginBottom: "20px",
    fontSize: "30px",
    fontWeight: "bold",
  },
  form: {
    display: "flex",
    flexDirection: "column",
    gap: "20px",
  },
  input: {
    padding: "10px",
    fontSize: "18px",
    borderRadius: "5px",
    border: "3px solid #ccc",
  },
  button: {
    padding: "10px",
    fontSize: "20px",
    backgroundColor: "#007bff",
    color: "#fff",
    border: "none",
    borderRadius: "5px",
    cursor: "pointer",
  },
  message: {
    marginTop: "20px",
    fontWeight: "bold",
    color: "#333",
    fontSize: "20px",
  },
};

export default ForgotPassword;
