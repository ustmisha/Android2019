package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, new IntentFilter("MainActivity"));

        final Button button = findViewById(R.id.MainButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editText = findViewById(R.id.TextEdit);
                int number = Integer.valueOf(editText.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MyIntentService.class);
                intent.putExtra("number", number);

                startService(intent);

                button.setEnabled(false);
            }
        });
    }

    BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String numbers = intent.getStringExtra("prime_numbers");
            int numbersAmount = intent.getIntExtra("amount", 0);

            TextView allNumbersTextView = findViewById(R.id.NumbersTextView);
            allNumbersTextView.setText(numbers);

            TextView amountTextView = findViewById(R.id.MainTextView);
            amountTextView.setText(Integer.toString(numbersAmount) + " numbers found");

            Button button = findViewById(R.id.MainButton);

            button.setEnabled(true);
        }
    };
}