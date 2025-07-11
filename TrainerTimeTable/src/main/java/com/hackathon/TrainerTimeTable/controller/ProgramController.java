package com.hackathon.TrainerTimeTable.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.TrainerTimeTable.entities.Program;
import com.hackathon.TrainerTimeTable.entities.Program;
import com.hackathon.TrainerTimeTable.service.ProgramService;

@RestController
@RequestMapping("/admin")
public class ProgramController {

	@Autowired
	ProgramService programService;

	@PostMapping("addProgram")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> addProgram(@RequestBody Program request) {
		if (request == null) {
			return ResponseEntity.noContent().build();
		} else {
			String response = programService.addProgram(request);
			return ResponseEntity.ok(response);
		}
	}

	@GetMapping("/getProgramById/{id}")
	public ResponseEntity<Map<String, Object>> getProgramById(@PathVariable long id) {
		Map<String, Object> response = new HashMap<>();
		JSONObject data = programService.getProgramById(id);
		Object member = data.get("data");
		if (member == null) {
			response.put("data", null);
			response.put("message", "Program not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}

		response.put("data", member);
		response.put("message", "Program is still active");
		return ResponseEntity.ok(response);
	}

	@PutMapping("/updateProgram/{id}")
	public ResponseEntity<Map<String, Object>> updateMember(@PathVariable long id, @RequestBody Program request) {
	    Map<String, Object> response = new HashMap<>();

	    JSONObject data = programService.updateProgramDetails(id, request);
	    
	    Object member = data.opt("object");  // use opt() to avoid exception if key is missing

	    if (member == null || member.equals(JSONObject.NULL)) {
	        response.put("data", null);
	        response.put("message", "Program not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    Map<String, Object> dataMap = data.toMap();

	    response.put("data", dataMap);
	    response.put("message", "Program updated in the system successfully");

	    return ResponseEntity.ok(response);
	}

}
