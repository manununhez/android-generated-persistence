package py.com.cuatroqstudios.persistenceapp.db.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import py.com.cuatroqstudios.persistenceapp.db.helper.MySQLiteHelper;
import py.com.cuatroqstudios.persistenceapp.utils.Tools;

/**
 * Created by manuel on 7/7/16.
 */
public class DbUtils {
    public static void exportDB(Context context) throws IOException {

        Tools.Logger.i("Exportando DB");
//      OUTPUT -/storage/emulated/0/emarket.db
//      OUTPUT -/storage/emulated/0/Android/data/py.com.cuatroqstudios.persistenceapp/files/emarket.db
        Tools.Logger.i("OUTPUT -" + Environment.getExternalStorageDirectory() + "/" + MySQLiteHelper.DATABASE_NAME);
        Tools.Logger.i("OUTPUT -" + context.getExternalFilesDir(null) + "/" + MySQLiteHelper.DATABASE_NAME);

        Tools.Logger.i("OUTPUT -" + context.getFilesDir());
//        Tools.Logger.i("OUTPUT -" + MemoryStorage.getAllStorageLocations().toString());
//        Tools.Logger.i("OUTPUT -" + MemoryStorage.getSdCardPath());
        //Open your local db as the input stream
//        InputStream myInput = context.getAssets().open("wops.local.db");
        File dbFile = context.getDatabasePath(MySQLiteHelper.DATABASE_NAME);
//        File dbFile = new File(inFileName);
        FileInputStream myInput = new FileInputStream(dbFile);
        // Path to the just created empty db
//        File outFileName = new File(Environment.getExternalStorageDirectory() + "/" + MySQLiteHelper.DATABASE_NAME, "");
        File outFileName = new File(Environment.getExternalStorageDirectory() + "/" + MySQLiteHelper.DATABASE_NAME, "");
//        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

}
