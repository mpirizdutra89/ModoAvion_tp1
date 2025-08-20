package com.mpd.tp1modoavion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;


public class ModoAvion extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        if (intent != null && Intent.ACTION_AIRPLANE_MODE_CHANGED.equals(intent.getAction())) {
            boolean modoAvionOn = intent.getBooleanExtra("state", false);
            String msj;

            if (modoAvionOn) {
                msj = "Modo Avión Activado";
                Log.d("ModoAvion", msj);
                Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
            } else {
                msj = "Modo Avión Desactivado";
                Log.d("ModoAvion", msj);
               Toast.makeText(context, msj, Toast.LENGTH_LONG).show();


                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String numero = "2664004896";

                            Uri telefono = Uri.parse("tel:" + numero);
                            //Intent llamar = new Intent(Intent.ACTION_DIAL, telefono); //solo abre la app de llamada con el numero precargado
                            Intent llamar = new Intent(Intent.ACTION_CALL, telefono); //este deveria llamar

                            llamar.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(llamar);
                        } catch (Exception e) {
                            Toast.makeText(context, "falla: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            Log.e("ModoAvion", "Error en la llamada: " + e.getMessage());
                        }
                    }
                }, 2000);
            }
        }
    }
}