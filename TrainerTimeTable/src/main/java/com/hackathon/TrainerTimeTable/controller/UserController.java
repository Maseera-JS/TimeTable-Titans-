package com.hackathon.TrainerTimeTable.controller;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.hackathon.TrainerTimeTable.dto.AuthRequestDTO;
import com.hackathon.TrainerTimeTable.dto.AuthResponseDTO;
import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.entities.User;
import com.hackathon.TrainerTimeTable.repository.MemberRepository;
import com.hackathon.TrainerTimeTable.repository.UserRepository;
import com.hackathon.TrainerTimeTable.security.AppConfig;
import com.hackathon.TrainerTimeTable.service.UserService;
import com.hackathon.TrainerTimeTable.util.JwtTokenHelper;

@RestController

public class UserController {
	
	@Autowired
	UserService service;
	
	@Autowired UserRepository repo;
	
	@Autowired
    private JwtTokenHelper jwtTokenHelper;
	
	  @Autowired
	   private MemberRepository memberRepository;
	
	   private final AuthenticationManager authenticationManager;

	    public UserController(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }
	    
	 

	    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	    @PostMapping("user/forgotPassword")
	    public String resetPassword(@RequestParam String email, @RequestParam String newPassword) {
	        Optional<Member> memberOpt = memberRepository.findByEmail(email);
	        if (memberOpt.isEmpty()) {
	            return "❌ Email not found";
	        }

	        Member member = memberOpt.get();
	        member.setPassword(passwordEncoder.encode(newPassword));
	        memberRepository.save(member);
	        return "✅ Password updated successfully";
	    }

	@PostMapping("/user/addUser")
	public String addUser(@RequestBody JSONObject requestData) {
		JSONObject request =  new JSONObject(requestData);
		service.addNewUser(request);
		
		return "User added successfully";
	}
	
	

	@PostMapping(value = "user/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> register(
	        @RequestPart("user") String userReq,
	        @RequestPart(value = "image", required = false) MultipartFile imageFile) throws IOException {

	    // Deserialize the user JSON string into a User object
	    ObjectMapper objectMapper = new ObjectMapper();
	    User user = objectMapper.readValue(userReq, User.class);
	   
	    String msg = service.registerUser(user,imageFile);
	    return ResponseEntity.ok(msg);
	}

    @PostMapping(value = "user/authenticate",produces = "application/json")
    	public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequestDTO request) {
    	JSONObject res = new JSONObject();
//    	   org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(
//    		        new UsernamePasswordAuthenticationToken(
//    		            request.getUserName(),
//    		            request.getPassword()
//    		        )
//    		    );
    	
   
    	  
//    	   UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    	   AuthResponseDTO response  = service.loginUser(request);
    	   res.put("data", response);
    	   return ResponseEntity.ok(response);
       
    }
}
