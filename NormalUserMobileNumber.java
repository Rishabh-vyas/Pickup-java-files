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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class NormalUserMobileNumber extends AppCompatActivity {

    private ImageView backBtnNormalUser2ndRegistartion;
    private Button sendOTPBtn;
    private EditText normalUsermobileNumber;
    private String verificationId;
    private ProgressBar progressBar_mobilenumber;
    private CountryCodePicker countryCodePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_mobile_number);

        initilizer();

        backBtnNormalUser2ndRegistartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NormalUserMobileNumber.this, NormalUserProfile.class));
            }
        });

        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkValidation();
            }
        });
    }

    private void checkValidation() {
        String phone = normalUsermobileNumber.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            normalUsermobileNumber.setError("Enter Mobile Number");
        } else {

            progressBar_mobilenumber.setVisibility(View.VISIBLE);
            sendOTPBtn.setEnabled(false);
            sendOTPBtn.setVisibility(View.INVISIBLE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    String mobileNumberEnterByUser = normalUsermobileNumber.getText().toString().trim();
                    String mobileNumberWithCountryCode= countryCodePicker.getSelectedCountryCodeWithPlus()+mobileNumberEnterByUser;

                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            mobileNumberWithCountryCode,
                            60,
                            TimeUnit.SECONDS,
                            NormalUserMobileNumber.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                    progressBar_mobilenumber.setVisibility(View.GONE);
                                    sendOTPBtn.setEnabled(true);
                                    sendOTPBtn.setVisibility(View.VISIBLE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {

                                    progressBar_mobilenumber.setVisibility(View.GONE);
                                    sendOTPBtn.setEnabled(true);
                                    sendOTPBtn.setVisibility(View.VISIBLE);
                                    Toast.makeText(NormalUserMobileNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String verificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    progressBar_mobilenumber.setVisibility(View.GONE);
                                    sendOTPBtn.setEnabled(true);
                                    sendOTPBtn.setVisibility(View.VISIBLE);

                                    Toast.makeText(NormalUserMobileNumber.this, "OTP Send", Toast.LENGTH_SHORT).show();


                                    Intent goToOtpVerify=new Intent(NormalUserMobileNumber.this,NormalUserOtpVerification.class);
                                    goToOtpVerify.putExtra("MobileNumber",mobileNumberWithCountryCode);
                                    goToOtpVerify.putExtra("verificationID",verificationID);
                                    startActivity(goToOtpVerify);
                                    finish();
                                }
                            }

                    );


                }
            },2000);


        }
    }

    private void initilizer() {
        backBtnNormalUser2ndRegistartion = findViewById(R.id.backBtnNormalUser2ndRegistartion);
        sendOTPBtn = findViewById(R.id.sendOTPBtn);
        normalUsermobileNumber = findViewById(R.id.normalUsermobileNumber);
        progressBar_mobilenumber=findViewById(R.id.progressBar_mobilenumber);
        countryCodePicker=findViewById(R.id.country_code);

    }


}


