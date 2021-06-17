package com.gmail.gillishaan.splash2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button btnback;

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
//        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

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


//        AutoCompleteTextView autoCompleteTextView = autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
//        ArrayAdapter arrayAdapter = new ArrayAdapter(SecondActivity.this,
//                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sweets));
//        autoCompleteTextView.setAdapter(arrayAdapter);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(SecondActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert==true){
                                Toast.makeText(SecondActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),ActivityHome.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(SecondActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(SecondActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SecondActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
//                startActivity(intent);
//            }
//        });
    }
}