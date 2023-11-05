package cu.repository.service.dronewebservice.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import cu.repository.service.dronewebservice.exceptions.DroneNotReadyForLoadingBatteryException;
import cu.repository.service.dronewebservice.exceptions.WeightLimitException;
import cu.repository.service.dronewebservice.repository.IMedicationRepository;
import org.springframework.beans.BeanUtils;
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

    private final double MAX_WEIGHT = 500.0;

    @Autowired
    IDroneRepository droneRepository;

    @Autowired
    IMedicationRepository medicationRepository;

    @Override
    public List<DroneEntity> checkAvailableDronesForLoading() {
        List<DroneEntity> drones = this.droneRepository.findAll();
        return drones.stream().filter(d -> d.getState() == EState.IDLE && d.getState() == EState.LOADED).collect(Collectors.toList());
    }

    @Override
    public int checkBatteryLevel(Long droneId) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);
        if(drone.isPresent()){
            return drone.get().getBatteryCapacity();
        }else {
            log.error("Drone with id: {} not found", droneId);
            throw new DroneNotFoundException("Drone with serial number: " +  droneId + " not found");
        }
    }

    @Override
    public Set<MedicationEntity> checkMedicationItems(Long droneId) {
        Optional<DroneEntity> drone = this.droneRepository.findById(droneId);
        if(drone.isPresent()){
            return drone.get().getMedications();
        }else {
            log.info("Drone with id: {} not found", droneId);
            throw new DroneNotFoundException("Drone with serial number: "+ droneId + " not found");
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
            double currentWeight = drone.get().getWeightLimit() + medication.getWeight();
            if(currentWeight >= MAX_WEIGHT){
                throw new WeightLimitException(
                        "Drone with serial number: " + drone.get().getSerialNumber() + " has the max weight supported, this drone can't load more medications"
                );
            }
            drone.get().setWeightLimit(drone.get().getWeightLimit() + medication.getWeight());
            medication.setDrone(drone.get());
            drone.get().getMedications().add(medication);
            this.droneRepository.save(drone.get());
            log.info("Drone with serial number: {} was loaded successfuly", droneId);
            return true;
        }else{
            log.error("Drone with serial number: {} was not found", droneId);
            return false;
        }
    }

    @Override
    public DroneEntity saveNewDrone(DroneEntity drone) {
        DroneEntity newDrone = new DroneEntity();
        if(drone.getBatteryCapacity() <= 25 && drone.getState() == EState.LOADING){
            BeanUtils.copyProperties(drone, newDrone);
            return this.droneRepository.save(newDrone);
        }else if(drone.getBatteryCapacity() > 25 && drone.getState() == EState.LOADING){
            throw new DroneNotReadyForLoadingBatteryException(
                    "Drones only can in loading state when the battery is below 25%, current drone has " + drone.getBatteryCapacity() + "% of battery capacity");
        }
        return this.droneRepository.save(drone);
    }
    
}
