    package com.gmail.gillishaan.splash2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ActivityHome extends AppCompatActivity {

    Button btClickMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Add all your codes here
//        btClickMe = findViewById(R.id.btClickMe);
        btClickMe = findViewById(R.id.button);
        btClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ActivityHome.this, "You clicked me. Thank you!", Toast.LENGTH_SHORT).show();
            }  
//                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
        });

        //Happy coding :)

    }
}