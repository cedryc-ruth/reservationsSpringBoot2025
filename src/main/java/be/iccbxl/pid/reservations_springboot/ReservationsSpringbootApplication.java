package be.iccbxl.pid.reservations_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReservationsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationsSpringbootApplication.class, args);
	}

}
