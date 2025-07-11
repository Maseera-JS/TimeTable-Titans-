package com.hackathon.TrainerTimeTable.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.TrainerTimeTable.dto.BatchAssignmentDTO;
import com.hackathon.TrainerTimeTable.dto.TrainerScheduleDTO;
import com.hackathon.TrainerTimeTable.entities.BatchAssignment;
import com.hackathon.TrainerTimeTable.entities.Program;
import com.hackathon.TrainerTimeTable.entities.TrainerSchedule;
import com.hackathon.TrainerTimeTable.service.TrainerScheduleService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/trainer")
public class TrainerScheduleController {
	
	@Autowired TrainerScheduleService trainerScheduleService;
	
	@PostMapping("/scheduleBatch")
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<String> scheduleBatch(@RequestBody TrainerScheduleDTO request) {
	    Map<String, Object> response = new HashMap<>();
	    
	    if (request == null) {
	        return ResponseEntity.noContent().build();
	    }

	    String result = trainerScheduleService.scheduleTrainerBatch(request);
	    
	    if (result == null) {
	        response.put("message", "Failed to assign batch");
	        return  ResponseEntity.noContent().build();
	    }

	    return ResponseEntity.ok(result);
	}

	@GetMapping(value ="getBatchDetails/{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBatchDetails(@PathVariable long id){
		JSONObject response = new JSONObject();
		TrainerSchedule obj = trainerScheduleService.getScheduledBatchDetails(id);
		if(obj == null ) {
    		response.put("message", "Batch not Scheduled");
    		return ResponseEntity.ok(obj);
    	}
    	response.put("data", obj);
    	response.put("message", "Batch already scheduled");
          return ResponseEntity.ok(obj);
		
	}
	
    @GetMapping(value= "/getAllScheduledBatch", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllScheduledBatch() {
        Map<String, Object> batches = trainerScheduleService.getAllScheduledBatch() ;
        return ResponseEntity.ok(batches);
    }
    
//    @PutMapping(value = "/editBatchSchedule/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> updateBatch(@PathVariable Long id, @RequestBody TrainerSchedule scheduleDTO) {
//    	
//        Map<String, Object> updatedSchedule = trainerScheduleService.editBatchSchedule(scheduleDTO , id);
//        return ResponseEntity.ok(updatedSchedule.toString());
//    }

    @PutMapping(value = "/editBatchSchedule/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> updateBatch(@PathVariable Long id, 
                                                           @RequestBody TrainerSchedule scheduleDTO) {
        Map<String, Object> updatedSchedule = trainerScheduleService.editBatchSchedule(scheduleDTO , id);
        return ResponseEntity.ok(updatedSchedule);
    }


}
