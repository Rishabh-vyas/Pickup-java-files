package org.probuilder.pkup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private ImageView backBtn;
    private LinearLayout mainlaLayout;
    private Button lBtn;
    private Switch aswitch;
    private TextView emailText;
    private TextView passText;
    private TextView forgotText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inilize();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(Login.this,SplashScreen.class));
                finish();
            }
        });

        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(aswitch.isChecked())
                {
                    mainlaLayout.setBackgroundResource(R.drawable.back_color_parcel);

                }else
                {
                    mainlaLayout.setBackgroundResource(R.drawable.back_color_ride);

                }
            }
        });


    }

    private void inilize()
    {
        backBtn=findViewById(R.id.backBtn);
        mainlaLayout=findViewById(R.id.mainLayout);
        lBtn=findViewById(R.id.lBtn);
        aswitch=findViewById(R.id.aswitch);
        emailText=findViewById(R.id.emailtext);
        passText=findViewById(R.id.passtext);
        forgotText=findViewById(R.id.forgottext);
    }
}