package clients;

import models.RatesResponse;
import models.ResponseWrapper;
import services.LatestUSDRates;

public class RatesServiceClient extends RetrofitClient {

    private final LatestUSDRates service;

    public RatesServiceClient() {
        super("https://open.er-api.com/v6/");
        service = createService(LatestUSDRates.class);
    }

    public ResponseWrapper<RatesResponse> getLatestUsdRates() {
        return execute(service.getRates());
    }
}
