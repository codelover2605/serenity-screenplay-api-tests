package asserter;

import models.AssertCondition;

import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class Asserter {
    protected <T> AssertCondition<T> buildCondition(String message, Predicate<T> predicate, Class<T> tClazz) {
        return (AssertCondition<T>) AssertCondition.builder()
                .message(message)
                .predicate((Predicate<Object>) predicate)
                .clazz((Class<Object>) tClazz)
                .build();
    }
}
