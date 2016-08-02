package py.com.cuatroqstudios.persistenceapp.db.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import py.com.cuatroqstudios.persistenceapp.BuildConfig;
import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.db.tables.ProductTable;
import py.com.cuatroqstudios.persistenceapp.db.tables.ProviderTable;
import py.com.cuatroqstudios.persistenceapp.db.tables.ShoppingCartTable;
import py.com.cuatroqstudios.persistenceapp.utils.Tools;

/**
 * Created by manuel on 7/7/16.
 */
public class MyContentProvider extends ContentProvider {
    private static final int PRODUCTS = 10;
    private static final int PRODUCTS_ID = 20;
    private static final int PROVEEDOR = 30;
    private static final int PROVEEDOR_ID = 40;
    private static final int CARRITO = 50;
    private static final int CARRITO_ID = 60;
    private static final int IMAGEN = 70;
    private static final int IMAGEN_ID = 80;


    private static final String AUTHORITY = BuildConfig.APPLICATION_ID + ".contentprovider";

    private static final String BASE_PATH_PRODUCT = ProductTable.TABLET_NAME;
    private static final String BASE_PATH_PROVEEDOR = ProviderTable.TABLET_NAME;
    private static final String BASE_PATH_CARRITO = ShoppingCartTable.TABLET_NAME;
//    private static final String BASE_PATH_IMAGEN = ImageProductTable.TABLET_NAME;

    public static final Uri CONTENT_URI_PRODUCTO = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_PRODUCT);
    public static final Uri CONTENT_URI_PROVEEDOR = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_PROVEEDOR);
    public static final Uri CONTENT_URI_CARRITO = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CARRITO);
//    public static final Uri CONTENT_URI_IMAGEN = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_IMAGEN);

    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    private MySQLiteHelper databaseHelper;
    private ContentResolver contentResolver;

    static {
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_PRODUCT, PRODUCTS);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_PRODUCT + "/#", PRODUCTS_ID);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_PROVEEDOR, PROVEEDOR);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_PROVEEDOR + "/#", PROVEEDOR_ID);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CARRITO, CARRITO);
        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_CARRITO + "/#", CARRITO_ID);
