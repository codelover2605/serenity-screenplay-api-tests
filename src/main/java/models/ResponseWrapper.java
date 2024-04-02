package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWrapper<T> {
    private String error;
    private T body;
    private int statusCode;
    private long responseTime;
}
