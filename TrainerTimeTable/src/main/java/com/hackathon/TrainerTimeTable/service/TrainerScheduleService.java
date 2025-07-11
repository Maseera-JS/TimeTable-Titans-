package com.hackathon.TrainerTimeTable.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.TrainerTimeTable.dto.TrainerScheduleDTO;
import com.hackathon.TrainerTimeTable.entities.BatchAssignment;
import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.entities.TrainerSchedule;
import com.hackathon.TrainerTimeTable.repository.BatchAssignmentRepository;
import com.hackathon.TrainerTimeTable.repository.MemberRepository;
import com.hackathon.TrainerTimeTable.repository.TrainerScheduleRepository;

@Service
public class TrainerScheduleService {
	
	@Autowired TrainerScheduleRepository trainerScheduleRepo;
	
	@Autowired BatchAssignmentRepository batchAssignmentRepo;
	
	@Autowired MemberRepository memberRepo;
	
	public String scheduleTrainerBatch(TrainerScheduleDTO request) {
		Map<String, Object> response = new HashMap<>();
		 try {
		        
		        BatchAssignment batch = batchAssignmentRepo.findById(request.getBatchId())
		                .orElseThrow(() -> new RuntimeException("Batch not found"));

		        
		        Member trainer = memberRepo.findById(request.getTrainerId())
		                .orElseThrow(() -> new RuntimeException("Trainer not found"));

		      
		        TrainerSchedule schedule = new TrainerSchedule();
		        schedule.setBatch(batch);
		        schedule.setTrainer(trainer);
		        schedule.setSubModule(request.getSubModule());
		        schedule.setClassLink(request.getClassLink());
		        schedule.setStartDate(request.getStartDate());
		        schedule.setDays(request.getDays());
		        schedule.setCreatedOn(LocalDate.now());
		        batch.setActualBatchStartDate(request.getActualBatchStartDate());
		        batch.setActualBatchEndDate(request.getActualBatchEndDate());
		        
		        
		        batchAssignmentRepo.save(batch);
		        TrainerSchedule saved = trainerScheduleRepo.save(schedule);

		        response.put("message", "Batch scheduled successfully");
		        response.put("data", saved);

		    } catch (Exception e) {
		        response.put("error", "Failed to schedule batch: " + e.getMessage());
		    }
		return "Batch scheduled successfully";
	}
	
     public TrainerSchedule getScheduledBatchDetails(long scheduleId) {
    	 Optional<TrainerSchedule> scheduledBatch = trainerScheduleRepo.findById(scheduleId);
    	 TrainerSchedule scheduled = scheduledBatch.get();
 		 return scheduled ;
		
	}
     
     public Map<String, Object> getAllScheduledBatch() {
    	 Map<String, Object> response = new HashMap<String, Object>();
    	 List<TrainerSchedule> allBatches = trainerScheduleRepo.findAll();
    	 if(allBatches.isEmpty()) {
    		 response.put("data", allBatches);
    		 response.put("message", "Batches not available");
    		 return response;
    	 }
    	 
    	 response.put("data", allBatches);
    	 response.put("message", "Batch list available");
		 
 		 return response;
		
	}
     
     public Map<String, Object> editBatchSchedule(TrainerSchedule request , long scheduleId) {
    	 Map<String, Object> response = new HashMap<String, Object>();
    	 Optional<TrainerSchedule> schedule = trainerScheduleRepo.findById(scheduleId);
    	 if(schedule.isEmpty()) {
    		 response.put("data", schedule);
    		 response.put("message", "Cannot edit batch");
    		 return response;
    	 }
    	 TrainerSchedule editBatch = schedule.get();
    	 
    	 editBatch.setBatch(request.getBatch());
    	 editBatch.setClassLink(request.getClassLink());
    	 editBatch.setCreatedOn(LocalDate.now());
    	 editBatch.setDays(request.getDays());
    	 editBatch.setStartDate(request.getStartDate());
    	 editBatch.setSubModule(request.getSubModule());
    	 editBatch.setTrainer(request.getTrainer());
    	 
    	 trainerScheduleRepo.save(editBatch);
    	 response.put("data", editBatch);
    	 response.put("message", "batch rescheduled successfully");
 		return response;
 		
 	 }

}
