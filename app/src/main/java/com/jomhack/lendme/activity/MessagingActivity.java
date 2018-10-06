package com.jomhack.lendme.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.jomhack.lendme.R;

public class MessagingActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Intent intent = getIntent();
        String phoneNumber = intent.getExtras().getString("phone");

        textView = (TextView) findViewById(R.id.myTextView);

        textView.setText("This is the authenticated phone number " + phoneNumber);
    }
}
