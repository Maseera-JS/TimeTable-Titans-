package com.hackathon.TrainerTimeTable.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.TrainerTimeTable.entities.Program;
import com.hackathon.TrainerTimeTable.entities.User;
import com.hackathon.TrainerTimeTable.repository.ProgramRepository;

@Service
public class ProgramService {

	
	@Autowired ProgramRepository programRepo;
	
	public String addProgram(Program request) {
		Program program = new Program();
		if (request == null || request.getProgramName() =="" ) {
			return "Program details are missing!, pls check.";
		} else {
			program.setProgramName(request.getProgramName());
			program.setCategory(request.getCategory()); 
			program.setCreatedAt(LocalDate.now());
			program.setDeliveryMode(request.getDeliveryMode());
			program.setDurationInDays(request.getDurationInDays());
			program.setStartDate(request.getStartDate());	
			program.setEndDate(request.getEndDate());
			program.setExamMode(request.getExamMode());
			program.setMaxAge(request.getMaxAge());
			program.setMinAge(request.getMinAge());
			program.setPrice(request.getPrice());
				
			
			programRepo.save(program);
			return "New program added successfully";
		}
	}
	
	public JSONObject getProgramById(long programId) {
		JSONObject response = new JSONObject();
		Optional<Program> existingProgram = programRepo.findById(programId);
		if(existingProgram == null || existingProgram.isEmpty()) {
			response.append("data", JSONObject.NULL);
			response.append("message", "Program not found");
			return response;
		}else {
			Program member = existingProgram.get();
			response.append("data", member);
			response.append("message", "Program found");
			return response;
		}
	}
	
	public JSONObject getAllPrograms() {
		JSONObject response = new JSONObject();
		List<Program> programList = programRepo.findAll();
		if(programList == null || programList.isEmpty()) {
			response.append("data", JSONObject.NULL);
			response.append("message", "Object not found");
			return response;
		}else {
			response.append("data", programList);
			response.append("message", "Object found");
			return response;
		}
	}
	
	public JSONObject updateProgramDetails(long id, Program request) {
	    JSONObject response = new JSONObject();
	    Optional<Program> existingProgram = programRepo.findById(id); 

	    if (existingProgram.isEmpty()) {
	        response.append("data", JSONObject.NULL);
	        response.append("message", "Data not found");
	        return response;
	    }

	    Program program = existingProgram.get();
	    program.setProgramName(request.getProgramName());
	    program.setCategory(request.getCategory());
	    program.setDeliveryMode(request.getDeliveryMode());
	    program.setDurationInDays(request.getDurationInDays());
	    program.setStartDate(request.getStartDate());
	    program.setEndDate(request.getEndDate());
	    program.setExamMode(request.getExamMode());
	    program.setMaxAge(request.getMaxAge());
	    program.setMinAge(request.getMinAge());
	    program.setPrice(request.getPrice());

	    programRepo.save(program);

	    response.append("object", program);
//	    response.append("message", "Data updated successfully");
	    return response;
	}

	
	
	
}
