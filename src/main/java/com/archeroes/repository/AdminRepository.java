package com.archeroes.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.archeroes.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
	
	@Transactional(readOnly = true)
	public Admin findByEmail(String email);
}
