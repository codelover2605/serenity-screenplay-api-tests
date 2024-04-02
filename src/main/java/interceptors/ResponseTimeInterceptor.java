package interceptors;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ResponseTimeInterceptor implements Interceptor {

    private long responseTimeMs;

    @Override
    public Response intercept(Chain chain) {
        try {
            long startTime = System.nanoTime();
            Response response = chain.proceed(chain.request());
            long endTime = System.nanoTime();
            responseTimeMs = (endTime - startTime) / 1_000_000;
            return response;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }
}