//        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_IMAGEN, IMAGEN);
//        URI_MATCHER.addURI(AUTHORITY, BASE_PATH_IMAGEN + "/#", IMAGEN_ID);
    }


    @Override
    public boolean onCreate() {
        databaseHelper = new MySQLiteHelper(getContext());
        contentResolver = getContext().getContentResolver();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Tools.Logger.i("querying ContentProvider\n" +
                "Uri: " + uri.toString() + "\n" +
                "Projection: " + Arrays.toString(projection) + "\n" +
                "Selection: " + selection + "\n" +
                "SelectionArgs: " + Arrays.toString(selectionArgs) + "\n" +
                "SortOrder: " + sortOrder);

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();

        int uriType = URI_MATCHER.match(uri);

        // check if the caller has requested a column which does not exists
        checkColumns(projection, uriType);

        switch (uriType) {
            case PRODUCTS:
                sqLiteQueryBuilder.setTables(BASE_PATH_PRODUCT);
                Tools.Logger.i("uriType is PRODUCT");
                break;

            case PRODUCTS_ID:
                sqLiteQueryBuilder.setTables(BASE_PATH_PRODUCT);
//                adding the ID to the original query
                sqLiteQueryBuilder.appendWhere("_id =" + uri.getLastPathSegment());
                break;

            case PROVEEDOR:
                sqLiteQueryBuilder.setTables(BASE_PATH_PROVEEDOR);
                Tools.Logger.i("uriType is PROVEEDOR");
                break;

            case PROVEEDOR_ID:
                sqLiteQueryBuilder.setTables(BASE_PATH_PROVEEDOR);
//                adding the ID to the original query
                sqLiteQueryBuilder.appendWhere("_id =" + uri.getLastPathSegment());
                break;

            case CARRITO:
                sqLiteQueryBuilder.setTables(BASE_PATH_CARRITO);
                Tools.Logger.i("uriType is PROVEEDOR");
                break;

            case CARRITO_ID:
                sqLiteQueryBuilder.setTables(BASE_PATH_CARRITO);
//                adding the ID to the original query
                sqLiteQueryBuilder.appendWhere("_id =" + uri.getLastPathSegment());
                break;

//            case IMAGEN:
//                sqLiteQueryBuilder.setTables(BASE_PATH_IMAGEN);
//                Tools.Logger.i("uriType is IMAGEN");
//                break;
//
//            case IMAGEN_ID:
//                sqLiteQueryBuilder.setTables(BASE_PATH_IMAGEN);
////                adding the ID to the original query
//                sqLiteQueryBuilder.appendWhere("_id =" + uri.getLastPathSegment());
//                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uri);
        }

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = sqLiteQueryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(contentResolver, uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Tools.Logger.i("inserting ContentProvider\n" +
                "Uri: " + uri.toString() +
                "ContentValues: " + values.toString());

        int uriType = URI_MATCHER.match(uri);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long id = 0;
        Uri result;
        switch (uriType) {
            case PRODUCTS:
                id = db.insert(BASE_PATH_PRODUCT, null, values);
                result = Uri.parse(BASE_PATH_PRODUCT + "/" + id);
                break;

            case PROVEEDOR:
                id = db.insert(BASE_PATH_PROVEEDOR, null, values);
                result = Uri.parse(BASE_PATH_PROVEEDOR + "/" + id);
                break;

            case CARRITO:
                id = db.insert(BASE_PATH_CARRITO, null, values);
                result = Uri.parse(BASE_PATH_CARRITO + "/" + id);
                break;

//            case IMAGEN:
//                id = db.insert(BASE_PATH_IMAGEN, null, values);
//                result = Uri.parse(BASE_PATH_IMAGEN + "/" + id);
//                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uri);

        }

        contentResolver.notifyChange(uri, null);
        return result;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Tools.Logger.i("deleting ContentProvider\n" +
                "Uri: " + uri.toString() + "\n" +
                "Selection: " + selection + "\n" +
                "SelectionArgs: " + Arrays.toString(selectionArgs));

        int uriType = URI_MATCHER.match(uri);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowsDeleted = 0;
        String id;

        switch (uriType) {
            case PRODUCTS:
                rowsDeleted = db.delete(BASE_PATH_PRODUCT, selection, selectionArgs);
                break;
            case PRODUCTS_ID:
                id = uri.getLastPathSegment();
                rowsDeleted = db.delete(BASE_PATH_PRODUCT, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(BASE_PATH_PRODUCT, "_id = " + id, null);
                } else {
                    rowsDeleted = db.delete(BASE_PATH_PRODUCT, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;

            case PROVEEDOR:
                rowsDeleted = db.delete(BASE_PATH_PROVEEDOR, selection, selectionArgs);
                break;

            case PROVEEDOR_ID:
                id = uri.getLastPathSegment();
                rowsDeleted = db.delete(BASE_PATH_PROVEEDOR, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(BASE_PATH_PROVEEDOR, "_id = " + id, null);
                } else {
                    rowsDeleted = db.delete(BASE_PATH_PROVEEDOR, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;
            case CARRITO:
                rowsDeleted = db.delete(BASE_PATH_CARRITO, selection, selectionArgs);
                break;

            case CARRITO_ID:
                id = uri.getLastPathSegment();
                rowsDeleted = db.delete(BASE_PATH_CARRITO, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(BASE_PATH_CARRITO, "_id = " + id, null);
                } else {
                    rowsDeleted = db.delete(BASE_PATH_CARRITO, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;

//            case IMAGEN:
//                rowsDeleted = db.delete(BASE_PATH_IMAGEN, selection, selectionArgs);
//                break;
//
//            case IMAGEN_ID:
//                id = uri.getLastPathSegment();
//                rowsDeleted = db.delete(BASE_PATH_IMAGEN, selection, selectionArgs);
//
//                if (TextUtils.isEmpty(selection)) {
//                    rowsDeleted = db.delete(BASE_PATH_IMAGEN, "_id = " + id, null);
//                } else {
//                    rowsDeleted = db.delete(BASE_PATH_IMAGEN, "_id = " + id + " and " + selection, selectionArgs);
//                }
//                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uri);

        }

        contentResolver.notifyChange(uri, null);
        Tools.Logger.i("rowsDeleted: " + rowsDeleted);

        return rowsDeleted;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Tools.Logger.i("updating ContentProvider\n" +
                "Uri: " + uri.toString() + "\n" +
                "ContentValues: " + values.toString() + "\n" +
                "Selection: " + selection + "\n" +
                "SelectionArgs: " + Arrays.toString(selectionArgs));

        int uriType = URI_MATCHER.match(uri);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        int rowsUpdated = 0;
        String id;

        switch (uriType) {
            case PRODUCTS:
                rowsUpdated = db.update(BASE_PATH_PRODUCT, values, selection, selectionArgs);
                break;
            case PRODUCTS_ID:
                id = uri.getLastPathSegment();
                rowsUpdated = db.delete(BASE_PATH_PRODUCT, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(BASE_PATH_PRODUCT, values, "_id = " + id, null);
                } else {
                    rowsUpdated = db.update(BASE_PATH_PRODUCT, values, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;

            case PROVEEDOR:
                rowsUpdated = db.update(BASE_PATH_PROVEEDOR, values, selection, selectionArgs);
                break;
            case PROVEEDOR_ID:
                id = uri.getLastPathSegment();
                rowsUpdated = db.delete(BASE_PATH_PROVEEDOR, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(BASE_PATH_PROVEEDOR, values, "_id = " + id, null);
                } else {
                    rowsUpdated = db.update(BASE_PATH_PROVEEDOR, values, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;

            case CARRITO:
                rowsUpdated = db.update(BASE_PATH_CARRITO, values, selection, selectionArgs);
                break;
            case CARRITO_ID:
                id = uri.getLastPathSegment();
                rowsUpdated = db.delete(BASE_PATH_CARRITO, selection, selectionArgs);

                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = db.update(BASE_PATH_CARRITO, values, "_id = " + id, null);
                } else {
                    rowsUpdated = db.update(BASE_PATH_CARRITO, values, "_id = " + id + " and " + selection, selectionArgs);
                }
                break;

//            case IMAGEN:
//                rowsUpdated = db.update(BASE_PATH_IMAGEN, values, selection, selectionArgs);
//                break;
//            case IMAGEN_ID:
//                id = uri.getLastPathSegment();
//                rowsUpdated = db.delete(BASE_PATH_IMAGEN, selection, selectionArgs);
//
//                if (TextUtils.isEmpty(selection)) {
//                    rowsUpdated = db.update(BASE_PATH_IMAGEN, values, "_id = " + id, null);
//                } else {
//                    rowsUpdated = db.update(BASE_PATH_IMAGEN, values, "_id = " + id + " and " + selection, selectionArgs);
//                }
//                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uri);

        }

        contentResolver.notifyChange(uri, null);
        Tools.Logger.i("rowsUpdated: " + rowsUpdated);

        return rowsUpdated;
    }

    private void checkColumns(String[] projection, int uriType) {
        ArrayList<String> available = new ArrayList<String>();

        switch (uriType) {
            case PRODUCTS:
                available.add(ProductTable.COLUMN_NOMBRE);
                available.add(ProductTable.COLUMN_CODIGO);
                available.add(ProductTable.COLUMN_PRECIO);
                available.add(ProductTable.COLUMN_PRODUCTO_ID);
                available.add(ProductTable.COLUMN_PROVEEDOR_ID);
                break;

            case PROVEEDOR:
                available.add(ProviderTable.COLUMN_DESCRIPCION_LOCAL);
                available.add(ProviderTable.COLUMN_PROVEEDOR_ID);
                available.add(ProviderTable.COLUMN_PROVEEDOR_NAME);
                available.add(ProviderTable.COLUMN_RUC);
                break;

            case CARRITO:
                available.add(ShoppingCartTable.COLUMN_PORCENTAJE_IMPUESTO);
                available.add(ShoppingCartTable.COLUMN_PRODUCTO_CANTIDAD);
                available.add(ShoppingCartTable.COLUMN_DESCRIPCION);
                available.add(ShoppingCartTable.COLUMN_MARCA);
                available.add(ShoppingCartTable.COLUMN_PRECIO);
                available.add(ShoppingCartTable.COLUMN_PRODUCTO_COMPRA_SYNC_TIME);
                available.add(ShoppingCartTable.COLUMN_UNIDAD_MINIMA_VENTA);
                available.add(ShoppingCartTable.COLUMN_PRODUCTO_ID);
                available.add(ShoppingCartTable.COLUMN_PROVEEDOR_ID);
                break;

//            case IMAGEN:
//                available.add(ImageProductTable.COLUMN_IMAGES_ID);
//                available.add(ImageProductTable.COLUMN_IMAGEN);
//                available.add(ImageProductTable.COLUMN_PRODUCTO_ID);
//                available.add(ImageProductTable.COLUMN_IMAGEN_SRC);
//                break;
            default:
                throw new IllegalArgumentException("Unknow URI:" + uriType);
        }

        if (projection != null) {
            HashSet<String> requestColumns = new HashSet<>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<>(available);
            //verificar si todas las columnas solicitadas existen
            if (!availableColumns.containsAll(requestColumns)) {
                throw new IllegalArgumentException("Unknow columns in projection");
            }
        }
    }
}
