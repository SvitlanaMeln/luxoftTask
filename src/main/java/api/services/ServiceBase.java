package api.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;

@Slf4j
public class ServiceBase {
    protected Retrofit retrofit;
    protected static String baseUrl = "http://77.102.250.113:17354";
    protected static Gson gson;
    private static String username = "user4";
    private static String password = "hlB5U1rA";


    public ServiceBase(){
        gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        OkHttpClient okHttpClient = OkHttpClientBase.getOkHttpClientWithAuth(username, password);

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    static <T> T triggerApiCall(Call<T> responseBodyCall, String serviceName) {
        Response<T> response = null;
        try {
            response = responseBodyCall.execute();
            if (response.isSuccessful()) {
                log.info(String.format("Service %s invoked : Message - %s", serviceName,
                        response.errorBody() != null ?
                        response.errorBody().string() : "Successful"));
                log.info(response.toString());
                log.info(response.body() != null ? response.body().toString() : null);
            } else {
                log.info("Error response : " + response);
            }

        } catch (IOException e) {
            log.info("Error message : " + e.getMessage());
        }
        return response.body();
    }

}
