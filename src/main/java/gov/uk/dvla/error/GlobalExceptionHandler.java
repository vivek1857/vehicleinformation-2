package gov.uk.dvla.error;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Value("${file.directory.path.for.scanning}")
	private String fileDirectory;
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<FileScannerError> handleFileErros(IOException ex,HttpServletRequest req){
		
		log.error("Error scanning files in directory: {} Exception message: {}",fileDirectory,ex.getMessage());
		
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new FileScannerError(ex.getMessage()));
		
	}

}
