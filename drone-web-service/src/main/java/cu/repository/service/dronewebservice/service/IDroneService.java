package cu.repository.service.dronewebservice.service;

import java.util.List;
import java.util.Set;

import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 */

public interface IDroneService {
    /**
     * Create and save a new drone
     * @param drone the drone entity
     * @return the drone entity that was saved
     */
    DroneEntity saveNewDrone(DroneEntity drone);

    /**
     * Find all the drones in the repository
     * @return a list of {@link DroneEntity}
     */
    List<DroneEntity> findAllDrones();

    /**
     * Load a given drone with a medication
     * @param serialNumber the serial number of the drone
     * @param medication the medication to load in a given drone
     * @return true in best case, false in another case
     */
    Boolean loadDroneWithMedications(Long serialNumber, MedicationEntity medication);

    /**
     * Check all the medication when a drone is given
     * @param serialNumber the serial number of the given drone
     * @return a collection of medication items
     */
    Set<MedicationEntity> checkMedicationItems(Long serialNumber);

    /**
     * Check the available drones for loading medications
     * @return a list of {@link DroneEntity}
     */
    List<DroneEntity> checkAvailableDronesForLoading();

    /**
     * Check the battery level for a given drone
     * @param serialNumber the serial number of the drone
     * @return a current battery capacity
     */
    int checkBatteryLevel(Long serialNumber);
}
