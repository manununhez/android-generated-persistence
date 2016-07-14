package py.com.cuatroqstudios.persistenceapp.filesmanager.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import py.com.cuatroqstudios.persistenceapp.utils.Tools;

/**
 * Created by manuel on 7/7/16.
 */
public class MyFilesHelper {

    public static byte[] getBytesFromFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            throw new IOException("Archivo muy grande, no se puede leer todo "
                    + "el archivo " + file.getName());
        }

        byte[] bytes = new byte[(int) length];
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.
                length - offset)) >= 0)
            offset += numRead;
        if (is != null)
            is.close();
        if (offset < bytes.length)
            throw new IOException("No se puede leer todo el archivo: " + file.
                    getName());
        return bytes;
    }

    public static void writeToFile(String data, String fileName, String extension) {
        try {
            File myFile = new File("/sdcard/"+fileName+"."+extension);

            if (!myFile.exists()) {
                boolean newFile = myFile.createNewFile();
            }

            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();

            fOut.close();
        } catch (Exception e) {
            Tools.Logger.d("Ocurrio un error al escribir el archivo ");
        }

    }

    public static Bitmap Base64ToBitmap(String bitmap) {
        byte[] decodedString = Base64.decode(bitmap, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

}
