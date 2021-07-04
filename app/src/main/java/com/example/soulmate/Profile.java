package com.example.soulmate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.github.dhaval2404.imagepicker.constant.ImageProvider;
import com.github.dhaval2404.imagepicker.listener.DismissListener;
import com.github.dhaval2404.imagepicker.util.FileUtil;
import com.github.dhaval2404.imagepicker.util.IntentUtils;

public class Profile extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    final String SAVED_PASSWORD = "PASSWORD";
    final String SAVED_LOGIN = "LOGIN";
    final String SAVED_NAME = "NAME";
    final String SAVED_IMG = "IMG";

    private static final int PROFILE_IMAGE_REQ_CODE = 101;
    private Uri mProfileUri;
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ImageButton back = findViewById(R.id.profileBack);
        imgProfile = findViewById(R.id.imgProfile);

        sharedPreferences = getPreferences(MODE_PRIVATE);
        String img = sharedPreferences.getString(SAVED_IMG, "");
        if (!img.equals("")) {
            imgProfile.setImageURI(Uri.parse(img));
        }

        sharedPreferences = getSharedPreferences("SignIn",MODE_PRIVATE);
        String savedLogin = sharedPreferences.getString(SAVED_LOGIN, "");
        String savedName = sharedPreferences.getString(SAVED_NAME, "");
        TextView profileLogin = findViewById(R.id.profileLogin);
        TextView profileName = findViewById(R.id.profileName);
        profileLogin.setText(savedLogin);
        profileName.setText(savedName);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageButton exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void pickProfileImage(View view) {
        ImagePicker.with(this)
                // Crop Square image
                .cropSquare()
                .setDismissListener(new DismissListener() {
            @Override
            public void onDismiss() {
                Log.d("ImagePicker", "Dialog Dismiss");
            }
        })// Image resolution will be less than 512 x 512
                .maxResultSize(400, 400)
                .start(PROFILE_IMAGE_REQ_CODE);
    }

    public void showImage(View view) {
        Uri uri;
        if (view == imgProfile) {
            uri = mProfileUri;
        } else {
            uri = null;
        }

        if (uri != null) {
            startActivity(IntentUtils.getUriViewIntent(this, uri));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            // Uri object will not be null for RESULT_OK
            Uri uri = data.getData();
            if (requestCode == PROFILE_IMAGE_REQ_CODE) {
                mProfileUri = uri;
                sharedPreferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(SAVED_IMG, mProfileUri.toString());
                editor.apply();
                imgProfile.setImageURI(mProfileUri);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

}