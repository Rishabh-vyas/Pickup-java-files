package org.probuilder.pkup;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NormalUserRegistartionPage extends AppCompatActivity {

    private Button nextBtn1;
    private ImageView backBtnNormalUser1stRegistartion;
    private EditText normalUserRegisterName;
    private EditText normalUserRegisterEmail;
    private EditText normalUserRegisterPassword;
    private EditText normalUserRegisterConfromPassword;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_registartion_page);

        initilzer();

        nextBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation();
            }
        });

        backBtnNormalUser1stRegistartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NormalUserRegistartionPage.this,ChooseAccount.class));
            }
        });

    }

    private void checkValidation()
    {
        String name=normalUserRegisterName.getText().toString();
        String mail=normalUserRegisterEmail.getText().toString();
        String pass=normalUserRegisterPassword.getText().toString();
        String confPass=normalUserRegisterConfromPassword.getText().toString();

        if (TextUtils.isEmpty(name))
        {
            normalUserRegisterName.setError("Enter Name");
        }
        else if (TextUtils.isEmpty(pass))
        {
            normalUserRegisterPassword.setError("Enter Password");
        }
        else if (TextUtils.isEmpty(confPass))
        {
            normalUserRegisterConfromPassword.setError("Enter Password ");
        }
        else if (TextUtils.isEmpty(mail))
        {
            normalUserRegisterEmail.setError("Enter Email");
        }
        else if (!(pass.length()>=6))
        {
            Toast.makeText(NormalUserRegistartionPage.this,"Password must be more than 6",Toast.LENGTH_SHORT).show();
        }
        else if (!(pass.length()>=6))
        {
            Toast.makeText(NormalUserRegistartionPage.this,"Password must be more than 6",Toast.LENGTH_SHORT).show();
        }
        else if (!pass.equals(confPass))
        {
            Toast.makeText(NormalUserRegistartionPage.this,"Password not matched",Toast.LENGTH_SHORT).show();
        }
        else if (!(normalUserRegisterEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")))
        {
            normalUserRegisterEmail.setError("Enter Valid email");
        }
        else
        {

//            main code to go to new page ......  :)
            progressBar.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run()
                {

                    Intent goToProfileIntent=new Intent(NormalUserRegistartionPage.this,NormalUserProfile.class);
                    goToProfileIntent.putExtra("UserName",normalUserRegisterName.getText().toString());
                    goToProfileIntent.putExtra("Email",normalUserRegisterEmail.getText().toString());
                    goToProfileIntent.putExtra("Password",normalUserRegisterPassword.getText().toString());
                    startActivity(goToProfileIntent);
                    finish();

                }
            },2000);



        }
    }

    private void initilzer()
    {
        nextBtn1=findViewById(R.id.nextBtn1);
        backBtnNormalUser1stRegistartion=findViewById(R.id.backBtnNormalUser1stRegistartion);
        normalUserRegisterName=findViewById(R.id.normalUserRegisterName);
        normalUserRegisterEmail=findViewById(R.id.normalUserRegisterEmail);
        normalUserRegisterPassword=findViewById(R.id.normalUserRegisterPassword);
        normalUserRegisterConfromPassword=findViewById(R.id.normalUserRegisterConfromPassword);
        progressBar=findViewById(R.id.progressBar);
    }
}