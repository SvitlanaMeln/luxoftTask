package api.services;

import okhttp3.*;

import java.io.IOException;

public class OkHttpClientBase {

    public static OkHttpClient getOkHttpClientWithAuth(final String username, final String password) {
        final String credentials = Credentials.basic(username, password);

        return new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();
                        Request requestWithAuth = original.newBuilder()
                                .header("Authorization", credentials)
                                .build();
                        return chain.proceed(requestWithAuth);
                    }
                })
                .build();
    }
}
