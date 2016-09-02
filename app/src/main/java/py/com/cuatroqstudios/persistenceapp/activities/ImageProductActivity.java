package py.com.cuatroqstudios.persistenceapp.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.filesmanager.helper.MyFilesHelper;

public class ImageProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_product);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("ProductImage");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final TextInputEditText tieIdProductoImagen = (TextInputEditText) findViewById(R.id.tieIdProductoImagen);
        final TextInputEditText tieImagen = (TextInputEditText) findViewById(R.id.tieImagen);
        final TextInputEditText tieIdProducto = (TextInputEditText) findViewById(R.id.tieIdProducto);
        final TextInputEditText tieImg_src = (TextInputEditText) findViewById(R.id.tieImg_src);

        Button btnWriteToFile = (Button) findViewById(R.id.btnWriteToFile);
        Button btnReadFromFile = (Button) findViewById(R.id.btnReadFromFile);

        final EditText etContent = (EditText) findViewById(R.id.etContent);

        final String content = tieIdProductoImagen.getText() + "," + tieImagen.getText() + "," + tieIdProducto.getText() + ","
                + tieImg_src.getText();


        btnReadFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etContent.setText(MyFilesHelper.readFile(getFilesDir().getPath() +"prueba.txt", "UTF-8"));
            }
        });

        btnWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Test - Write", tieIdProductoImagen.getText() + "," + tieImagen.getText() + "," + tieIdProducto.getText() + ","
                        + tieImg_src.getText());
                MyFilesHelper.writeFile(getFilesDir().getPath() +"prueba.txt", tieIdProductoImagen.getText() + "," + tieImagen.getText() + "," + tieIdProducto.getText() + ","
                        + tieImg_src.getText());
            }
        });


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
