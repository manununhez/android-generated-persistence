package py.com.cuatroqstudios.persistenceapp.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import py.com.cuatroqstudios.persistenceapp.db.tables.ProductTable;
import py.com.cuatroqstudios.persistenceapp.db.tables.ProviderTable;
import py.com.cuatroqstudios.persistenceapp.db.tables.ShoppingCartTable;
import py.com.cuatroqstudios.persistenceapp.utils.Tools;

/**
 * Created by manuel on 7/7/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "emarket.db";
    public static final int DATABASE_VERSION = 1;

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Tools.Logger.i("Creating database " + DATABASE_NAME + "with version " + DATABASE_VERSION);
        ProductTable.onCreate(sqLiteDatabase);
        ShoppingCartTable.onCreate(sqLiteDatabase);
        ProviderTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Tools.Logger.i("Upgrading database " + DATABASE_NAME + "from  version " + oldVersion + "to version" + newVersion);
        ProductTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
        ShoppingCartTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
        ProviderTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
