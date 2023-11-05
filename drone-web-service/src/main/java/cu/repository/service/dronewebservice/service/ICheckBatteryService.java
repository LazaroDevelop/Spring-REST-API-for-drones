package cu.repository.service.dronewebservice.service;

import java.util.List;

import cu.repository.service.dronewebservice.model.entity.CheckBatteryEvent;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 */

public interface ICheckBatteryService {
    /**
     * A periodic task for monitoring drones battery level
     */
    void monitoringDroneBatteryLevel();

    /**
     * Given the event log history
     * @return a list of {@link CheckBatteryEvent}
     */
    List<CheckBatteryEvent> showEventHistory();
}
