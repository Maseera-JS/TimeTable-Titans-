package com.hackathon.TrainerTimeTable.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="member")
@Setter@Getter@AllArgsConstructor@NoArgsConstructor
public class Member {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;;

    private String name;

    @NotBlank
    @Column(unique = true)
    private String email;
    
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    
    @Column(name="role")
    private String role;
    
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    
    

}
