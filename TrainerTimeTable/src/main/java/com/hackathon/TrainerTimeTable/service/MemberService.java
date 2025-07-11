package com.hackathon.TrainerTimeTable.service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.entities.User;
import com.hackathon.TrainerTimeTable.repository.MemberRepository;
import com.hackathon.TrainerTimeTable.repository.UserRepository;

@Service
public class MemberService {

	@Autowired MemberRepository memberRepository;
	
	@Autowired UserRepository userRepo;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	public String addMember(Member request) {

		String response = "";
		Member member = new Member();
		User user = new User();
		if (request == null || request.getEmail().isBlank() || request.getEmail().isEmpty()) {
			return "Email is required. Object not saved";
		} else {
			member.setName(request.getName()); 
			member.setPassword(request.getPassword());
			member.setEmail(request.getEmail());
			
			
            member.setUser(user);
            user.setUserName(request.getName());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
		    user.setMember(member);
			memberRepository.save(member);
			return "New member added successfully";
		}
	}
	
	public JSONObject getMemberByEmail(String email) {
		JSONObject response = new JSONObject();
		Optional<Member> existingMember = memberRepository.findByEmail(email);
		if(existingMember == null || existingMember.isEmpty()) {
			response.append("data", JSONObject.NULL);
			response.append("message", "Object not found");
			return response;
		}else {
			Member member = existingMember.get();
			response.append("data", member);
			response.append("message", "Object found");
			return response;
		}
		
	}
	
	public JSONObject getMemberById(long id) {
		JSONObject response = new JSONObject();
		Optional<Member> existingMember = memberRepository.findById(id);
		if(existingMember == null || existingMember.isEmpty()) {
			response.append("data", JSONObject.NULL);
			response.append("message", "Object not found");
			return response;
		}else {
			Member member = existingMember.get();
			response.append("data", member);
			response.append("message", "Object found");
			return response;
		}
	}
	
	public JSONObject updateMemberByEmail(String email, Member request) {
		JSONObject response = new JSONObject();
		Optional<Member> existingMember = memberRepository.findByEmail(email);
		if(existingMember == null || existingMember.isEmpty()) {
			
			response.append("data", JSONObject.NULL);
			response.append("message", "Data not found");
			return response;
		}else {
			Member member = existingMember.get();
			member.setName(request.getName());
			member.setPassword(request.getPassword());
			
			memberRepository.save(member);
			response.append("data", member);
			response.append("message", "Data updated successfully");
			return response;
		}
	}
	
	//for admin
//	public JSONObject deleteMemberByEmail(String email) {
//		
//	}
	
	
}
