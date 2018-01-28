package gov.uk.dvla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleinformationApplication {
    
	public static void main(String[] args) {
		System.out.println("hello there");
		SpringApplication.run(VehicleinformationApplication.class, args);
	}
}
