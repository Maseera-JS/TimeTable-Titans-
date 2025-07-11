package com.hackathon.TrainerTimeTable.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.TrainerTimeTable.dto.BatchAssignmentDTO;
import com.hackathon.TrainerTimeTable.entities.BatchAssignment;

@Repository
public interface BatchAssignmentRepository extends JpaRepository<BatchAssignment,Long> {

//	List<BatchAssignmentDTO> findAll();
	BatchAssignment findByBatchCode(String batchCode);
}
