package tests;

import asserters.RatesResponseAsserter;
import clients.APITest;
import clients.ActorClient;
import clients.USDRatesServiceClient;
import models.RatesResponse;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SerenityJUnit5Extension.class)
public class LatestUSDRatesTests extends APITest {

    private USDRatesServiceClient usdRates = createInstance(USDRatesServiceClient.class);
    private RatesResponseAsserter responseAsserter = createInstance(RatesResponseAsserter.class);

    @Test
    public void shouldValidLatestUSDRates() {
        ActorClient<RatesResponse> rateChecker = actorClient();

        rateChecker
                .performs(usdRates::getLatest)
                .statusCodeShouldBe(200)
                .responseTimeShouldBeWithInSecs(3)
                .shouldSeeThat(responseAsserter.resultIs("success"))
                .shouldSeeThat(responseAsserter.usdPriceAgainstCurrencyIsInRange("AED", 3.6, 3.7))
                .shouldSeeThat(responseAsserter.hasCurrencyPairs(162));
    }
}