package org.probuilder.pkup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class NormalUserOtpVerification extends AppCompatActivity {

    private ImageView backBtnNormalUser3rdRegistartion;
    private Button verifyOTPBtn;
    private ProgressBar progressBar_OTPVerification;
    private EditText OTPEditText;
    private String mobileNumber;
    private PinView OTPPinView;
    private String verificationID;
    private TextView resendOTP;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_otp_verification);

        initilizer();



        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(OTPPinView.getText().toString()==null)
                {
                    OTPPinView.setError("Wrong OTP ");
                    Toast.makeText(NormalUserOtpVerification.this, "Wrong OTP .", Toast.LENGTH_SHORT).show();
                }

                String code=OTPPinView.getText().toString();

                if(verificationID!=null)
                {
//                   progressBar_OTPVerification.setVisibility(View.VISIBLE);
                    verifyOTPBtn.setEnabled(false);
                    verifyOTPBtn.setVisibility(View.INVISIBLE);

                    PhoneAuthCredential phoneAuthCredential =PhoneAuthProvider.getCredential(
                            verificationID,
                            code
                    );

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
//                                    progressBar_OTPVerification.setVisibility(View.GONE);
                                    verifyOTPBtn.setEnabled(true);
                                    verifyOTPBtn.setVisibility(View.VISIBLE);

                                    if (task.isSuccessful())
                                    {
                                        Intent intent=new Intent(NormalUserOtpVerification.this,RegisterGuide.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }else
                                    {
                                        Toast.makeText(NormalUserOtpVerification.this, "Verification code is wrong .", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }

            }
        });

        backBtnNormalUser3rdRegistartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(NormalUserOtpVerification.this, NormalUserMobileNumber.class));
                finish();
            }
        });

        resendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                Toast.makeText(NormalUserOtpVerification.this, "Hello", Toast.LENGTH_SHORT).show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        getIntent().getStringExtra("MobileNumber"),
                        60,
                        TimeUnit.SECONDS,
                        NormalUserOtpVerification.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(NormalUserOtpVerification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerificationID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationID=newVerificationID;
                                Toast.makeText(NormalUserOtpVerification.this, "OTP Send agin !", Toast.LENGTH_SHORT).show();
                            }
                        }

                );


            }
        });

    }

    private void initilizer() {
        backBtnNormalUser3rdRegistartion = findViewById(R.id.backBtnNormalUser3rdRegistartion);
        verifyOTPBtn = findViewById(R.id.verifyOTPBtn);
        resendOTP=findViewById(R.id.resendOTP);
        OTPPinView = findViewById(R.id.OTPPinView);
        mobileNumber=getIntent().getStringExtra("MobileNumber");
        verificationID=getIntent().getStringExtra("verificationID");
        Toast.makeText(this, verificationID, Toast.LENGTH_SHORT).show();

    }



}
