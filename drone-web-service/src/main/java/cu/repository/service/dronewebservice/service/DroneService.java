package cu.repository.service.dronewebservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import cu.repository.service.dronewebservice.repository.IMedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cu.repository.service.dronewebservice.exceptions.DroneNotFoundException;
import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.model.enums.EState;
import cu.repository.service.dronewebservice.repository.IDroneRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DroneService implements IDroneService {

    @Autowired
    IDroneRepository droneRepository;

    @Autowired
    IMedicationRepository medicationRepository;

    @Override
    public List<DroneEntity> checkAvailableDronesForLoading() {
        List<DroneEntity> drones = this.droneRepository.findAll();
        return drones.stream().filter(d -> d.getState() == EState.IDLE).collect(Collectors.toList());
    }

    @Override
    public int checkBatteryLevel(Long droneId) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);
        if(drone.isPresent()){
            return drone.get().getBattery();
        }else {
            log.error("Drone with id: {} not found", droneId);
            throw new DroneNotFoundException("Drone with id: " +  droneId + " not found");
        }
    }

    @Override
    public Set<MedicationEntity> checkMedicationItems(Long droneId) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);
        if(drone.isPresent()){
            return drone.get().getMedications();
        }else {
            log.info("Drone with id: {} not found", droneId);
            throw new DroneNotFoundException("Drone with id: "+ droneId + " not found");
        }
    }

    @Override
    public List<DroneEntity> findAllDrones() {
        return this.droneRepository.findAll();
    }

    @Override
    public Boolean loadDroneWithMedications(Long droneId, MedicationEntity medication) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);

        if(drone.isPresent()){
            medication.setDrone(drone.get());
            drone.get().getMedications().add(medication);
            log.info("Drone with id: {} was loaded successfuly", droneId);
            this.droneRepository.save(drone.get());
            return true;
        }else{
            log.error("Drone with id: {} not found", droneId);
            return false;
        }
    }

    @Override
    public DroneEntity saveNewDrone(DroneEntity drone) {
        return this.droneRepository.save(drone);
    }
    
}
