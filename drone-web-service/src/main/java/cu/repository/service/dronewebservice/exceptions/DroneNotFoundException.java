package cu.repository.service.dronewebservice.exceptions;


public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(String message){
        super(message);
    }
}
