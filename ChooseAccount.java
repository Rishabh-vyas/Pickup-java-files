package org.probuilder.pkup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseAccount extends AppCompatActivity {

    private Button bussinessRegisterBtn;
    private Button normalUserRegistrationBtn;
    private ImageView backBtnChooserAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_account);

        initilzer();

        bussinessRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ChooseAccount.this,RegisterGuide.class));
            }
        });

        normalUserRegistrationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ChooseAccount.this,NormalUserRegistartionPage.class));
            }
        });

        backBtnChooserAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ChooseAccount.this,SplashScreen.class));
                finish();
            }
        });
        

    }

    private void initilzer()
    {
        bussinessRegisterBtn=findViewById(R.id.bussinessRegisterBtn);
        normalUserRegistrationBtn=findViewById(R.id.normalUserRegistrationBtn);
        backBtnChooserAccount=findViewById(R.id.backBtnChooserAccount);
    }
}