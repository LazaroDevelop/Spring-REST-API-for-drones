package cu.repository.service.dronewebservice.exceptions;

public class DroneNotReadyForLoadingBatteryException extends RuntimeException{
    public DroneNotReadyForLoadingBatteryException(String message){
        super(message);
    }
}
