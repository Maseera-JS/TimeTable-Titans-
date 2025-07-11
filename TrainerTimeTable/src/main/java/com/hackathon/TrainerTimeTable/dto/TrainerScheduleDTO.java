package com.hackathon.TrainerTimeTable.dto;

import java.time.LocalDate;
import java.util.List;

public class TrainerScheduleDTO {

	    private String batchCode;
	    private Long batchId;
	    private Long trainerId;
	    private String subModule;
	    private String classLink;
	    private LocalDate startDate;
	    public LocalDate actualBatchStartDate;
	    public LocalDate actualBatchEndDate;
	    private List<String> days;
	    
	    
		public Long getBatchId() {
			return batchId;
		}
		public void setBatchId(Long batchId) {
			this.batchId = batchId;
		}
		public void setBatchCode(String batchCode) {
			this.batchCode = batchCode;
		}
		public String getBatchCode() {
			return batchCode;
		}
		public void setBatchId(String batchCode) {
			this.batchCode = batchCode;
		}
		public Long getTrainerId() {
			return trainerId;
		}
		public void setTrainerId(Long trainerId) {
			this.trainerId = trainerId;
		}
		public String getSubModule() {
			return subModule;
		}
		public void setSubModule(String subModule) {
			this.subModule = subModule;
		}
		public String getClassLink() {
			return classLink;
		}
		public void setClassLink(String classLink) {
			this.classLink = classLink;
		}
		public LocalDate getStartDate() {
			return startDate;
		}
		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}
		public List<String> getDays() {
			return days;
		}
		public void setDays(List<String> days) {
			this.days = days;
		}
		
		
		public LocalDate getActualBatchStartDate() {
			return actualBatchStartDate;
		}
		public void setActualBatchStartDate(LocalDate actualBatchStartDate) {
			this.actualBatchStartDate = actualBatchStartDate;
		}
		public LocalDate getActualBatchEndDate() {
			return actualBatchEndDate;
		}
		public void setActualBatchEndDate(LocalDate actualBatchEndDate) {
			this.actualBatchEndDate = actualBatchEndDate;
		}
		public TrainerScheduleDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
}
