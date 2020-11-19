package com.archeroes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Hero {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String realName;
	
	private String mainAlias;
	
	private String otherAlias;
	
	@Lob
	@Column
	private String affiliation;
	
	@Lob
	@Column
	private String relatives;
	
	private String baseOfOperations;
	
	private String alignment;
	
	private String identity;
	
	private String citizenship;
	
	private String maritalStatus;
	
	private String occupation;
	
	private String gender;
	
	private String height;
	
	private String weight;
	
	private String eyes;
	
	private String hair;
	
	private String universe;
	
	private Integer contentStatus = 1;
	
	@Lob
	@Column
	private String extraJSON;
}
