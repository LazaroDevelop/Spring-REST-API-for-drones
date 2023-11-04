package cu.repository.service.dronewebservice.service;

import java.util.List;
import java.util.Set;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;

public interface IDroneService {
    DroneEntity saveNewDrone(DroneEntity drone);
    List<DroneEntity> findAllDrones();
    Boolean loadDroneWithMedications(Long droneId, MedicationEntity medication);
    Set<MedicationEntity> checkMedicationItems(Long droneId);
    List<DroneEntity> checkAvailableDronesForLoading();
    int checkBatteryLevel(Long droneId);
}
