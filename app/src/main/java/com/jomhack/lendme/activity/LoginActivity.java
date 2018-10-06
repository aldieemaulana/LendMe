package com.jomhack.lendme.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
import com.jomhack.lendme.R;
import com.jomhack.lendme.utils.AppUtils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

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
