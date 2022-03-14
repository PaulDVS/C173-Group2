package com.xyz.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class ResultImp<T> {
	@Getter
	@Setter
	private String message;
	@Getter
	@Setter
	private T object;

}
