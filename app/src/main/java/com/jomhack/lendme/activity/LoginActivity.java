package com.jomhack.lendme.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.hbb20.CountryCodePicker;
import com.jomhack.lendme.App;
import com.jomhack.lendme.R;
import com.jomhack.lendme.base.BaseActivity;
import com.jomhack.lendme.constants.AppConstants;
import com.jomhack.lendme.utils.Utils;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

/** A login screen that offers login via email/password. */
public class LoginActivity extends BaseActivity {

  private static final String TAG = "PhoneAuth";

  private EditText phoneText;

  private EditText codeText;
  private Button verifyButton;
  private Button sendButton;
  private Button resendButton;
  private Button signoutButton;
  private TextView statusText;
  private TextView textError;

  @BindView(R.id.parentLayout)
  CoordinatorLayout cordinate_main;

  private String number;

  private String phoneVerificationId;
  private PhoneAuthProvider.OnVerificationStateChangedCallbacks verificationCallbacks;
  private PhoneAuthProvider.ForceResendingToken resendToken;

  CountryCodePicker ccp;
  private Context context;
  public FirebaseAuth fbAuth;
  public RadioGroup rGroup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);

    phoneText = findViewById(R.id.phoneText);
    codeText = findViewById(R.id.codeText);
    verifyButton = findViewById(R.id.verifyButton);
    sendButton = findViewById(R.id.sendButton);
    resendButton = findViewById(R.id.resendButton);
    signoutButton = findViewById(R.id.signoutButton);
    statusText = findViewById(R.id.statusText);
    textError = findViewById(R.id.textError);
    rGroup = (RadioGroup) findViewById(R.id.radioSelection);

    ccp = findViewById(R.id.ccp);
    ccp.registerCarrierNumberEditText(phoneText);

    verifyButton.setEnabled(false);
    resendButton.setEnabled(false);
    signoutButton.setEnabled(false);
    statusText.setText("Signed Out");

    TextView title = findViewById(R.id.textTitleToolbar);

    context = this;
    title.setText(context.getResources().getString(R.string.title_activity_login));
    Companion.initData(context);
    fbAuth = App.Companion.getFirebaseAuth();

    verifyButton.setVisibility(View.GONE);
    codeText.setEnabled(false);
    resendButton.setVisibility(View.GONE);

    phoneText.addTextChangedListener(
        new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

          @Override
          public void onTextChanged(CharSequence s, int start, int before, int count) {}

          @Override
          public void afterTextChanged(Editable s) {
            if (s.toString().length() <= 2) {
              sendButton.setVisibility(View.VISIBLE);
              sendButton.setEnabled(true);
              verifyButton.setVisibility(View.GONE);
              codeText.setEnabled(false);
              resendButton.setVisibility(View.GONE);
            }
          }
        });

    setListener();
  }

  private void setListener() {

    verifyButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            Utils.Companion.hideKeyboard((Activity) context);
            if (Utils.Companion.isOnline(context)) {
              if (codeText != null && codeText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              verifyCode();
            } else setError(getString(R.string.text_check_internet));
          }
        });

    sendButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            Utils.Companion.hideKeyboard((Activity) context);
            if (Utils.Companion.isOnline(context)) {
              if (phoneText != null && phoneText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              sendCode();
            } else setError(getString(R.string.text_check_internet));
          }
        });

    resendButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            Utils.Companion.hideKeyboard((Activity) context);
            if (Utils.Companion.isOnline(context)) {
              if (phoneText != null && phoneText.getText().toString().isEmpty()) {
                phoneText.setError(getString(R.string.text_phone_no_empty));
                return;
              }
              resendCode();
            } else setError(getString(R.string.text_check_internet));
          }
        });

    signoutButton.setOnClickListener(
        new OnClickListener() {
          @Override
          public void onClick(View view) {
            Utils.Companion.hideKeyboard((Activity) context);
            if (Utils.Companion.isOnline(context)) signOut();
            else setError(getString(R.string.text_check_internet));
          }
        });

    rGroup.setOnCheckedChangeListener(
        new RadioGroup.OnCheckedChangeListener() {
          public void onCheckedChanged(RadioGroup group, int checkedId) {
            // This will get the radiobutton that has changed in its check state
            RadioButton checkedRadioButton = (RadioButton) group.findViewById(checkedId);
            // This puts the value (true/false) into the variable
            boolean isChecked = checkedRadioButton.isChecked();
            // If the radiobutton that has changed in check state is now checked...
            if (isChecked && checkedId == R.id.radioLender ) {
              AppConstants.isBorrower = false;
              // Changes the textview's text to "Checked: example radiobutton text"
            } else if (isChecked && checkedId == R.id.radioBorrower) {
              AppConstants.isBorrower = true;
              // Changes the textview's text to "Checked: example radiobutton text"
            }
          }
        });
  }

  public void sendCode() {

    Companion.showProgress();
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
            Companion.hideProgress();
            signInWithPhoneAuthCredential(credential);
          }

          @Override
          public void onVerificationFailed(FirebaseException e) {
            Companion.hideProgress();
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
              // Invalid request
              setError("Invalid credential: " + e.getLocalizedMessage());
            } else if (e instanceof FirebaseTooManyRequestsException) {
              // SMS quota exceeded
              setError("SMS Quota exceeded.");
            }
          }

          @Override
          public void onCodeSent(
              String verificationId, PhoneAuthProvider.ForceResendingToken token) {
            Companion.hideProgress();
            phoneVerificationId = verificationId;
            resendToken = token;
            verifyButton.setEnabled(true);
            sendButton.setEnabled(false);
            resendButton.setEnabled(true);

            verifyButton.setVisibility(View.VISIBLE);
            codeText.setEnabled(true);
            sendButton.setVisibility(View.GONE);
            resendButton.setVisibility(View.VISIBLE);
            codeText.requestFocus();
          }
        };
  }

  private void setError(String s) {
    textError.setText(s);
  }

  public void verifyCode() {
    Companion.showProgress();
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
                Companion.showProgress();
                if (task.isSuccessful()) {

                  Companion.hideProgress();
                  FirebaseUser user = task.getResult().getUser();
                  String phoneNumber = user.getPhoneNumber();

                  if (AppConstants.isBorrower) {
                    Intent intent = new Intent(LoginActivity.this, BorrowerMainActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);
                  } else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("phone", phoneNumber);
                    startActivity(intent);

                  }
                  finish();
                } else {
                  Companion.hideProgress();
                  if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                    setError(" The verification code entered was invalid");
                  }
                }
              }
            });
  }

  public void resendCode() {
    Companion.showProgress();
    number = ccp.getFullNumberWithPlus();

    setUpVerificatonCallbacks();

    PhoneAuthProvider.getInstance()
        .verifyPhoneNumber(number, 60, TimeUnit.SECONDS, this, verificationCallbacks, resendToken);
  }

  public void signOut() {
    Companion.showProgress();
    fbAuth.signOut();
    statusText.setText("Signed Out");
    signoutButton.setEnabled(false);
    sendButton.setEnabled(true);
    Companion.hideProgress();
  }
}
