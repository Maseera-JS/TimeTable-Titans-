package com.hackathon.TrainerTimeTable.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
@Table(name = "trainer_schedule")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TrainerSchedule {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long scheduleId;

	    @ManyToOne
	    @JoinColumn(name = "batch_id")
	    @JsonIgnore
	    private BatchAssignment batch;

	    @ManyToOne
	    @JoinColumn(name = "trainer_id")
	    @JsonIgnore
	    private Member trainer;

	    @Column(name = "sub_module", nullable = false)
	    private String subModule;

	    @Column(name = "class_link")
	    private String classLink;

	    @Column(name = "start_date", nullable = false)
	    private LocalDate startDate;

	    @ElementCollection
	    @CollectionTable(name = "schedule_days", joinColumns = @JoinColumn(name = "schedule_id"))
	    @Column(name = "day")
	    private List<String> days;

	    @Column(name = "created_on")
	    private LocalDate createdOn = LocalDate.now();
	    
	    

}
