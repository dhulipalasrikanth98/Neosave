package com.neosave.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalException {
	@ResponseStatus(HttpStatus.BAD_REQUEST )
	@ExceptionHandler(value = RecordsNotFoundException.class )
	public @ResponseBody ErrorResponse handleRecordsNotFoundException(RecordsNotFoundException recordsNotFoundException) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),recordsNotFoundException.getMessage());
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST )
	@ExceptionHandler(value = InvalidDetailsException.class )
	public @ResponseBody ErrorResponse handleInvalidDetailsException(InvalidDetailsException recordsNotFoundException) {
		return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),recordsNotFoundException.getMessage());
	}
	

	
}
