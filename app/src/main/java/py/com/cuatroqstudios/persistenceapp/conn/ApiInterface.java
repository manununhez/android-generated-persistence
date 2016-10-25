package py.com.cuatroqstudios.persistenceapp.conn;

import java.util.List;

import py.com.cuatroqstudios.persistenceapp.models.Product;
import py.com.cuatroqstudios.persistenceapp.models.Provider;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by manuelitox on 8/18/2016.
 */
public interface ApiInterface {
    @GET("1gvln") //all products
    Call<JsonResponse<List<Product>>> getProducts();

    @GET("4doqz") //all providers
    Call<JsonResponse<List<Provider>>> getProviders();


}
