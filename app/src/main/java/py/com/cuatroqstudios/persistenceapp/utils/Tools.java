package py.com.cuatroqstudios.persistenceapp.utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;

/**
 * Created by Andres on 06/12/2015.
 */
public class Tools {

//    public static final String TAG = BuildConfig.APPLICATION_ID;
    public static final String TAG = "py.com.persistenceapp";


    //ProgressDialog generico
    public static ProgressDialog getProgressDialog(Context context,
                                                   String title, String text) {
        ProgressDialog pd = new ProgressDialog(context);
        pd.setTitle(title);
        pd.setMessage(text);
        pd.setCancelable(false);

        return pd;
    }


    //AlertDialog generico
    public static AlertDialog.Builder getAlertDialog(final Context context,
                                                     final String title, String text, String buttonName, DialogInterface.OnClickListener listener,
                                                     final String errCode) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setCancelable(false);

        //Por si no se le asigno ninguna accion al boton.
        if (listener == null) {
            listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //DO NOTHING
                }
            };
        }
        dialog.setPositiveButton(buttonName, listener);
        dialog.setMessage(text);

        return dialog;
    }

    // Alert dialog con opcion generico
    public static AlertDialog.Builder getAlertDialogWithChoice(Context context,
                                                               final String title, final String text, final String positiveButton,
                                                               DialogInterface.OnClickListener positiveListener, final String negativeButton,
                                                               DialogInterface.OnCancelListener listener) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(text);
        dialog.setPositiveButton(positiveButton, positiveListener);
        dialog.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setOnCancelListener(listener);
        return dialog;
    }


    //Logger
    public static class Logger {
        public static void i(String message) {
            Log.i(TAG, message);
        }

        public static void w(String message) {
            Log.w(TAG, message);
        }

        public static void e(String message) {
            if (message == null) {
                message = "Sin mensaje";
            }
            Log.e(TAG, message);
        }

        public static void d(String message) {
            Log.d(TAG, message);
        }
    }


    public static  boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }


    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

}

