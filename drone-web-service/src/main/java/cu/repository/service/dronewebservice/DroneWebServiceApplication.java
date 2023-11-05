package cu.repository.service.dronewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DroneWebServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DroneWebServiceApplication.class, args);
    }

}
