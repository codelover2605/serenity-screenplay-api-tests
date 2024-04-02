package asserters;

import clients.AssertCondition;
import com.google.common.util.concurrent.AtomicDouble;
import models.RatesResponse;
import models.ResponseWrapper;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class RatesResponseAsserter {
    public AssertCondition<RatesResponse> resultIs(String result) {
        AtomicReference<String> actual = new AtomicReference<>("");

        Predicate<ResponseWrapper<RatesResponse>> predicate = (response) -> {
            String actualResult = response.getBody().getResult();
            actual.set(actualResult);
            return actualResult.equals(result);
        };

        return AssertCondition.<RatesResponse>builder()
                .predicate(predicate)
                .message(String.format("Result should be %s but was %s", result, actual))
                .build();
    }

    public AssertCondition<RatesResponse> hasCurrencyPairs(int value) {
        AtomicInteger currencyPairs = new AtomicInteger();
        Predicate<ResponseWrapper<RatesResponse>> predicate = response -> {
            int size = response.getBody().getRates().entrySet().size();
            currencyPairs.set(size);
            return (long) size == value;
        };

        return AssertCondition.<RatesResponse>builder()
                .predicate(predicate)
                .message(String.format("Expected %s currency pairs but was %s", value, currencyPairs))
                .build();
    }

    public AssertCondition<RatesResponse> usdPriceAgainstCurrencyIsInRange(String currency, double start, double end) {
        AtomicDouble aedValue = new AtomicDouble();
        Predicate<ResponseWrapper<RatesResponse>> predicate = response -> {
            double value = response.getBody().getRates().get(currency);
            aedValue.set(value);
            return value > start && value < end;
        };
        return AssertCondition.<RatesResponse>builder()
                .predicate(predicate)
                .message(String.format("Expected %s to be in the rang %s-%s but was %s", currency, start, end, aedValue))
                .build();

    }
}
