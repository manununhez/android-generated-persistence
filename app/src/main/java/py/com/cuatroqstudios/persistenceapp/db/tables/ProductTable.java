package py.com.cuatroqstudios.persistenceapp.db.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manunez on 30/10/2015.
 */
public class ProductTable {
    private ContentResolver contentResolver;
    //    DATABASE TABLE
    public static final String TABLET_NAME = "producto";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTO_ID = "idProducto";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_PRECIO = "precio";
    public static final String COLUMN_CODIGO = "codigo";
    public static final String COLUMN_PROVEEDOR_ID= "idProveedor";


    //    DATABASE CREATION SQL STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLET_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_PRODUCTO_ID + " integer not null,"
            + COLUMN_NOMBRE + " text not null,"
            + COLUMN_PRECIO + " text not null,"
            + COLUMN_CODIGO + " text not null,"
            + COLUMN_PROVEEDOR_ID + " integer not null"
            + ");";

    public ProductTable(ContentResolver contentResolver) {
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
