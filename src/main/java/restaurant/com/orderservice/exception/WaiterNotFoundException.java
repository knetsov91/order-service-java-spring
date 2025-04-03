package restaurant.com.orderservice.exception;

public class WaiterNotFoundException extends RuntimeException {

    public WaiterNotFoundException(String message) {
        super(message);
    }
}