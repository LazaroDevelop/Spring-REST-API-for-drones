package cu.repository.service.dronewebservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;
import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.service.ICheckBatteryService;
import cu.repository.service.dronewebservice.service.IDroneService;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief endpoint controller
 */

@RestController
@RequestMapping("v1/api")
public class DispatchController {

    @Autowired
    IDroneService droneService;

    @Autowired
    ICheckBatteryService batteryService;

    /**
     * GET Endpoint for show log history
     * @return a {@link ResponseEntity} with a list of {@link CheckBatteryEvent}
     */
    @GetMapping("/show-log-history")
    public ResponseEntity<List<CheckBatteryEvent>> showHistory(){
        List<CheckBatteryEvent> events = this.batteryService.showEventHistory();
        if(events.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }

    /**
     * GET Endpoint for show all the drones in the system
     * @return a {@link ResponseEntity} with a list of {@link DroneEntity}
     */
    @GetMapping("/drone-all")
    public ResponseEntity<List<DroneEntity>> findAllDrones(){
        List<DroneEntity> drones = this.droneService.findAllDrones();
        if(drones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drones);
    }

    /**
     * POST Endpoint for create a new drone
     * @param drone the new drone
     * @return a {@link ResponseEntity} with a {@link DroneEntity} created
     */
    @PostMapping("/drone-new")
    public ResponseEntity<DroneEntity> saveNewDrone(@RequestBody DroneEntity drone){
        DroneEntity newDrone = this.droneService.saveNewDrone(drone);
        if(newDrone == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newDrone);
    }

    /**
     * PUT Endpoint for add medication in a given drone
     * @param serialNumber the serial number of the drone
     * @param medication the medication to add
     * @return a <code>String<code/> message
     */
    @PutMapping("/add-medication/{serialNumber}")
    public ResponseEntity<String> addMedication(@PathVariable("serialNumber") Long serialNumber, @RequestBody MedicationEntity medication){
        if(this.droneService.loadDroneWithMedications(serialNumber, medication)){
            return ResponseEntity.ok("Drone loaded");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET Endpoint for check the medications of one given drone
     * @param serialNumber the serial number of the drone
     * @return the medication set of the drone
     */
    @GetMapping("/drone-medications/{serialNumber}")
    public ResponseEntity<Set<MedicationEntity>> checkMedications(@PathVariable("serialNumber") Long serialNumber){
        Set<MedicationEntity> medications = this.droneService.checkMedicationItems(serialNumber);
        if(medications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    /**
     * GET Endpoint for know the battery level of one given drone
     * @param serialNumber the serial number of the drone
     * @retur a <code>String<code/> message with battery level
     */
    @GetMapping("/drone-battery-level/{serialNumber}")
    public ResponseEntity<String> checkDroneBatteryLevel(@PathVariable("serialNumber") Long serialNumber){
        return ResponseEntity.ok("Drone battery level is " + this.droneService.checkBatteryLevel(serialNumber) + "%");
    }

    /**
     * GET Endpoint for know the drones for loading
     * @return a list of {@link DroneEntity} ready for loading
     */
    @GetMapping("/drone-for-loading")
    public ResponseEntity<List<DroneEntity>> checkDronesForLoading(){
        List<DroneEntity> drones = this.droneService.checkAvailableDronesForLoading();
        if(drones.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(drones);
    }

    /**
     * GET Endpoint to the connection
     * @return a <code>String<code/> message
     */
    @GetMapping("/ping")
    public ResponseEntity<String> testConnection(){
        return ResponseEntity.ok("The service is running");
    }
}
