package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class GeneralAgency {

	@Id
	private Long  GaCd;
	
	private String gaHnNm;
	
	private String addr1;
	
	private String x;
	
	private String y;
}
