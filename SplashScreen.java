package org.probuilder.pkup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private TextView loginBtn;
    private Button newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initilze();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SplashScreen.this,Login.class));
                finish();
            }
        });

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(SplashScreen.this,ChooseAccount.class));
            }
        });

    }

    private void initilze()
    {
        loginBtn=findViewById(R.id.loginBtn);
        newAccount=findViewById(R.id.newAccount);
    }
}