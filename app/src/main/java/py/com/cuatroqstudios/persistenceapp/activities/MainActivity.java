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

        Button btnDb = (Button) findViewById(R.id.btnDb);
        Button btnFiles = (Button) findViewById(R.id.btnFiles);
        Button btnKeyValue = (Button) findViewById(R.id.btnKeyValue);

        btnDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DatabaseHelperActivity.class));
            }
        });

        btnFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, FilesHelperActivity.class));

            }
        });

        btnKeyValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KeyValueHelperActivity.class));

            }
        });
    }


}
