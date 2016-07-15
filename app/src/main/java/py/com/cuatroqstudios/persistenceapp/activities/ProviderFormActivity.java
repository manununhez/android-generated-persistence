package py.com.cuatroqstudios.persistenceapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProviderDAO;
import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.models.Provider;

public class ProviderFormActivity extends AppCompatActivity {
    private Boolean booleanEditMode = false;
    private TextInputEditText tieIdProveedor;
    private TextInputEditText tieDescripcion;
    TextInputEditText tieProveedor;
    TextInputEditText tieRuc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_form);

        Intent intent = getIntent();
        if (intent.getStringExtra("typeOperation").equals("edit")) {
            booleanEditMode = true;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(booleanEditMode ? "Edit Provider" : "Add Provider");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tieIdProveedor = (TextInputEditText) findViewById(R.id.tieIdProveedor);
        tieDescripcion = (TextInputEditText) findViewById(R.id.tieDescripcion);
        tieProveedor = (TextInputEditText) findViewById(R.id.tieProveedor);
        tieRuc = (TextInputEditText) findViewById(R.id.tieRuc);

        Button btnSave = (Button) findViewById(R.id.btnSave);

        if (booleanEditMode) {
            btnSave.setText("Save Changes");
            Provider provider = (Provider) intent.getSerializableExtra("provider");
            tieIdProveedor.setText(String.valueOf(provider.getIdProveedor()));
            tieDescripcion.setText(provider.getDescripcion_local());
            tieProveedor.setText(provider.getProveedor());
            tieRuc.setText(provider.getRuc());
        }

        MySQLiteHelper db = new MySQLiteHelper(this);
        final ProviderDAO providerDAO = new ProviderDAO(db);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Provider provider = getProviderFromEditext();
                if (booleanEditMode) {
                    providerDAO.updateProvider(provider);
                }else{
                    providerDAO.addProvider(provider);
                }

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    private Provider getProviderFromEditext() {
        int idProveedor = Integer.valueOf(tieIdProveedor.getText().toString());
        String descripcion = tieDescripcion.getText().toString();
        String ruc = tieRuc.getText().toString();
        String proveedor = tieProveedor.getText().toString();
        return new Provider(idProveedor,proveedor,descripcion,ruc);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
