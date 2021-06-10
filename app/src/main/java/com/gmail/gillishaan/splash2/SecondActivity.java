package com.gmail.gillishaan.splash2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnback = findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ActivityHome.class));
//
            }
//
//            }
        });
        AutoCompleteTextView autoCompleteTextView = autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
        ArrayAdapter arrayAdapter = new ArrayAdapter(SecondActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sweets));
        autoCompleteTextView.setAdapter(arrayAdapter);
    }
}