package org.probuilder.pkup;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterGuide extends AppCompatActivity {

    private Button createNewBussinessAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guide);

        initilizer();

        createNewBussinessAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterGuide.this,BusinessRegistartionPage.class));
                finish();
            }
        });

    }

    private void initilizer()
    {
        createNewBussinessAccountBtn=findViewById(R.id.createNewBussinessAccountBtn);
    }
}