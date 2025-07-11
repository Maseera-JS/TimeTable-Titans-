package com.hackathon.TrainerTimeTable.dto;

public class AuthResponseDTO {
	
	private String message;
	
	private String token;
	
	private String role;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public AuthResponseDTO(AuthResponseDTO response) {
		super();
		// TODO Auto-generated constructor stub
	}

	public AuthResponseDTO() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	

}
