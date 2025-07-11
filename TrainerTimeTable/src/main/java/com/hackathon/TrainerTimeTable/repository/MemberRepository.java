package com.hackathon.TrainerTimeTable.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackathon.TrainerTimeTable.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	public Optional<Member> findByMemberId(long id);
	
	public Optional<Member> findByEmail(String email);
	
	
}
