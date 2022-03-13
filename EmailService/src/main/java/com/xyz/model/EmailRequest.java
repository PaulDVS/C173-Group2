package com.xyz.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor

public class EmailRequest {
	@Setter
	@Getter
	private String to;
	
	@Setter
	@Getter
	private String subject;
	
	@Setter
	@Getter
	private String message;
	


	 
	
	 
	
	
}
