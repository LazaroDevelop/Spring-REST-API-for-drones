package cu.repository.service.dronewebservice.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;
import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.repository.IBatteryEventRepository;
import cu.repository.service.dronewebservice.repository.IDroneRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CheckBatteryService implements ICheckBatteryService{

    @Autowired
    IDroneRepository droneRepository;

    @Autowired
    IBatteryEventRepository batteryEventRepository;

    @Override
    @Scheduled(fixedRate = 10000)
    public void monitoringDroneBatteryLevel() {
        List<DroneEntity> drones = this.droneRepository.findAll();
        drones.forEach(d -> {
            log.info("Checking drones battery, drone: {}", d.toString());
            CheckBatteryEvent batteryEvent = new CheckBatteryEvent();
            batteryEvent.setDroneSerialNumber(d.getSerialNumber());
            batteryEvent.setDroneBatteryLevel(d.getBattery());
            batteryEvent.setDroneModel(d.getModel());
            batteryEvent.setTimestamp(new Date());
            this.batteryEventRepository.save(batteryEvent);
        });
        
    }

    @Override
    public List<CheckBatteryEvent> showEventHistory() {
        return this.batteryEventRepository.findAll();
    }
}
