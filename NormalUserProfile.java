package org.probuilder.pkup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class NormalUserProfile extends AppCompatActivity {

    private Button uploadProfilePhotoBtn;
    private Button nextBtn2;
    private ImageView backBtnNormalUser4thRegistartion;
    private ImageView normalUserPhoto;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    private ProgressBar progressBarProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_user_profile);

        initilizer();

        backBtnNormalUser4thRegistartion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NormalUserProfile.this,NormalUserRegistartionPage.class));
                finish();
            }
        });

//        nextBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(NormalUserProfile.this,NormalUserMobileNumber.class));
//                finish();
//            }
//        });

        nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Check image and go to mobile page
                if(filePath == null )
                {
                    Toast.makeText(NormalUserProfile.this, "No image uploaded", Toast.LENGTH_SHORT).show();
                }
                else
                {

//                  goto next page (mobile page)
                    progressBarProfile.setVisibility(View.VISIBLE);
                    Toast.makeText(NormalUserProfile.this, "Profile Photo uploaded", Toast.LENGTH_SHORT).show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent goToMobilePageIntent= new Intent(NormalUserProfile.this, NormalUserMobileNumber.class);
                            goToMobilePageIntent.putExtra("ProfileImagePath",filePath);
                            startActivity(goToMobilePageIntent);
                            finish();

                        }
                    },2000);
                }

            }
        });
        
        uploadProfilePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });

    }

    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Image from here..."),PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data)
    {
        super.onActivityResult(requestCode,resultCode,data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                normalUserPhoto.setImageBitmap(bitmap);
            }

            catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }

    private void initilizer()
    {
        uploadProfilePhotoBtn=findViewById(R.id.uploadProfilePhotoBtn);
        nextBtn2=findViewById(R.id.nextBtn2);
        backBtnNormalUser4thRegistartion=findViewById(R.id.backBtnNormalUser4thRegistartion);
        normalUserPhoto=findViewById(R.id.normalUserPhoto);
        progressBarProfile=findViewById(R.id.progressBarProfile);
    }
}