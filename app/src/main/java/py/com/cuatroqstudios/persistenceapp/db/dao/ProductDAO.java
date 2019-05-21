package py.com.cuatroqstudios.persistenceapp.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.db.tables.ProductTable;
import py.com.cuatroqstudios.persistenceapp.models.Product;

/**
 * Created by manuel on 7/8/16.
 */
public class ProductDAO {

    private MySQLiteHelper mySQLiteHelper;

    public ProductDAO(MySQLiteHelper mySQLiteHelper) {
        this.mySQLiteHelper = mySQLiteHelper;
    }

    // Adding new provider
    public void addProduct(Product product) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductTable.COLUMN_CODIGO, product.getCode());
        values.put(ProductTable.COLUMN_PROVEEDOR_ID, product.getIdProvider());
        values.put(ProductTable.COLUMN_NOMBRE, product.getName());
        values.put(ProductTable.COLUMN_PRECIO, product.getPrice());
        values.put(ProductTable.COLUMN_PRODUCTO_ID, product.getIdProduct());


        // Inserting Row
        db.insert(ProductTable.TABLET_NAME, null, values);
        db.close(); // Closing database connection
    }


    // Getting single provider
    public Product getProduct(int id) {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        Product product = new Product();
        Cursor cursor = db.query(ProductTable.TABLET_NAME, new String[] {
                        ProductTable.COLUMN_PRODUCTO_ID, ProductTable.COLUMN_NOMBRE,
                        ProductTable.COLUMN_PRECIO,ProductTable.COLUMN_CODIGO,
                        ProductTable.COLUMN_PROVEEDOR_ID},
                ProductTable.COLUMN_PRODUCTO_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            product = new Product(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    Integer.parseInt(cursor.getString(4)));

            cursor.close();
        }

        return product;
    }

    // Getting All Providers
    public List<Product> getAllProducts() {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        List<Product> productList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ProductTable.TABLET_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //El orden de los cursores depende del orden en que fueron creados las columnas de la tabla
                Product product = new Product(Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        Integer.parseInt(cursor.getString(5)));

                // Adding product to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return provider list
        return productList;
    }

    // Getting products Count
    public int getProductsCount() {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + ProductTable.TABLET_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    // Updating single provider
    public int updateProduct(Product product) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductTable.COLUMN_CODIGO, product.getCode());
        values.put(ProductTable.COLUMN_PROVEEDOR_ID, product.getIdProvider());
        values.put(ProductTable.COLUMN_NOMBRE, product.getName());
        values.put(ProductTable.COLUMN_PRECIO, product.getPrice());
        values.put(ProductTable.COLUMN_PRODUCTO_ID, product.getIdProduct());

        // updating row
        return db.update(ProductTable.TABLET_NAME, values, ProductTable.COLUMN_PRODUCTO_ID + " = ?",
                new String[] { String.valueOf(product.getIdProduct()) });

    }

    // Deleting single provider
    public void deleteProduct(Product product) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        db.delete(ProductTable.TABLET_NAME, null, null);
        db.close();
    }
}
