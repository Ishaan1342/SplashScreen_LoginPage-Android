package com.gmail.gillishaan.splash2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    Button btnback;
    ProgressBar pb;
    static int i = 1;

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
        pb = (ProgressBar) findViewById(R.id.progressBar);
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

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(SecondActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(SecondActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SecondActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(SecondActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SecondActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

//        signin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
//                startActivity(intent);
//            }
//        });

        password.addTextChangedListener(new TextWatcher() {


            @Override

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
                progressBar.setVisibility(View.GONE);

                // TODO Auto-generated method stub
                if (password.getText().toString().length() == 0) {

                    progressBar.setVisibility(View.GONE);


//                    progressBar.setVisibility(8);
                    password.setError("Enter your password..!");
                } else {

                    progressBar.setVisibility(View.VISIBLE);
                    caculation();

                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

        });

    }

    protected void caculation() {
        // TODO Auto-generated method stub
        String temp = password.getText().toString();
        System.out.println(i + " current password is : " + temp);
        i = i + 1;
        if (i<10) {
            password.setHint("Password Strength: Weak");
        }
        if (i<15&&i>10){
            password.setHint("Password Strength: Medium");
        }if (i>15){
            password.setHint("Password Strength: Strong");
        }

        int length = 0, uppercase = 0, lowercase = 0, digits = 0, symbols = 0, bonus = 0, requirements = 0;

        int lettersonly = 0, numbersonly = 0, cuc = 0, clc = 0;

        length = temp.length();
        for (int i = 0; i < temp.length(); i++) {
            if (Character.isUpperCase(temp.charAt(i)))
                uppercase++;
            else if (Character.isLowerCase(temp.charAt(i)))
                lowercase++;
            else if (Character.isDigit(temp.charAt(i)))
                digits++;

            symbols = length - uppercase - lowercase - digits;

        }

        for (int j = 1; j < temp.length() - 1; j++) {

            if (Character.isDigit(temp.charAt(j)))
                bonus++;

        }

        for (int k = 0; k < temp.length(); k++) {

            if (Character.isUpperCase(temp.charAt(k))) {
                k++;

                if (k < temp.length()) {

                    if (Character.isUpperCase(temp.charAt(k))) {

                        cuc++;
                        k--;

                    }

                }

            }

        }

        for (int l = 0; l < temp.length(); l++) {

            if (Character.isLowerCase(temp.charAt(l))) {
                l++;

                if (l < temp.length()) {

                    if (Character.isLowerCase(temp.charAt(l))) {

                        clc++;
                        l--;

                    }

                }

            }

        }

        System.out.println("length" + length);
        System.out.println("uppercase" + uppercase);
        System.out.println("lowercase" + lowercase);
        System.out.println("digits" + digits);
        System.out.println("symbols" + symbols);
        System.out.println("bonus" + bonus);
        System.out.println("cuc" + cuc);
        System.out.println("clc" + clc);

        if (length > 7) {
            requirements++;
        }

        if (uppercase > 0) {
            requirements++;
        }

        if (lowercase > 0) {
            requirements++;
        }

        if (digits > 0) {
            requirements++;
        }

        if (symbols > 0) {
            requirements++;
        }

        if (bonus > 0) {
            requirements++;
        }

        if (digits == 0 && symbols == 0) {
            lettersonly = 1;
        }

        if (lowercase == 0 && uppercase == 0 && symbols == 0) {
            numbersonly = 1;
        }

        int Total = (length * 4) + ((length - uppercase) * 2)
                + ((length - lowercase) * 2) + (digits * 4) + (symbols * 6)
                + (bonus * 2) + (requirements * 2) - (lettersonly * length*2)
                - (numbersonly * length*3) - (cuc * 2) - (clc * 2);

        System.out.println("Total" + Total);

        if(Total<30){
            pb.setProgress(Total-15);
        }

        else if (Total>=40 && Total <50)
        {
            pb.setProgress(Total-20);
        }

        else if (Total>=56 && Total <70)
        {
            pb.setProgress(Total-25);
        }

        else if (Total>=76)
        {
            pb.setProgress(Total-30);
        }
        else{
            pb.setProgress(Total-20);
        }

    }

}






