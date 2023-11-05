package cu.repository.service.dronewebservice.service;

import java.util.Date;
import java.util.List;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;
import cu.repository.service.dronewebservice.model.entity.DroneEntity;
import cu.repository.service.dronewebservice.repository.IBatteryEventRepository;
import cu.repository.service.dronewebservice.repository.IDroneRepository;

@Component
public class CheckBatteryService implements ICheckBatteryService{

    @Autowired
    IDroneRepository droneRepository;

    @Autowired
    IBatteryEventRepository batteryEventRepository;

    private static final Logger logger = LoggerFactory.getLogger(CheckBatteryService.class);

    @Override
    @Scheduled(cron = "*/10 * * * * *")
    public void monitoringDroneBatteryLevel() {
        List<DroneEntity> drones = this.droneRepository.findAll();
        // CheckBatteryService.logger.info("Elements: {}", drones.size());
        drones.forEach(d -> {
            CheckBatteryService.logger.info("Drone with serial number: {} and model:{} has {}% of battery level", d.getSerialNumber(), d.getModel(), d.getBattery());
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
