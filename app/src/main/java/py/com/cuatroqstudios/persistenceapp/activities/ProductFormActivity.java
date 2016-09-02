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

import py.com.cuatroqstudios.persistenceapp.R;
import py.com.cuatroqstudios.persistenceapp.db.dao.ProductDAO;
import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.models.Product;

public class ProductFormActivity extends AppCompatActivity {
    private Boolean booleanEditMode = false;
    private TextInputEditText tieIdProveedor;
    private TextInputEditText tieIdProducto;
    private TextInputEditText tieNombre;
    private TextInputEditText tiePrecio;
    private TextInputEditText tieCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        Intent intent = getIntent();
        if (intent.getStringExtra("typeOperation").equals("edit")) {
            booleanEditMode = true;
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(booleanEditMode ? "Edit Product" : "Add Product");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        tieIdProveedor = (TextInputEditText) findViewById(R.id.tieIdProveedor);
        tieIdProducto = (TextInputEditText) findViewById(R.id.tieIdProducto);
        tieNombre = (TextInputEditText) findViewById(R.id.tieNombre);
        tiePrecio = (TextInputEditText) findViewById(R.id.tiePrecio);
        tieCodigo = (TextInputEditText) findViewById(R.id.tieCodigo);

        Button btnSave = (Button) findViewById(R.id.btnSave);

        if (booleanEditMode) {
            btnSave.setText("Save Changes");
            Product product = (Product) intent.getSerializableExtra("product");
            tieIdProveedor.setText(String.valueOf(product.getIdProvider()));
            tieIdProducto.setText(String.valueOf(product.getIdProduct()));
            tieNombre.setText(product.getName());
            tiePrecio.setText(product.getPrice());
            tieCodigo.setText(product.getCode());
        }

        MySQLiteHelper db = new MySQLiteHelper(this);
        final ProductDAO productDAO = new ProductDAO(db);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Product product = getProductFromEditext();
                if (booleanEditMode) {
                    productDAO.updateProduct(product);
                }else{
                    productDAO.addProduct(product);
                }

                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

    }

    private Product getProductFromEditext() {
        int idProveedor = Integer.valueOf(tieIdProveedor.getText().toString());
        int idProducto = Integer.valueOf(tieIdProducto.getText().toString());
        String codigo = tieCodigo.getText().toString();
        String precio = tiePrecio.getText().toString();
        String nombre = tieNombre.getText().toString();
        return new Product(idProducto,nombre,precio,codigo,idProveedor);
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
