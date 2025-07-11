package com.hackathon.TrainerTimeTable.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.TrainerTimeTable.dto.BatchAssignmentDTO;
import com.hackathon.TrainerTimeTable.entities.BatchAssignment;
import com.hackathon.TrainerTimeTable.entities.BatchTrainerMapping;
import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.entities.Program;
import com.hackathon.TrainerTimeTable.repository.BatchAssignmentRepository;
import com.hackathon.TrainerTimeTable.repository.MemberRepository;
import com.hackathon.TrainerTimeTable.repository.ProgramRepository;

@Service
public class BatchAssignmentService {

	  @Autowired
	    private BatchAssignmentRepository batchRepo;
	  
	  @Autowired
	    private MemberRepository memberRepo;
	  
	  @Autowired
	    private ProgramRepository programRepo;

	   
	  public JSONObject createBatchAssignment(BatchAssignmentDTO batch) {
		  JSONObject response =  new JSONObject();
		    if (batch.getDomainTrainer() == null || batch.getDomainTrainer() == null) {
		        throw new IllegalArgumentException("Domain Trainer or ID is null");
		    }

		    if (batch.getSoftskillTrainer() == null || batch.getSoftskillTrainer() == null) {
		        throw new IllegalArgumentException("Softskill Trainer or ID is null");
		    }

		    if (batch.getProgram() == null || batch.getProgram() == 0) {
		        throw new IllegalArgumentException("Program or ID is null");
		    }

		    // Fetch from DB with proper checks
		    Member domain = memberRepo.findById(batch.getDomainTrainer())
		        .orElseThrow(() -> new RuntimeException("Domain trainer not found"));

		    Member softSkill = memberRepo.findById(batch.getSoftskillTrainer())
		        .orElseThrow(() -> new RuntimeException("Softskill trainer not found"));

		    Program prog = programRepo.findById(batch.getProgram())
		        .orElseThrow(() -> new RuntimeException("Program not found"));

		    // Create new batch entity and set values
		    BatchAssignment newBatch = new BatchAssignment();
		    newBatch.setBatchCode(batch.getBatchCode());
		    newBatch.setBatchStartDate(batch.getBatchStartDate());
		    newBatch.setBatchEndDate(batch.getBatchEndDate());
		    newBatch.setClassStartTime(batch.getClassStartTime());
		    newBatch.setClassEndTime(batch.getClassEndTime());

		    newBatch.setDomainTrainer(domain);
		    newBatch.setSoftskillTrainer(softSkill);
		    newBatch.setProgram(prog);

		    // Save and return
		    batchRepo.save(newBatch);
		    response.put("batch", newBatch);
		    return response;
		}



	   
	    public BatchAssignment getBatchAssignmentById(String id) {
	    	BatchAssignment batch = batchRepo.findByBatchCode(id);
	    	return batch;
	    	}
	    	
	    	
	    

	   
	    public List<BatchAssignment> getAllBatchAssignments() {
	    	List<BatchAssignment> list = batchRepo.findAll();
	        return list;
	    }

	   
	    public Map<String, Object> updateBatchAssignment(Long id, BatchAssignmentDTO batch) {
	    	Map<String, Object> response = new HashMap<String, Object>();
	        Optional<BatchAssignment> optionalBatch = batchRepo.findById(id);
	        
	        Optional<Member> domainTrainer = memberRepo.findById(batch.getDomainTrainer());
	        
	        Optional<Member> softSkillTrainer = memberRepo.findById(batch.getSoftskillTrainer());

	        if (optionalBatch.isEmpty() || domainTrainer.isEmpty() || softSkillTrainer.isEmpty())  {
	           response.put("message","Batch assignment not found with id" +id);
	           return response;
	        }

	        BatchAssignment existing = optionalBatch.get();
	        Member domainT = domainTrainer.get();
	        Member ssTrainer = softSkillTrainer.get();

	        if (batch.getBatchStartDate() != null) {
	            existing.setBatchStartDate(batch.getBatchStartDate());
	        }

	        if (batch.getBatchEndDate() != null) {
	            existing.setBatchEndDate(batch.getBatchEndDate());
	        }

	        if (batch.getClassStartTime() != null) {
	            existing.setClassStartTime(batch.getClassStartTime());
	        }

	        if (batch.getClassEndTime() != null) {
	            existing.setClassEndTime(batch.getClassEndTime());
	        }

	        if (batch.getDomainTrainer() != null) {
	            existing.setDomainTrainer(domainT);;
	        }

	        if (batch.getSoftskillTrainer() != null) {
	            existing.setSoftskillTrainer(ssTrainer);
	        }

	        batchRepo.save(existing);
	        response.put("data", existing);
	        response.put("message", "Batch updated sucessfully");
	        return response;
	    }

	   
	    public void deleteBatchAssignment(Long id) {
	        if (!batchRepo.existsById(id)) {
	            throw new RuntimeException("Batch assignment not found with id: " + id);
	        }
	        batchRepo.deleteById(id);
	    }
	
	
}
