package py.com.cuatroqstudios.persistenceapp.conn;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manuelitox on 8/18/2016.
 */
public class ApiClient {

    public static final String BASE_URL = "https://api.myjson.com/bins/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}