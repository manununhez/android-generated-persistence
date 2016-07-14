package py.com.cuatroqstudios.persistenceapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.models.Provider;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProviderDAO;
import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;

public class DatabaseHelperActivity extends AppCompatActivity {
    private TextView tvListBD;
    private TextView tvDataCount;
    private ProviderDAO providerDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Button btnInserts = (Button) findViewById(R.id.btnInserts);
        Button btnGetAll = (Button) findViewById(R.id.btnGetAll);
        Button btnGetSpecific = (Button) findViewById(R.id.btnGetSpecific);
        final TextView tvSpecificID = (TextView) findViewById(R.id.tvSpecificID);
        tvListBD = (TextView) findViewById(R.id.tvListBD);
        tvDataCount = (TextView) findViewById(R.id.tvDataCount);

        MySQLiteHelper db = new MySQLiteHelper(this);
        providerDAO = new ProviderDAO(db);

        updateDatabaseList();
        updateDatabaseCount();

        btnInserts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                providerDAO.addProvider(new Provider(1, "Sallustro", "Comercial", "4561238"));
                providerDAO.addProvider(new Provider(2, "Diesa", "Local", "4561238"));
                providerDAO.addProvider(new Provider(3, "Sepsa", "Local", "4561238"));
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDatabaseList();
                updateDatabaseCount();
            }
        });

        btnGetSpecific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvSpecificID.setText(providerDAO.getProvider(2).toString());
            }
        });
    }


    private void updateDatabaseList() {
        List<Provider> providerList = providerDAO.getAllProviders();
        tvListBD.setText(providerList.size() > 0 ? providerList.toString() : "No hay nada cargado.");
    }

    private void updateDatabaseCount(){
        tvDataCount.setText(String.valueOf(providerDAO.getProvidersCount()));
    }
}
