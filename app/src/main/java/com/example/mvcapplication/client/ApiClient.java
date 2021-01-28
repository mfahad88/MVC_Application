package com.example.mvcapplication.client;


import android.annotation.SuppressLint;


import com.example.mvcapplication.api.ApiInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static final String BASE_LIVE = "https://www.naheed.pk/";
    public static final String BASE_URL2 = "https://media.naheed.pk/catalog/";
    //      public static final String BASE_URL = "http://192.168.1.7:8080/";
    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    private static String TOKEN=null;

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        ApiClient.TOKEN = TOKEN;
    }

    @SuppressLint("NewApi")
    public static synchronized ApiInterface getInstance() {

        if (retrofit==null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .serializeNulls()
                    .setPrettyPrinting()
                    .create();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create(gson))
//                      .callbackExecutor(Executors.newCachedThreadPool())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

                    .client(getRequestHeader())
                    .build();
        }

        return retrofit.create(ApiInterface.class);
    }



    private static OkHttpClient getRequestHeader() {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Dispatcher dispatcher=new Dispatcher();
        dispatcher.setMaxRequests(2);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .dispatcher(dispatcher)


                /*.authenticator(new Authenticator() {

                    @Override
                    public Request authenticate(Route route, Response response) throws IOException {
                      //  TOKEN= Utils.restoreSession(MyApp.getContext()).get("token").toString();
                        if(TextUtils.isEmpty(TOKEN)) {
                            TOKEN= Utils.restoreSession(MyApp.getContext()).get("token").toString();
                            retrofit2.Response<String> call = ApiClient.getInstance().token(new LoginApi("apiuser","demopass123" ))
                                    .execute();
                            if (call.isSuccessful()) {
                                TOKEN = call.body();

                            }
                        }

                        Log.wtf("Token----------->", TOKEN);


                        return response.request().newBuilder()
                                .header("Authorization", "Bearer " + TOKEN)
                                .build();
                    }
                })*/
                .addInterceptor(interceptor)

                .connectTimeout(40, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.MINUTES)
                .writeTimeout(20, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .followRedirects(false)
                .followSslRedirects(false)
                .build();

        return httpClient;
    }



}