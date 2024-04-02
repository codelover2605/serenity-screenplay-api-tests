package models;

import lombok.Builder;
import lombok.Data;

import java.util.function.Predicate;

@Data
@Builder
public class AssertCondition<T> {
    private String message;
    private String failureMessage;
    private Predicate<T> predicate;
    private Class<T> clazz;
}
