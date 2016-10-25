package py.com.cuatroqstudios.persistenceapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.db.utils.DbUtils;

public class MainActivity extends AppCompatActivity {

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
        Button btnRest = (Button) findViewById(R.id.btnRest);


        btnRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RestActivity.class));
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


        //Handle incoming data
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent, "text"); // Handle text being sent
            } else if (type.startsWith("image/"))
            {
                handleSendData(intent,"image" ); // Handle single image being sent
            } else if (type.startsWith("audio/"))
            {
                handleSendData(intent,"audio" ); // Handle single audio being sent

            } else if (type.startsWith("video/"))
            {
                handleSendData(intent,"video" ); // Handle single video being sent
            } else if (type.startsWith("application/"))
            {
                handleSendData(intent,"application" ); // Handle single file being sent
            }
        }
    }


    //***********
    void handleSendText(Intent intent, String type) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            Toast.makeText(MainActivity.this, type+" => "+sharedText, Toast.LENGTH_SHORT).show();
        }
    }

    void handleSendData(Intent intent, String type) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            // Update UI to reflect image being shared
            Toast.makeText(MainActivity.this, type+" => "+imageUri.toString(), Toast.LENGTH_SHORT).show();

        }
    }

}
