package com.hackathon.TrainerTimeTable.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDate;

public class BatchAssignmentDTO {

	    public String batchCode;
	    public Long domainTrainer;
	    public Long softskillTrainer;
	    public Long program;
	    public LocalDate batchStartDate;
	    public LocalDate batchEndDate;
	    public LocalTime classStartTime;
	    public LocalTime classEndTime;
		public String getBatchCode() {
			return batchCode;
		}
		public void setBatchCode(String batchCode) {
			this.batchCode = batchCode;
		}
		public Long getDomainTrainer() {
			return domainTrainer;
		}
		public void setDomainTrainer(Long domainTrainer) {
			this.domainTrainer = domainTrainer;
		}
		public Long getSoftskillTrainer() {
			return softskillTrainer;
		}
		public void setSoftskillTrainer(Long softskillTrainer) {
			this.softskillTrainer = softskillTrainer;
		}
		public Long getProgram() {
			return program;
		}
		public void setProgram(Long program) {
			this.program = program;
		}
		public LocalDate getBatchStartDate() {
			return batchStartDate;
		}
		public void setBatchStartDate(LocalDate batchStartDate) {
			this.batchStartDate = batchStartDate;
		}
		public LocalDate getBatchEndDate() {
			return batchEndDate;
		}
		public void setBatchEndDate(LocalDate batchEndDate) {
			this.batchEndDate = batchEndDate;
		}
		public LocalTime getClassStartTime() {
			return classStartTime;
		}
		public void setClassStartTime(LocalTime classStartTime) {
			this.classStartTime = classStartTime;
		}
		public LocalTime getClassEndTime() {
			return classEndTime;
		}
		public void setClassEndTime(LocalTime classEndTime) {
			this.classEndTime = classEndTime;
		}
		public BatchAssignmentDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
