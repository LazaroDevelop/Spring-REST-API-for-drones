package cu.repository.service.dronewebservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.model.enums.EState;
import cu.repository.service.dronewebservice.repository.IDroneRepository;
import cu.repository.service.dronewebservice.repository.IMedicationRepository;

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
            throw new RuntimeException("Drone with id: " +  droneId + " not found");
        }
    }

    @Override
    public Set<MedicationEntity> checkMedicationItems(Long droneId) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);
        if(drone.isPresent()){
            return drone.get().getMedications();
        }else {
            throw new RuntimeException("Drone with id: "+ droneId + " not found");
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
            this.medicationRepository.save(medication);
            return true;
        }

        return false;
    }

    @Override
    public DroneEntity saveNewDrone(DroneEntity drone) {
        return this.droneRepository.save(drone);
    }
    
}
