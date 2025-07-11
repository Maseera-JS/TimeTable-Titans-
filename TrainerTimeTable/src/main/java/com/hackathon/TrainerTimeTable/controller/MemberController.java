package com.hackathon.TrainerTimeTable.controller;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.service.MemberService;

@RestController
public class MemberController {
	
	@Autowired MemberService memberService;
	
	@PostMapping("/user/addMember")
	public ResponseEntity<String> addNewMember(@RequestBody Member request){
		if(request == null) {
			return ResponseEntity.noContent().build();
		}else {
			String response =  memberService.addMember(request);
			return ResponseEntity.ok(response);
		}
	}
	
	@GetMapping("/getMemberByEmail/{email}")
	public ResponseEntity<Map<String, Object>> getMemberByEmail(@PathVariable String email) {
	    Map<String, Object> response = new HashMap<>();
	    JSONObject data = memberService.getMemberByEmail(email);
	    Object member = data.get("data");
	    if (member == null) {
	        response.put("data", null);
	        response.put("message", "Member not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    response.put("data", member);
	    response.put("message", "Member available in system");
	    return ResponseEntity.ok(response);
	}

	
	@GetMapping(value="/getMember/{id}" , produces = "application/json")
	public ResponseEntity<Map<String, Object>> getMemberById(@PathVariable long id) {
	    Map<String, Object> response = new HashMap<>();
	    JSONObject data = memberService.getMemberById(id);
	    Object member = data.get("data");
	    if (member == null) {
	        response.put("data", null);
	        response.put("message", "Member not found");
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    response.put("data", member);
	    response.put("message", "Member available in system");
	    return ResponseEntity.ok(response);
	}
	
	@PutMapping("/updateMember/{email}")
	public ResponseEntity<Map<String, Object>> updateMember(@PathVariable String email ,@RequestBody Member request ){
		  Map<String, Object> response = new HashMap<>();
		    JSONObject data = memberService.getMemberByEmail(email);
		    Object member = data.get("data");
		    if (member == null) {
		        response.put("data", null);
		        response.put("message", "Member not found");
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		    }

		    JSONObject res = memberService.updateMemberByEmail(email, request);
		    
		    response.put("data", res.get("data"));
		    response.put("message", "Member available in system");
		    return ResponseEntity.ok(response);
	}
	
}
