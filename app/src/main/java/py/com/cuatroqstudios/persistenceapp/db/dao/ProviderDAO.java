package py.com.cuatroqstudios.persistenceapp.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import py.com.cuatroqstudios.persistenceapp.models.Provider;
import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.db.tables.ProviderTable;

/**
 * Created by manuel on 7/8/16.
 */
public class ProviderDAO {

    private MySQLiteHelper mySQLiteHelper;

    public ProviderDAO(MySQLiteHelper mySQLiteHelper) {
        this.mySQLiteHelper = mySQLiteHelper;
    }

    // Adding new provider
    public void addProvider(Provider provider) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProviderTable.COLUMN_DESCRIPCION_LOCAL, provider.getDescription());
        values.put(ProviderTable.COLUMN_PROVEEDOR_ID, provider.getIdProvider());
        values.put(ProviderTable.COLUMN_PROVEEDOR_NAME, provider.getProveedor());
        values.put(ProviderTable.COLUMN_RUC, provider.getRuc());


        // Inserting Row
        db.insert(ProviderTable.TABLET_NAME, null, values);
        db.close(); // Closing database connection
    }


    // Getting single provider
    public Provider getProvider(int id) {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        Provider provider = new Provider();
        Cursor cursor = db.query(ProviderTable.TABLET_NAME, new String[] {
                ProviderTable.COLUMN_PROVEEDOR_ID, ProviderTable.COLUMN_PROVEEDOR_NAME,
                ProviderTable.COLUMN_DESCRIPCION_LOCAL,ProviderTable.COLUMN_RUC},
                ProviderTable.COLUMN_PROVEEDOR_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();

            provider = new Provider(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3));

            cursor.close();
        }

        return provider;
    }

    // Getting All Providers
    public List<Provider> getAllProviders() {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        List<Provider> providerList = new ArrayList<Provider>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + ProviderTable.TABLET_NAME;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                //El orden de los cursores depende del orden en que fueron creados las columnas de la tabla
                Provider provider = new Provider(Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),cursor.getString(3),cursor.getString(4));

                // Adding contact to list
                providerList.add(provider);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return provider list
        return providerList;
    }

    // Getting providers Count
    public int getProvidersCount() {
        SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();

        String countQuery = "SELECT  * FROM " + ProviderTable.TABLET_NAME;
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    // Updating single provider
    public int updateProvider(Provider provider) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProviderTable.COLUMN_DESCRIPCION_LOCAL, provider.getDescription());
        values.put(ProviderTable.COLUMN_PROVEEDOR_ID, provider.getIdProvider());
        values.put(ProviderTable.COLUMN_PROVEEDOR_NAME, provider.getProveedor());
        values.put(ProviderTable.COLUMN_RUC, provider.getRuc());

        // updating row
        return db.update(ProviderTable.TABLET_NAME, values, ProviderTable.COLUMN_PROVEEDOR_ID + " = ?",
                new String[] { String.valueOf(provider.getIdProvider()) });

    }

    // Deleting single provider
    public void deleteProvider(Provider provider) {
        SQLiteDatabase db = mySQLiteHelper.getWritableDatabase();

        db.delete(ProviderTable.TABLET_NAME, ProviderTable.COLUMN_PROVEEDOR_ID + " = ?",
                new String[] { String.valueOf(provider.getIdProvider()) });
        db.close();
    }

}
