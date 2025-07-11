import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import "react-calendar/dist/Calendar.css";
import "./TrainerCalendar.css";

const TrainerCalendar = () => {
  const [date, setDate] = useState(new Date());
  const [scheduledBatches, setScheduledBatches] = useState([]);
  const role = localStorage.getItem("role");

  useEffect(() => {
    const fetchScheduledBatches = async () => {
      const token = localStorage.getItem("token");
      try {
        const response = await fetch("http://localhost:3030/trainer/getAllScheduledBatch", {
          headers: { Authorization: `Bearer ${token}` },
        });
        const result = await response.json();
        if (result.data) setScheduledBatches(result.data);
      } catch (error) {
        console.error("Failed to fetch scheduled batches:", error);
      }
    };

    fetchScheduledBatches();
  }, []);

  const tileContent = ({ date, view }) => {
    if (view !== "month") return null;

    const today = new Date();
    const dayName = date.toLocaleDateString("en-US", { weekday: "long" });

    const matchingBatches = scheduledBatches.filter((batch) => {
      const startDate = new Date(batch.startDate);
      const batchDays = batch.days || [];

      return startDate <= date && batchDays.includes(dayName);
    });

    const isToday = date.toDateString() === today.toDateString();
    const dayDiff = Math.floor((date - today) / (1000 * 60 * 60 * 24));
    const isUpcoming = dayDiff > 0 && dayDiff <= 3;

    if (matchingBatches.length === 0) return null;

    return (
      <div className="tooltip-wrapper">
        <div
          className={`dot ${
            isToday ? "dot-today" : isUpcoming ? "dot-upcoming" : ""
          }`}
        ></div>
        <div className="tooltip-text">
          {matchingBatches.map((b, idx) => (
            <div key={idx}>
              {role === "ADMIN"
                ? `ID: ${b.scheduleId}`
                : `ID: ${b.scheduleId} - ${b.subModule}`}
            </div>
          ))}
        </div>
      </div>
    );
  };

  return (
    <div className="calendar-container">
      {/* 🟢 Legend placed ABOVE the calendar */}
      <div className="calendar-legend">
        <span className="legend-item">
          <span className="dot dot-today"></span> Today
        </span>
        <span className="legend-item">
          <span className="dot dot-upcoming"></span> Upcoming (next 3 days)
        </span>
      </div>

      <Calendar
        onChange={setDate}
        value={date}
        tileContent={tileContent}
        prevLabel="←"
        nextLabel="→"
        showNavigation={true}
      />
    </div>
  );
};

export default TrainerCalendar;
