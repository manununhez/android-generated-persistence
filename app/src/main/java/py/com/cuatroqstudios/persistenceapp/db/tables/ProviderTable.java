package py.com.cuatroqstudios.persistenceapp.db.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manunez on 30/10/2015.
 */
public class ProviderTable {
    private ContentResolver contentResolver;
    //    DATABASE TABLE
    public static final String TABLET_NAME = "proveedor";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PROVEEDOR_ID = "idProveedor";
    public static final String COLUMN_DESCRIPCION_LOCAL = "descripcion_local";
    public static final String COLUMN_PROVEEDOR_NAME = "proveedor";
    public static final String COLUMN_RUC = "ruc";


    //    DATABASE CREATION SQL STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLET_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_PROVEEDOR_ID + " integer not null,"
            + COLUMN_PROVEEDOR_NAME + " text not null,"
            + COLUMN_DESCRIPCION_LOCAL + " text not null,"
            + COLUMN_RUC + " text not null"
            + ");";

    public ProviderTable(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS " + TABLET_NAME);
        onCreate(database);
    }

}