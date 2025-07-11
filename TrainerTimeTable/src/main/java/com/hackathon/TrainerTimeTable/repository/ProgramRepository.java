package com.hackathon.TrainerTimeTable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.TrainerTimeTable.entities.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Long> {
	
	

}
