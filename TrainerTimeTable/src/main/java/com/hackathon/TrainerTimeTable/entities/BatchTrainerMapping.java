package com.hackathon.TrainerTimeTable.entities;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name="Batch_Trainer_Mapping")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class BatchTrainerMapping {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

//	    @ManyToOne
//	    @JoinColumn(name = "batch_id")
//	    private BatchAssignment batch;
//
//	    @ManyToOne
//	    @JoinColumn(name = "trainer_id")
//	    private Member trainer;
	    
	    
}
