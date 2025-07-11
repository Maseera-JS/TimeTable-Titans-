package com.hackathon.TrainerTimeTable.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.TrainerTimeTable.dto.BatchAssignmentDTO;
import com.hackathon.TrainerTimeTable.entities.BatchAssignment;
import com.hackathon.TrainerTimeTable.service.BatchAssignmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/batches")
public class BatchAssignmentController {
	
	   @Autowired
	    private BatchAssignmentService batchService;

	 
	   @PreAuthorize("hasRole('ADMIN')")
	   @PostMapping(value = "assignNewBatch/", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> assignBatch(@RequestBody BatchAssignmentDTO batch) {
	    	JSONObject response = new JSONObject();
	    	try {
	    	if(batch == null || batch.getBatchCode().equals("")) {
	    		response.put("message", "Incomplete details!, pls assign properly");
	    		return ResponseEntity.ok(response.toString());
	    	}
	    	  JSONObject created = batchService.createBatchAssignment(batch);
	    	  //System.out.println(created.get("data"));
	          response.put("data", created.get("batch"));
	          response.put("message", "Batch assigned successfully");
	    	
	          
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	return ResponseEntity.ok(response.toString());
	    }


	    @GetMapping(value = "getBatchByBatchCode/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> getBatchById(@PathVariable String id) {
	    	JSONObject response = new JSONObject();
	    	 BatchAssignment batch = batchService.getBatchAssignmentById(id);
		    	if(batch == null ) {
		    		response.put("message", "Batch not found");
		    		return ResponseEntity.ok(batch);
		    	}
		    	response.put("data", batch);
		    	response.put("message", "Batch found");
		          return ResponseEntity.ok(batch);
	    }


	    @GetMapping(value= "/getAllBatches", produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<?> getAllBatches() {
	        List<BatchAssignment> batches = batchService.getAllBatchAssignments();
	        return ResponseEntity.ok(batches);
	    }

	
	    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<Map<String, Object>> updateBatch(@PathVariable Long id, @RequestBody BatchAssignmentDTO batch) {
	    	
	    	Map<String, Object> updated = batchService.updateBatchAssignment(id, batch);
	        if(updated == null) {
	        	return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(updated);
	    }

	
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteBatch(@PathVariable Long id) {
	        batchService.deleteBatchAssignment(id);
	        return ResponseEntity.ok("Batch assignment deleted successfully.");
	    }

}
