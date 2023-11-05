package cu.repository.service.dronewebservice.exceptions;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief drone no ready for loading state
 */
public class DroneNotReadyForLoadingBatteryException extends RuntimeException{
    public DroneNotReadyForLoadingBatteryException(String message){
        super(message);
    }
}
