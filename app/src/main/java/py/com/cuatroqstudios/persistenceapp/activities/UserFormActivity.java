package py.com.cuatroqstudios.persistenceapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.math.BigDecimal;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.sharedmanager.helper.MySharedPreferencesHelper;

public class UserFormActivity extends AppCompatActivity {
    private TextInputEditText tiePassword;
    private TextInputEditText tieNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Edit User");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tieNombre = (TextInputEditText) findViewById(R.id.tieNombre);
        tiePassword = (TextInputEditText) findViewById(R.id.tiePassword);

        Button btnSave = (Button) findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        loadData();

    }

    private void loadData(){
        String nombre = MySharedPreferencesHelper.getValue(this, "nombre");
        String password = MySharedPreferencesHelper.getValue(this, "password");


        tieNombre.setText(nombre == null ? "" : nombre);
        tiePassword.setText(password == null ? "" : password);
    }

    private void saveData(){
        String nombre = tieNombre.getText().toString();
        String password = tiePassword.getText().toString();

        MySharedPreferencesHelper.save(UserFormActivity.this, "nombre", nombre);
        MySharedPreferencesHelper.save(UserFormActivity.this, "password", password);
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
