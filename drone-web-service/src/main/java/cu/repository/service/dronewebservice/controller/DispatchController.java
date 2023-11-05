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

@RestController
@RequestMapping("v1/api")
public class DispatchController {

    @Autowired
    IDroneService droneService;

    @Autowired
    ICheckBatteryService batteryService;

    @GetMapping("/show-log-history")
    public ResponseEntity<List<CheckBatteryEvent>> showHistory(){
        List<CheckBatteryEvent> events = this.batteryService.showEventHistory();
        if(events.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }


    @GetMapping("/drone-all")
    public ResponseEntity<List<DroneEntity>> findAllDrones(){
        List<DroneEntity> drones = this.droneService.findAllDrones();
        if(drones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drones);
    }

    @PostMapping("/drone-new")
    public ResponseEntity<DroneEntity> saveNewDrone(@RequestBody DroneEntity drone){
        DroneEntity newDrone = this.droneService.saveNewDrone(drone);
        if(newDrone == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newDrone);
    }

    @PutMapping("/add-medication/{droneId}")
    public ResponseEntity<String> addMedication(@PathVariable("droneId") Long droneId, @RequestBody MedicationEntity medication){
        if(this.droneService.loadDroneWithMedications(droneId, medication)){
            return ResponseEntity.ok("Drone loaded");
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/drone-medications/{droneId}")
    public ResponseEntity<Set<MedicationEntity>> checkMedications(@PathVariable("droneId") Long droneId){
        Set<MedicationEntity> medications = this.droneService.checkMedicationItems(droneId);
        if(medications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    @GetMapping("/drone-battery-level/{droneId}")
    public ResponseEntity<String> checkDroneBatteryLevel(@PathVariable("droneId") Long droneId){
        return ResponseEntity.ok("Drone battery level is " + this.droneService.checkBatteryLevel(droneId) + "%");
    }

    @GetMapping("/drone-for-loading")
    public ResponseEntity<List<DroneEntity>> checkDronesForLoading(){
        List<DroneEntity> drones = this.droneService.checkAvailableDronesForLoading();
        if(drones.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(drones);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> testConnection(){
        return ResponseEntity.ok("The service is running");
    }
}
