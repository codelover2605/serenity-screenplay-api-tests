package tests;

import asserters.RatesResponseAsserter;
import clients.RatesServiceClient;
import models.RatesResponse;
import net.serenitybdd.junit5.SerenityJUnit5Extension;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class LatestUSDRatesTests extends APITest {

    RatesServiceClient ratesServiceClient = client(RatesServiceClient.class);
    RatesResponseAsserter ratesResponse = client(RatesResponseAsserter.class);

    @Test
    public void shouldValidLatestUSDRates() {
        rateChecker(RatesResponse.class)
                .performs(ratesServiceClient::getLatestUsdRates)
                .statusCodeShouldBe(200)
                .responseTimeShouldBeWithInSecs(3)
                .shouldSeeThat(ratesResponse.resultIs("success"))
                .shouldSeeThat(ratesResponse.usdPriceAgainstCurrencyIsInRange("AED", 3.6, 3.7))
                .shouldSeeThat(ratesResponse.hasCurrencyPairs(162));
    }
}