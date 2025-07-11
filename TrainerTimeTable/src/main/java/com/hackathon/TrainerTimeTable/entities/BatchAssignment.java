package com.hackathon.TrainerTimeTable.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Batch_Assignment")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class BatchAssignment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long assignmentId;
	
	@Column(name="batch_code")
	private String batchCode;
	
	@Column(name="batch_start_date")
	private LocalDate batchStartDate;
	
	@Column(name="batch_end_date")
	private LocalDate batchEndDate;
	
	@Nullable
	@Column(name="actual_batch_end_date")
	private LocalDate actualBatchEndDate;
	
	@Nullable
	@Column(name="actual_batch_start_date")
	private LocalDate actualBatchStartDate;
	
	@Column(name="class_start_time")
	private LocalTime classStartTime;
	
	@Column(name="class_end_time")
	private LocalTime classEndTime;
	
	
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;
    
	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "domain_trainer_id")
    private Member domainTrainer;

	@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "softskill_trainer_id")
    private Member softskillTrainer;
    
    
	
	
	
	
	
	
	
	
	
	
}
