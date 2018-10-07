package com.jomhack.lendme.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;

import com.jomhack.lendme.R;
import com.jomhack.lendme.activity.BorrowerMainActivity;
import com.jomhack.lendme.activity.MainActivity;
import com.jomhack.lendme.constants.AppConstants;

public class PopUpUtils {

  public static  void showPopup(final Context context, String message) {
    AlertDialog.Builder builder;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
    } else {
      builder = new AlertDialog.Builder(context);
    }
    //        builder.setTitle("Delete entry")
    builder
        .setMessage(message)
        .setPositiveButton(
            android.R.string.yes,
            new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                // continue with delete
                  dialog.dismiss();
                  AppConstants.isPopup = true;
                  Intent intent = new Intent(context,BorrowerMainActivity.class);
                  intent.putExtra("fromPopUp",true);
                  context.startActivity(intent);
                  ((Activity) context).overridePendingTransition(0,0);


              }
            })
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show();
  }


  public static  void savePref(Context context,boolean isborrower){
      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
      SharedPreferences.Editor editor = preferences.edit();
      editor.putBoolean("isborrower",isborrower);
      editor.apply();

  }

  public static boolean getPref(Context context){
      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
      boolean name = preferences.getBoolean("isborrower", false);
      return name;
  }
}
