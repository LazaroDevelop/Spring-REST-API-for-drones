package cu.repository.service.dronewebservice.exceptions;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief drone not found
 */
public class DroneNotFoundException extends RuntimeException{
    public DroneNotFoundException(String message){
        super(message);
    }
}
