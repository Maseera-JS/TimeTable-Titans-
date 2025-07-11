package com.hackathon.TrainerTimeTable.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hackathon.TrainerTimeTable.dto.AuthRequestDTO;
import com.hackathon.TrainerTimeTable.dto.AuthResponseDTO;
import com.hackathon.TrainerTimeTable.entities.CustomUserDetails;
import com.hackathon.TrainerTimeTable.entities.Member;
import com.hackathon.TrainerTimeTable.entities.User;
import com.hackathon.TrainerTimeTable.repository.MemberRepository;
import com.hackathon.TrainerTimeTable.repository.UserRepository;
import com.hackathon.TrainerTimeTable.util.JwtTokenHelper;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired UserRepository userRepository;
	@Autowired MemberRepository memberRepo;
	 @Autowired private JwtTokenHelper jwtUtil;
//	 @Autowired EmailService emailService;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	public JSONObject addNewUser(JSONObject request) {
		
		User user = new User();
		String message = "";
		JSONObject response = new JSONObject();
		
		if(request != null) {
		       String userName  = request.optString("userName");
		       String password  = request.optString("password");
		       String role  = request.optString("role");
		       String email  = request.optString("email");
		       
		       user.setUserName(userName);
		       user.setPassword(password);
		       user.setRole(role);
		       user.setEmail(email);
		       
		       userRepository.save(user);
		       message = "User created successfully";
		       
		       response.put("user", user);
		       response.put("message" , message);
		       return response;
		       
		}else {
		      message = "Error creating object";
			  response.put("message" , message);
			  return response;
		}
		
		
		
	}
	
	
	public String registerUser(User user, MultipartFile img) throws IOException {
	    if (userRepository.findByEmail(user.getEmail()) != null) {
	        return "User already exists!";
	    }

	    // Step 1: Build new User object
	    User nUser = new User();
	    nUser.setUserName(user.getUserName());
	    nUser.setEmail(user.getEmail());
	    nUser.setRole(user.getRole());
	    nUser.setPassword(passwordEncoder.encode(user.getPassword()));
	    nUser.setToken("");
	    nUser.setBaseCentre(user.getBaseCentre());
	    nUser.setContact(user.getContact());
	    nUser.setCreatedAt(LocalDateTime.now(ZoneId.of("Asia/Kolkata")));

	    // Step 2: Handle Image Upload
	    if (!img.isEmpty()) {
	        String uploadDir = "user-images/";
	        File uploadFolder = new File(uploadDir);
	        if (!uploadFolder.exists()) {
	            uploadFolder.mkdirs();
	        }

	        String fileName = UUID.randomUUID().toString() + "_" + img.getOriginalFilename();
	        Path filePath = Paths.get(uploadDir + fileName);
	        Files.write(filePath, img.getBytes());

	        nUser.setUserImage(filePath.toString());
	    }
        
//	    emailService.sendWelcomeEmail(nUser.getEmail(), nUser.getUserName(), nUser.getId());

	    userRepository.save(nUser);

	    Member member = new Member();
	    member.setName(nUser.getUserName());
	    member.setEmail(nUser.getEmail());
	    member.setPassword(user.getPassword()); 
	    member.setRole(nUser.getRole());
	    member.setUser(nUser);

	    memberRepo.save(member);

	
	    nUser.setMemberId(member.getMemberId());
	    nUser.setMember(member);
	    userRepository.save(nUser);

	    return "User registered successfully!";
	}

	   

	
	public AuthResponseDTO loginUser(AuthRequestDTO request) {
	    User user = userRepository.findByUserName(request.getUserName());
	    AuthResponseDTO response = new AuthResponseDTO();

	    if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
	        response.setMessage("Invalid email or password");
	        return response;
	    }

	   
	    String token = jwtUtil.generateToken(user.getUserName());

	  
	    user.setToken(token);
	    userRepository.save(user); 

	   
	    response.setMessage("Login successful");
	    response.setToken(token);
	    response.setRole(user.getRole());

	    return response;
	}




		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			// TODO Auto-generated method stub
			 try {
			        User user = userRepository.findByUserName(username);
			        if (user == null) {
			            throw new UsernameNotFoundException("User not found with username: " + username);
			        }

			        if (user.getRole() == null) {
			            throw new IllegalStateException("User role is null for username: " + username);
			        }

			        return new org.springframework.security.core.userdetails.User(
			            user.getUserName(),
			            user.getPassword(),
			            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()))

			        );
//			        return new CustomUserDetails(user);
			    } catch (Exception e) {
			        // This exception will get wrapped into InternalAuthenticationServiceException
			        System.err.println("Exception in loadUserByUsername: " + e.getMessage());
			        throw e;
			    }
		}


		
		
	
		




		
}
