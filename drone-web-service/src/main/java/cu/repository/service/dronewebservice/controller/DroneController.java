package cu.repository.service.dronewebservice.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;
import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.model.entity.MedicationEntity;
import cu.repository.service.dronewebservice.service.ICheckBatteryService;
import cu.repository.service.dronewebservice.service.IDroneService;

@RestController
@RequestMapping("v1/api")
public class DroneController {

    @Autowired
    IDroneService droneService;

    @Autowired
    ICheckBatteryService batteryService;

    @GetMapping("show-log-history")
    public ResponseEntity<List<CheckBatteryEvent>> showHistory(){
        List<CheckBatteryEvent> events = this.batteryService.showEventHistory();
        if(events.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }


    @GetMapping("drone-all")
    public ResponseEntity<List<DroneEntity>> findAllDrones(){
        List<DroneEntity> drones = this.droneService.findAllDrones();
        if(drones.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drones);
    }

    @PostMapping("drone-new")
    public ResponseEntity<DroneEntity> saveNewDrone(@RequestBody DroneEntity drone){
        DroneEntity newDrone = this.droneService.saveNewDrone(drone);
        if(newDrone == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(newDrone);
    }

    @PostMapping("add-medication/{droneId}")
    public ResponseEntity<String> addMedication(@PathVariable("droneId") Long droneId, @RequestBody MedicationEntity medication){
        if(this.droneService.loadDroneWithMedications(droneId, medication)){
            ResponseEntity.ok("Drone with id: {}" + droneId + " was loaded with medication " + medication.toString());
        }
        return ResponseEntity.ok("Error during drone medication load the drone was not found");
    }

    @GetMapping("drone-medications/{droneId}")
    public ResponseEntity<Set<MedicationEntity>> checkMedications(@PathVariable("droneId") Long droneId){
        Set<MedicationEntity> medications = this.droneService.checkMedicationItems(droneId);
        if(medications.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medications);
    }

    @GetMapping("drone-battery-level/{droneId}")
    public ResponseEntity<String> checkDroneBatteryLevel(@PathVariable("droneId") Long droneId){
        return ResponseEntity.ok("Drone battery level is " + this.droneService.checkBatteryLevel(droneId) + "%");
    }
}
