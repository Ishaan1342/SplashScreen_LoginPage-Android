    package com.gmail.gillishaan.splash2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class ActivityHome extends AppCompatActivity {

    Button btClickMe;
    Button btClickMe2;
    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        username = (EditText) findViewById(R.id.lgname);
        password = (EditText) findViewById(R.id.lgnpw);
        btnlogin = (Button) findViewById(R.id.Lgnbtn);
        DB = new DBHelper(this);

        //Add all your codes here
//        btClickMe = findViewById(R.id.btClickMe);
        btClickMe = findViewById(R.id.button);

        btClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityHome.this, SecondActivity.class));
//                Toast.makeText(ActivityHome.this, "You clicked me. Thank you!", Toast.LENGTH_SHORT).show();
            }  
//                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
        });
        btClickMe2 = findViewById(R.id.Lgnbtn);
        btClickMe2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(ActivityHome.this, SecondActivity.class));
                Toast.makeText(ActivityHome.this, "You clicked me. Thank you!", Toast.LENGTH_SHORT).show();
            }
//                Snackbar.make(v, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
        //Happy coding :)
//            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(ActivityHome.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(ActivityHome.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), AfterLogin.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ActivityHome.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}