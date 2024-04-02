package clients;

import interceptors.ResponseTimeInterceptor;
import models.ResponseWrapper;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Objects;

public abstract class RetrofitClient {

    private final Retrofit retrofit;
    ResponseTimeInterceptor responseTimeInterceptor;

    public RetrofitClient(String baseUrl) {
        responseTimeInterceptor = new ResponseTimeInterceptor();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(responseTimeInterceptor);

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .baseUrl(baseUrl)
                .build();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T> ResponseWrapper<T> execute(Call<T> call) {
        try {
            Response<T> response = call.execute();
            ResponseWrapper.ResponseWrapperBuilder builder = ResponseWrapper.builder()
                    .statusCode(response.code())
                    .responseTime(responseTimeInterceptor.getResponseTimeMs());

            return (ResponseWrapper<T>) (response.isSuccessful() ?
                    builder.body(response.body()).build() :
                    builder.error(Objects.nonNull(response.errorBody()) ? response.errorBody().string() : "Service threw an error").build());

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public <T> T createService(Class<T> serviceClass) {
        return retrofit.create(serviceClass);
    }
}