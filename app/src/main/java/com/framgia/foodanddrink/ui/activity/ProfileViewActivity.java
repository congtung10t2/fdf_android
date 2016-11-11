package com.framgia.foodanddrink.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ProfileViewActivity extends AppCompatActivity {
    public static final int CAMERA_REQUEST = 1;
    public static final int GALLERY_REQUEST = 2;
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({CAMERA_REQUEST, GALLERY_REQUEST})
    public @interface RequestDef {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
    }

    public void editValue(View v) {
        final TextView textViewChanged;
        switch (v.getId()) {
            case R.id.btn_edit_name:
                textViewChanged = (TextView) findViewById(R.id.text_profile_name);
                break;
            case R.id.btn_edit_phone:
                textViewChanged = (TextView) findViewById(R.id.text_profile_phone);
                break;
            case R.id.btn_edit_email:
                textViewChanged = (TextView) findViewById(R.id.text_profile_email);
                break;
            default:
                return;
        }
        final EditText txtUrl = new EditText(this);
        new AlertDialog.Builder(this).setTitle(textViewChanged.getText()).setView(txtUrl)
            .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textViewChanged.setText(txtUrl.getText().toString());
                }
            }).setNegativeButton(R.string.cancel, null).show();
    }

    public void loadPhoto(View v) {
        AlertDialog.Builder takeImage = new AlertDialog.Builder(
            this);
        takeImage.setTitle(R.string.take_pic);
        takeImage.setMessage(R.string.ask_for_pic_content);
        takeImage.setPositiveButton(R.string.gallery,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider
                        .MediaStore.Images.Media.EXTERNAL_CONTENT_URI), GALLERY_REQUEST);
                }
            });
        takeImage.setNegativeButton(R.string.camera,
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(android
                        .provider.MediaStore.ACTION_IMAGE_CAPTURE), CAMERA_REQUEST);
                }
            });
        takeImage.show();
    }

    @Override
    protected void onActivityResult(@RequestDef int requestCode, int resultCode, Intent data) {
        ImageView avatar = (ImageView) findViewById(R.id.image_avatar);
        switch (requestCode) {
            case CAMERA_REQUEST:
                if (resultCode != RESULT_OK) return;
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                avatar.setImageBitmap(imageBitmap);
                break;
            case GALLERY_REQUEST:
                if (resultCode != RESULT_OK) return;
                Uri selectedImage = data.getData();
                avatar.setImageURI(selectedImage);
                break;
            default:
                break;
        }
    }
}
