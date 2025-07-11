package com.hackathon.TrainerTimeTable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.TrainerTimeTable.entities.TrainerSchedule;

@Repository
public interface TrainerScheduleRepository extends JpaRepository<TrainerSchedule, Long>{

//	Optional<TrainerSchedule> findByBatchCode(String batchCode);
}
