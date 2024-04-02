package clients;

import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;
public class USDRatesServiceClient {
    public Task getLatest() {
        return Task.where("{0} Get Latest USD Rates", Get.resource("/latest/usd"));
    }
}
