package com.neosave.project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordsNotFoundException  extends RuntimeException{
	
	private String message;

}
