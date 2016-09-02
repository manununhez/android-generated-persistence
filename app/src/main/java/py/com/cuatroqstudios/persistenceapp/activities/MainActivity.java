package py.com.cuatroqstudios.persistenceapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProviderDAO;
import py.com.cuatroqstudios.persistenceapp.db.utils.DbUtils;

public class MainActivity extends AppCompatActivity {
    private TextView tvListBD;
    private TextView tvDataCount;
    private ProviderDAO providerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            DbUtils.exportDB(this);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Button btnUser = (Button) findViewById(R.id.btnUser);
        Button btnShoppingCart = (Button) findViewById(R.id.btnShoppingCart);
        Button btnProduct = (Button) findViewById(R.id.btnProduct);
        Button btnProvider = (Button) findViewById(R.id.btnProvider);
        Button btnImageProduct = (Button) findViewById(R.id.btnImageProduct);
        Button btnDataProviders = (Button) findViewById(R.id.btnDataProviders);
        Button btnPhoto = (Button) findViewById(R.id.btnPhoto);
        Button btnRest = (Button) findViewById(R.id.btnRest);

        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RestActivity.class));
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MakePhotoActivity.class));
            }
        });

        btnDataProviders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SensorActivity.class));
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });

        btnShoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShoppingCartActivity.class));
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProductActivity.class));
            }
        });

        btnProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ProviderActivity.class));
            }
        });

        btnImageProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ImageProductActivity.class));
            }
        });


    }


}
