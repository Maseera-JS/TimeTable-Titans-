package com.hackathon.TrainerTimeTable.entities;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User_Details")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_name")
	private String userName;
	
	@Column(name="user_password")
	private String password;
	
	@Column(name="user_role")
	private String role;
	
	@Column(name="user_email")
	private String email;
	
	@Column(name="contact")
	private String contact;
	
	@Column(name="member_id")
	private long memberId;
	
	
	@Column(name="user_image" )
	private String userImage;
	
	@Column(name="remember_token")
	private String token;
	
	
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;
	
	@Column(name="base_centre")
	private String baseCentre;
	
	@OneToOne(mappedBy = "user")
    private Member member;
	

	
	

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getBaseCentre() {
		return baseCentre;
	}

	public void setBaseCentre(String baseCentre) {
		this.baseCentre = baseCentre;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", role=" + role + ", email="
				+ email + ", contact=" + contact + ", memberId=" + memberId + ", userImage="
				+ (userImage) + ", token=" + token + ", createdAt=" + createdAt + ", baseCentre="
				+ baseCentre + ", member=" + member + "]";
	}


	
	
	
	

}
