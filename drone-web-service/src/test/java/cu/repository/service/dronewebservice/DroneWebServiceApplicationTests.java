package cu.repository.service.dronewebservice;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.model.enums.EModels;
import cu.repository.service.dronewebservice.model.enums.EState;
import cu.repository.service.dronewebservice.repository.IDroneRepository;

@RunWith(SpringRunner.class)
@WebFluxTest(DroneWebServiceApplicationTests.class)
class DroneWebServiceApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    IDroneRepository droneRepository;

    @Test
    void pingTest() {
        this.webTestClient
                .get()
                .uri("/v1/api/ping")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(String.class)
                .isEqualTo("The service is running");
    }

    @Test
    void createNewDroneWithTestClient() {
        DroneEntity drone = new DroneEntity();
        drone.setBatteryCapacity(80);
        drone.setMedications(new HashSet<MedicationEntity>());
        drone.setModel(EModels.CRUISERWEIGHT);
        drone.setWeightLimit(498.2);
        drone.setState(EState.IDLE);

        this.webTestClient.post()
                .uri("/v1/api/drone-new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(drone)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DroneEntity.class).isEqualTo(drone);

    }

    @Test
    void createNewDrone() {
        DroneEntity drone = new DroneEntity();
        drone.setBatteryCapacity(80);
        drone.setMedications(new HashSet<MedicationEntity>());
        drone.setModel(EModels.CRUISERWEIGHT);
        drone.setWeightLimit(498.2);
        drone.setState(EState.IDLE);

        assertEquals(this.droneRepository.save(drone), drone);
    }

}
