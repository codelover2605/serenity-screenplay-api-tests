package asserters;

import models.AssertCondition;
import asserter.Asserter;
import models.RatesResponse;

public class RatesResponseAsserter extends Asserter {
    public AssertCondition<RatesResponse> resultIs(String result) {
        return buildCondition(
                "Result should be success",
                (response) -> ((models.RatesResponse) response).getResult().equals(result),
                RatesResponse.class
        );
    }

    public AssertCondition<RatesResponse> hasCurrencyPairs(int value) {
        return buildCondition(
                String.format("Should have %s currency pairs", value),
                (response) -> response.getRates().entrySet().size() == value,
                RatesResponse.class
        );
    }

    public AssertCondition<RatesResponse> usdPriceAgainstCurrencyIsInRange(String currency, double start, double end) {
        return buildCondition(
                String.format("AED should be in range %s and %s", start, end),
                (response) -> {
                    double value = response.getRates().get(currency);
                    return value > start && value < end;
                },
                RatesResponse.class
        );
    }
}
