package restaurant.com.orderservice.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import restaurant.com.orderservice.web.dto.ErrorResponse;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ControllerException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleChangeOrderStatusException(HttpMessageNotReadableException e) {
        log.error(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Invalid order status", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handle(HttpServletRequest request) {
        log.error("MethodArgumentTypeMismatchException %s".formatted(request.getRequestURI()));

        ErrorResponse errorResponse = new ErrorResponse("Invalid parameter", 400);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ErrorResponse> orderNotFound(HttpServletRequest request, OrderNotFoundException e) {
        log.error("Exception in %s".formatted(request.getRequestURI()));
        log.error(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse("Order not found", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<ErrorResponse> restaurantNotFound(HttpServletRequest request, RestaurantNotFoundException e) {
        log.error("Exception in %s".formatted(request.getRequestURI()));
        log.error(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(WaiterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleWaiterNotFoundException(HttpServletRequest request, WaiterNotFoundException e) {
        log.error("Exception in %s".formatted(request.getRequestURI()));

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 400);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> generalHandle(HttpServletRequest request, Exception e) {
        log.error("Exception in %s".formatted(request.getRequestURI()));

        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), 500);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleDtoValidation(HttpServletRequest request, MethodArgumentNotValidException e, BindingResult bindingResult) {
        List<String> allErrors = bindingResult.getAllErrors()
                .stream()
                .map( error -> error.getDefaultMessage())
                .toList();

        String errorMessages = String.join("\n", allErrors);

        log.error("Exception in %s".formatted(request.getRequestURI()));
        log.error(errorMessages);

        ErrorResponse errorResponse = new ErrorResponse(errorMessages, 400);

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}