package restaurant.com.orderservice.web.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private int statusCode;
    private LocalDateTime time;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.time = LocalDateTime.now();
    }
}
