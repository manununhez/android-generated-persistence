package py.com.cuatroqstudios.persistenceapp.db.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by manunez on 30/10/2015.
 */
public class ImageProductTable {
    private ContentResolver contentResolver;
    //    DATABASE TABLE
    public static final String TABLET_NAME = "imagenproducto";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_IMAGES_ID = "idProductoImagen";
    public static final String COLUMN_IMAGEN = "imagen";
    public static final String COLUMN_PRODUCTO_ID = "idProducto";
    public static final String COLUMN_IMAGEN_SRC= "img_src";


    //    DATABASE CREATION SQL STATEMENT
    private static final String CREATE_TABLE = "create table "
            + TABLET_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_IMAGES_ID + " text not null,"
            + COLUMN_IMAGEN + " text not null," //BLOB
            + COLUMN_PRODUCTO_ID + " text not null,"
            + COLUMN_IMAGEN_SRC + " text not null"
            + ");";

    public ImageProductTable(ContentResolver contentResolver) {
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
