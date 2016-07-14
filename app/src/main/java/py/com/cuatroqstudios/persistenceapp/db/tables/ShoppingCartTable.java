package py.com.cuatroqstudios.persistenceapp.db.tables;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Manuel on 12/20/2015.
 */
public class ShoppingCartTable {
    private ContentResolver contentResolver;
    //    DATABASE TABLE
    public static final String TABLET_NAME = "carrito";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTO_ID = "idProducto";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String COLUMN_PROVEEDOR_ID = "idProveedor";
    public static final String COLUMN_MARCA = "marca";
    public static final String COLUMN_PORCENTAJE_IMPUESTO = "porcentaje_impuesto";
    public static final String COLUMN_PRECIO = "precio";
    public static final String COLUMN_UNIDAD_MINIMA_VENTA = "unidad_minima_venta";
    public static final String COLUMN_PRODUCTO_CANTIDAD = "cantidad_producto";
    public static final String COLUMN_PRODUCTO_COMPRA_SYNC_TIME = "sync_time";

    private static final String CREATE_TABLE = "create table "
            + TABLET_NAME
            + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_PRODUCTO_ID + " text not null,"
            + COLUMN_DESCRIPCION + " text not null,"
            + COLUMN_PROVEEDOR_ID + " text not null,"
            + COLUMN_MARCA + " text not null,"
            + COLUMN_PORCENTAJE_IMPUESTO + " text not null,"
            + COLUMN_PRECIO + " text not null,"
            + COLUMN_UNIDAD_MINIMA_VENTA + " text not null,"
            + COLUMN_PRODUCTO_CANTIDAD + " text not null,"
            + COLUMN_PRODUCTO_COMPRA_SYNC_TIME + " text"
            + ");";

    public ShoppingCartTable(ContentResolver contentResolver) {
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