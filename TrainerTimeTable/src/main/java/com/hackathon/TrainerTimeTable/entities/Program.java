package com.hackathon.TrainerTimeTable.entities;

import java.time.LocalDate;

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
@Table(name="Courses")
@Setter @Getter  @NoArgsConstructor @AllArgsConstructor
public class Program {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long programId;
	
	@Column(name="programe_name")
	private String programName;
	
	@Column(name="start_date")
	private LocalDate startDate;
	
	@Column(name="end_date")
	private LocalDate endDate;
	
	@Column(name="created_at")
	private LocalDate createdAt;
	
	@Column(name="min_age")
	private int minAge;
	
	@Column(name="max_age")
	private int maxAge;
	
	@Column(name="exam_mode")
	private String examMode;
	
	@Column(name="price")
	private int price;
	
	@Column(name="delivery_mode")
	private String deliveryMode;
	
	@Column(name="total_duration")
	private int durationInDays; 
	
	@Column(name="category")
	 private String category;   // i.e SS or domain 
	

}
