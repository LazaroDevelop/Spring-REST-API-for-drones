package cu.repository.service.dronewebservice;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;
import cu.repository.service.dronewebservice.repository.ICheckBatteryEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.model.enums.EModels;
import cu.repository.service.dronewebservice.model.enums.EState;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DroneWebServiceApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ICheckBatteryEventRepository batteryEventRepository;

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
    void createNewDrone() {
        DroneEntity drone = new DroneEntity();
        drone.setBatteryCapacity(80);
        drone.setMedications(new HashSet<>());
        drone.setModel(EModels.CRUISERWEIGHT);
        drone.setWeightLimit(472.2);
        drone.setState(EState.IDLE);

        DroneEntity drone1 = new DroneEntity();
        drone1.setBatteryCapacity(45);
        drone1.setMedications(new HashSet<>());
        drone1.setModel(EModels.LIGHTWEIGHT);
        drone1.setWeightLimit(200.0);
        drone1.setState(EState.DELIVERING);

        this.webTestClient.post()
                .uri("/v1/api/drone-new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(drone)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DroneEntity.class).value(v -> {
                    assertThat(v.getSerialNumber()).isNotNull();
                    assertThat(v.getBatteryCapacity()).isEqualTo(drone.getBatteryCapacity());
                    assertThat(v.getMedications()).isEqualTo(drone.getMedications());
                    assertThat(v.getState()).isEqualTo(drone.getState());
                    assertThat(v.getModel()).isEqualTo(drone.getModel());
                    assertThat(v.getWeightLimit()).isEqualTo(drone.getWeightLimit());
                });

        this.webTestClient.post()
                .uri("/v1/api/drone-new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(drone1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DroneEntity.class).value(v -> {
                    assertThat(v.getSerialNumber()).isNotNull();
                    assertThat(v.getBatteryCapacity()).isEqualTo(drone1.getBatteryCapacity());
                    assertThat(v.getMedications()).isEqualTo(drone1.getMedications());
                    assertThat(v.getState()).isEqualTo(drone1.getState());
                    assertThat(v.getModel()).isEqualTo(drone1.getModel());
                    assertThat(v.getWeightLimit()).isEqualTo(drone1.getWeightLimit());
                });
    }

    @Test
    void getAllTest(){
        this.webTestClient.get()
                .uri("/v1/api/drone-all")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void addMedicationForAGivenDrone(){
        DroneEntity drone = new DroneEntity();
        drone.setBatteryCapacity(80);
        drone.setMedications(new HashSet<>());
        drone.setModel(EModels.CRUISERWEIGHT);
        drone.setWeightLimit(472.2);
        drone.setState(EState.IDLE);

        this.webTestClient.post()
                .uri("/v1/api/drone-new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(drone)
                .exchange()
                .expectStatus().isOk()
                .expectBody(DroneEntity.class).value(v -> {
                    assertThat(v.getSerialNumber()).isNotNull();
                    assertThat(v.getBatteryCapacity()).isEqualTo(drone.getBatteryCapacity());
                    assertThat(v.getMedications()).isEqualTo(drone.getMedications());
                    assertThat(v.getState()).isEqualTo(drone.getState());
                    assertThat(v.getModel()).isEqualTo(drone.getModel());
                    assertThat(v.getWeightLimit()).isEqualTo(drone.getWeightLimit());
                });

        MedicationEntity medication = new MedicationEntity();
        medication.setCode("LKHDKJS98");
        medication.setDrone(drone);
        medication.setName("tilenol1912");
        medication.setWeight(1.98);
        medication.setImage("http://jldsiobiuofiuo%%38489KGjlskll");

        this.webTestClient.put()
                .uri("/v1/api/add-medication/3")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(medication)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Drone loaded");
    }

    @Test
    void showLogHistoryTest(){
        CheckBatteryEvent event = new CheckBatteryEvent();
        event.setDroneModel(EModels.LIGHTWEIGHT);
        event.setTimestamp(new Date());
        event.setDroneBatteryLevel(98);
        event.setDroneSerialNumber(1);

        this.batteryEventRepository.save(event);

        this.webTestClient.get()
                .uri("/v1/api/show-log-history")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }

}
