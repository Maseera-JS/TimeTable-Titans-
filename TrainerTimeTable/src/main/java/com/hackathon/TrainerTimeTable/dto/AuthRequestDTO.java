package com.hackathon.TrainerTimeTable.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthRequestDTO {
	
	   @JsonProperty("userName")
	  private String userName;
	   
	   @JsonProperty("password")
	    private String password;

	    public AuthRequestDTO() {}

	    public String getUserName() {
	        return userName;
	    }
	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    public String getPassword() {
	        return password;
	    }
	    public void setPassword(String password) {
	        this.password = password;
	    }

	    @Override
	    public String toString() {
	        return "AuthRequestDTO{userName='" + userName + "', password='" + password + "'}";
	    }
	  

}
