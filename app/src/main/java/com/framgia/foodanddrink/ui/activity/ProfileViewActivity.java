package com.framgia.foodanddrink.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.RequestDef;
import com.framgia.foodanddrink.utils.AlertDialogUtils;

public class ProfileViewActivity extends AppCompatActivity implements OnClickListener {
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        initViews();
    }

    private void initViews() {
        avatar = (ImageView) findViewById(R.id.image_avatar);
        findViewById(R.id.btn_edit_email).setOnClickListener(this);
        findViewById(R.id.btn_edit_name).setOnClickListener(this);
        findViewById(R.id.btn_edit_phone).setOnClickListener(this);
        findViewById(R.id.btn_camera_avt).setOnClickListener(this);
    }

    public void onClick(View view) {
        int btnId = view.getId();
        switch (btnId) {
            case R.id.btn_edit_email:
            case R.id.btn_edit_name:
            case R.id.btn_edit_phone:
                editValue(btnId);
                break;
            case R.id.btn_camera_avt:
                loadPhoto();
                break;
            default:
                break;
        }
    }

    private void editValue(int editId) {
        final TextView textViewChanged;
        final EditText txtUrl = new EditText(this);
        switch (editId) {
            case R.id.btn_edit_name:
                txtUrl.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                textViewChanged = (TextView) findViewById(R.id.text_profile_name);
                break;
            case R.id.btn_edit_phone:
                txtUrl.setInputType(InputType.TYPE_CLASS_PHONE);
                textViewChanged = (TextView) findViewById(R.id.text_profile_phone);
                break;
            case R.id.btn_edit_email:
                txtUrl.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                textViewChanged = (TextView) findViewById(R.id.text_profile_email);
                break;
            default:
                return;
        }
        new AlertDialog.Builder(this)
            .setTitle(textViewChanged.getText())
            .setView(txtUrl)
            .setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    textViewChanged.setText(txtUrl.getText().toString());
                }
            })
            .setNegativeButton(R.string.cancel, null).show();
    }

    private void loadPhoto() {
        new AlertDialog.Builder(this)
            .setTitle(R.string.take_pic)
            .setMessage(R.string.ask_for_pic_content)
            .setPositiveButton(R.string.gallery,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider
                                .MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                            RequestDef.GALLERY_REQUEST);
                    }
                })
            .setNegativeButton(R.string.camera,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(android
                            .provider.MediaStore.ACTION_IMAGE_CAPTURE), RequestDef.CAMERA_REQUEST);
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(@RequestDef int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            AlertDialogUtils.show(ProfileViewActivity.this, R.string.image_loading, R.string
                .load_image_fail);
            return;
        }
        switch (requestCode) {
            case RequestDef.CAMERA_REQUEST:
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                avatar.setImageBitmap(imageBitmap);
                break;
            case RequestDef.GALLERY_REQUEST:
                Uri selectedImage = data.getData();
                avatar.setImageURI(selectedImage);
                break;
            default:
                break;
        }
    }
}
