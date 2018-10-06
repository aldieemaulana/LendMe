package com.jomhack.lendme.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.util.EventLogTags;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.jomhack.lendme.R;

public class AppUtils {

  public static void showSnackBar(String result, View rootLayout) {
    final Snackbar snackBar = Snackbar.make(rootLayout, result, Snackbar.LENGTH_LONG);

    snackBar.setAction(
        "Dismiss",
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            snackBar.dismiss();
          }
        });
    snackBar.show();
  }

  public static boolean isOnline(Context context) {
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo netInfo = cm.getActiveNetworkInfo();
    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
      return true;
    } else {

    }
    return false;
  }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

}
