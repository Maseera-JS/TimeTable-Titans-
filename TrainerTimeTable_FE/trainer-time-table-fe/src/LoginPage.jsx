// // LoginPage.jsx
// import React, { useState } from "react";
// import { useNavigate } from "react-router-dom";

// const LoginPage = () => {
//   const [userName, setUserName] = useState("");
//   const [password, setPassword] = useState("");
//   const navigate = useNavigate();

//   const handleLogin = async (e) => {
//     e.preventDefault();
//     console.log("🚀 Login clicked");

//     try {
//       const response = await fetch("http://localhost:3030/user/authenticate", {
//         method: "POST",
//         headers: { "Content-Type": "application/json" },
//         body: JSON.stringify({ userName, password }),
//       });

//       console.log("📥 Got response:", response);

//       if (!response.ok) {
//         alert("Login failed");
//         return;
//       }

//       const data = await response.json();
//       console.log("✅ Login Data:", data);

//       localStorage.setItem("token", data.token);
//       localStorage.setItem("role", data.role);

//       console.log("🌐 Stored role:", localStorage.getItem("role"));

//       navigate("/dashboard");
//     } catch (err) {
//       console.error("🔥 Login error:", err);
//     }
//   };
//    console.log("🎯 LoginPage rendering...");

//   return (
//     <div>
//       <h2>Login Page</h2>
//       <form onSubmit={handleLogin}>
//         <input
//           type="text"
//           placeholder="Username"
//           value={userName}
//           onChange={(e) => setUserName(e.target.value)}
//           required
//         />
//         <br />
//         <input
//           type="password"
//           placeholder="Password"
//           value={password}
//           onChange={(e) => setPassword(e.target.value)}
//           required
//         />
//         <br />
//         <button type="submit">Login</button>
//       </form>
//     </div>
//   );
// };

// export default LoginPage;
