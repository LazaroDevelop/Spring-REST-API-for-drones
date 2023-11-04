package cu.repository.service.dronewebservice.service;

import java.util.List;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;

public interface ICheckBatteryService {
    void monitoringDroneBatteryLevel();
    List<CheckBatteryEvent> showEventHistory();
}
