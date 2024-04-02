package services;

import models.RatesResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface LatestUSDRates {

    @GET("https://open.er-api.com/v6/latest/USD")
    Call<RatesResponse> getRates();
}
