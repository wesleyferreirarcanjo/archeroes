package com.archeroes.dto;

import java.io.Serializable;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HeroDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String realName;
	
	private String mainAlias;
	
	private String otherAlias;

	private String affiliation;

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
	
	private String extraJSON;
}
