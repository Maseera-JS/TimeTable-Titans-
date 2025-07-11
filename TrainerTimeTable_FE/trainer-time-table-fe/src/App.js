
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import LoginPage from "./LoginPage";
import ForgotPassword from "./ForgotPassword";
import RegisterPage from "./RegisterPage";
import Dashboard from "./Dashboard";
import AssignBatch from './components/admin/AssignBatch';
import BatchDetails from "./components/admin/BatchDetails";
import Reports from "./components/admin/Reports";
import ScheduleBatch from "./components/trainer/ScheduleBatch";
import EditBatch from "./components/trainer/EditBatchSchedule";
import Utilization from "./components/trainer/Utilization";



function App() {
  return (
    <>
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/forgot-password" element={<ForgotPassword />} />

        {/* <Route path="/register" element={<RegisterPage />} /> */}
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/assign-batch" element={<AssignBatch />} />


         
        <Route path="/assign-batch" element={<AssignBatch />} />
        <Route path="/batch-details" element={<BatchDetails />} />
        <Route path="/reports" element={<Reports />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/register" element={<RegisterPage />} />

        <Route path="/trainer/schedule-batch" element={<ScheduleBatch />} />
        <Route path="/trainer/edit-batch" element={<EditBatch />} />
        <Route path="/trainer/utilization" element={<Utilization />} />

      </Routes>
    </Router>
     {/* <div>
      <TrainerSession />
    </div> */}
    </>
  );
}

export default App;
