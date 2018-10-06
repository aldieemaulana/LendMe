package com.jomhack.lendme.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;
import com.jomhack.lendme.R;
import com.jomhack.lendme.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

/** A login screen that offers login via email/password. */
public class LoginActivity extends AppCompatActivity {

  private static final String TAG = "PhoneAuth";

  private EditText phoneText;

  private EditText codeText;
  private Button verifyButton;
  private Button sendButton;
  private Button resendButton;
  private Button signoutButton;
  private TextView statusText;

  @BindView(R.id.cordinate_main)
  CoordinatorLayout cordinate_main;

  private String number;

  private String phoneVerificationId;
  private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
  private PhoneAuthProvider.ForceResendingToken resendToken;

  private FirebaseAuth fbAuth;
  CountryCodePicker ccp;
  private Context context;
  private ProgressDialog progress;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);


    phoneText = (EditText) findViewById(R.id.phoneText);
    codeText = (EditText) findViewById(R.id.codeText);
    verifyButton = (Button) findViewById(R.id.verifyButton);
    sendButton = (Button) findViewById(R.id.sendButton);
    resendButton = (Button) findViewById(R.id.resendButton);
    signoutButton = (Button) findViewById(R.id.signoutButton);
    statusText = (TextView) findViewById(R.id.statusText);

    ccp = (CountryCodePicker) findViewById(R.id.ccp);
    ccp.registerCarrierNumberEditText(phoneText);

    verifyButton.setEnabled(false);
    resendButton.setEnabled(false);
    signoutButton.setEnabled(false);
    statusText.setText("Signed Out");

    fbAuth = FirebaseAuth.getInstance();

    context = this;

    if(fbAuth !=null && fbAuth.getCurrentUser()!=null &&  fbAuth.getCurrentUser().getPhoneNumber()!=null){
      Intent intent = new Intent(LoginActivity.this, MessagingActivity.class);
      intent.putExtra("phone", fbAuth.getCurrentUser().getPhoneNumber());
      startActivity(intent);
      finish();

    }

    progress = new ProgressDialog(this);
    progress.setMessage("Please wait...");
    progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progress.setIndeterminate(true);
    progress.setProgress(0);
    verifyButton.setVisibility(View.INVISIBLE);
    codeText.setVisibility(View.INVISIBLE);
    resendButton.setVisibility(View.INVISIBLE);


    setListener();
  }

  private void setListener() {

    verifyButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            AppUtils.hideSoftKeyboard((Activity) context);
            if (AppUtils.isOnline(context)) {
              if (codeText != null && codeText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              verifyCode();
            } else AppUtils.showSnackBar(getString(R.string.text_check_internet), cordinate_main);
          }
        });

    sendButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            AppUtils.hideSoftKeyboard((Activity) context);
            if (AppUtils.isOnline(context)) {
              if (phoneText != null && phoneText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              sendCode();
            } else AppUtils.showSnackBar(getString(R.string.text_check_internet), cordinate_main);
          }
        });

    resendButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            AppUtils.hideSoftKeyboard((Activity) context);
            if (AppUtils.isOnline(context)) {
              if (phoneText != null && phoneText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              resendCode();
            } else AppUtils.showSnackBar(getString(R.string.text_check_internet), cordinate_main);
          }
        });

    signoutButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            AppUtils.hideSoftKeyboard((Activity) context);
            if (AppUtils.isOnline(context)) signOut();
            else AppUtils.showSnackBar(getString(R.string.text_check_internet), cordinate_main);
          }
        });
  }

  public void sendCode() {

    progress.show();
    number = ccp.getFullNumberWithPlus();

    setUpVerificatonCallbacks();

    PhoneAuthProvider.getInstance()
        .verifyPhoneNumber(
            number, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this, // Activity (for callback binding)
            verificationCallbacks);
  }

  private void setUpVerificatonCallbacks() {

    verificationCallbacks =
        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

          @Override
          public void onVerificationCompleted(PhoneAuthCredential credential) {
            progress.dismiss();
            signoutButton.setEnabled(true);
            statusText.setText("Signed In");
            resendButton.setEnabled(false);
            verifyButton.setEnabled(false);
            codeText.setText("");
            signInWithPhoneAuthCredential(credential);
          }

          @Override
          public void onVerificationFailed(FirebaseException e) {
            progress.dismiss();
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
              // Invalid request
              Log.d(TAG, "Invalid credential: " + e.getLocalizedMessage());
            } else if (e instanceof FirebaseTooManyRequestsException) {
              // SMS quota exceeded
              Log.d(TAG, "SMS Quota exceeded.");
            }
          }

          @Override
          public void onCodeSent(
              String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            progress.dismiss();
            phoneVerificationId = verificationId;
            resendToken = token;
            verifyButton.setEnabled(true);
            sendButton.setEnabled(false);
            resendButton.setEnabled(true);

            verifyButton.setVisibility(View.VISIBLE);
            codeText.setVisibility(View.VISIBLE);
            sendButton.setVisibility(View.GONE);
            resendButton.setVisibility(View.VISIBLE);
          }
        };
  }

  public void verifyCode() {
    progress.show();
    String code = codeText.getText().toString();

    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(phoneVerificationId, code);
    signInWithPhoneAuthCredential(credential);
  }

  private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
    fbAuth
        .signInWithCredential(credential)
        .addOnCompleteListener(
            this,
            new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                progress.dismiss();
                if (task.isSuccessful()) {
                  signoutButton.setEnabled(true);
                  codeText.setText("");
                  statusText.setText("Signed In");
                  resendButton.setEnabled(false);
                  verifyButton.setEnabled(false);
                  FirebaseUser user = task.getResult().getUser();
                  String phoneNumber = user.getPhoneNumber();

                  Intent intent = new Intent(LoginActivity.this, MessagingActivity.class);
                  intent.putExtra("phone", phoneNumber);
                  startActivity(intent);
                  finish();

                } else {
                  if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    // The verification code entered was invalid
                  }
                }
              }
            });
  }

  public void resendCode() {
    progress.show();
    number = ccp.getFullNumberWithPlus();

    setUpVerificatonCallbacks();

    PhoneAuthProvider.getInstance()
        .verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, verificationCallbacks, resendToken);
  }

  public void signOut() {
    progress.show();
    fbAuth.signOut();
    statusText.setText("Signed Out");
    signoutButton.setEnabled(false);
    sendButton.setEnabled(true);
    progress.dismiss();

  }
}
