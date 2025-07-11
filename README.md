# 🗓️ TimeTable Titans

## 1. Project Title & Description

**TimeTable Titans**  
A full-stack trainer scheduling and batch management system designed for training batch operations. Admin can create user, can view batches and download the reports in csv file.Trainers can schedule sessions after the Admin assign them the batches, edit the batch schedule , check the utilization.

---

## 2. Team Details

- **Team Name:** TimeTable Titans  
- **Members:**
  - Maseera Shaikh (maseera.jamalshaikh@anudip.org)   Team Leader
  -  Harshit Singh
  -  Shivanshu Singh
  -  Reshma Shaikh (Center Manager)

---

## 3. Tech Stack

**Frontend:**
- React.js
- HTML, CSS 

**Backend:**
- Spring Boot
- Java
- RESTful APIs

**Database:**
- MySQL

**Tools:**
- Postman (API testing)
- Git & GitHub 

---

## 4. Project Description

This system was created to simplify the batch scheduling process for training institutions. It allows:

- Trainers to schedule sessions (start date, days, submodules, links).
- Edit existing batch schedules.
- View all sessions or sessions assigned to a specific trainer.
- Store batch and trainer info in a relational database.

The backend is built with Spring Boot, using JPA for database operations. The frontend is built in React with a simple, intuitive UI.

---

## 5. Setup Instructions
For Backend:
Open the Backend project in Eclipse IDE -> check for the DB configuration -> create a new mysql database and add it in the application.properties file (src->main->resources): 
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

For Frontend :
open the project folder in from directory using cmd
run the following cmd :
npm install 
npm install react-router-dom
npm install react-icons
npm install axios
npm install react-datepicker
npm install @fullcalendar/react @fullcalendar/daygrid @fullcalendar/timegrid @fullcalendar/interaction

npm start


### 🧩 Backend (Spring Boot)

1. Clone the repository:
   ```bash
   git clone https://github.com/Maseera-JS/TimeTable-Titans-.git
