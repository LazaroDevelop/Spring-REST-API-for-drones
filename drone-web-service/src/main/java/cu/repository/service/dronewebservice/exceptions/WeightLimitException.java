package cu.repository.service.dronewebservice.exceptions;

/**
 * @author Lázaro Noel Guerra
 * @version 1.0
 * @brief drone with weight limit
 */
public class WeightLimitException extends RuntimeException{
    public WeightLimitException(String message){
        super(message);
    }
}
