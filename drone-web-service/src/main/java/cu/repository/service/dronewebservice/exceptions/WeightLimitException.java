package cu.repository.service.dronewebservice.exceptions;

public class WeightLimitException extends RuntimeException{
    public WeightLimitException(String message){
        super(message);
    }
}
