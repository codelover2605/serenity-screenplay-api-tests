package clients;

import lombok.Builder;
import lombok.Data;
import models.ResponseWrapper;

import java.util.function.Predicate;

@Data
@Builder
public class AssertCondition<T> {
    private String message;
    private Predicate<ResponseWrapper<T>> predicate;
}
