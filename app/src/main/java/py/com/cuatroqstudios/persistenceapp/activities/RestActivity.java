package py.com.cuatroqstudios.persistenceapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.conn.ApiClient;
import py.com.cuatroqstudios.persistenceapp.conn.ApiInterface;
import py.com.cuatroqstudios.persistenceapp.conn.JsonResponse;
import py.com.cuatroqstudios.persistenceapp.models.Product;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestActivity extends AppCompatActivity {
    private TextView tvResults;
    private ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        tvResults = (TextView) findViewById(R.id.tvResults);
        Button btnGetProducts = (Button) findViewById(R.id.btnGetProducts);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        btnGetProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getProducts();
            }
        });

    }

    private void getProducts() {
        Call<JsonResponse<List<Product>>> call = apiService.getProducts();
        call.enqueue(new Callback<JsonResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<JsonResponse<List<Product>>> call, Response<JsonResponse<List<Product>>> response) {
                List<Product> products = response.body().data;
                tvResults.setText(products.toString());

                Toast.makeText(RestActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JsonResponse<List<Product>>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Rest", t.toString());
            }


        });
    }
}

