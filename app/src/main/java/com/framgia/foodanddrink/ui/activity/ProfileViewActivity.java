package com.framgia.foodanddrink.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.RequestDef;
import com.framgia.foodanddrink.utils.AlertDialogUtils;
import com.framgia.foodanddrink.utils.DataStorage;
import com.framgia.foodanddrink.utils.UserStorage;
import com.framgia.foodanddrink.utils.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileViewActivity extends AppCompatActivity implements OnClickListener {
    private ImageView avatar;
    private TextView textEmail;
    private TextView textName;
    private TextView textPhone;
    private String imageDirectory;
    String userChoosenTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        initViews();
    }

    private void initViews() {
        avatar = (ImageView) findViewById(R.id.image_avatar);
        textEmail = (TextView) findViewById(R.id.text_profile_email);
        textPhone = (TextView) findViewById(R.id.text_profile_phone);
        textName = (TextView) findViewById(R.id.text_profile_name);
        findViewById(R.id.btn_edit_email).setOnClickListener(this);
        findViewById(R.id.btn_edit_name).setOnClickListener(this);
        findViewById(R.id.btn_edit_phone).setOnClickListener(this);
        findViewById(R.id.btn_camera_avt).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
        findViewById(R.id.btn_reset).setOnClickListener(this);
        resetValue();
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
            case R.id.btn_update:
                UserStorage.getInstance().setEmail(textEmail.getText().toString());
                UserStorage.getInstance().setName(textName.getText().toString());
                UserStorage.getInstance().setPhone(textPhone.getText().toString());
                UserStorage.getInstance().setImage(imageDirectory);
                UserStorage.getInstance().saveToStorage(this);
                ProfileViewActivity.this.finish();
                break;
            default:
                resetValue();
                break;
        }
    }

    private void resetValue(){
        textEmail.setText(UserStorage.getInstance().getEmail());
        textName.setText(UserStorage.getInstance().getName());
        textPhone.setText(UserStorage.getInstance().getPhone());
        imageDirectory = UserStorage.getInstance().getImage();
        File imgFile = new  File(imageDirectory);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            avatar.setImageBitmap(myBitmap);

        }
    }

    private void editValue(int editId) {
        final TextView textViewChanged;
        final EditText txtUrl = new EditText(this);
        switch (editId) {
            case R.id.btn_edit_name:
                txtUrl.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                textViewChanged = textName;
                break;
            case R.id.btn_edit_phone:
                txtUrl.setInputType(InputType.TYPE_CLASS_PHONE);
                textViewChanged = textPhone;
                break;
            case R.id.btn_edit_email:
                txtUrl.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                textViewChanged = textEmail;
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
                        startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), RequestDef.CAMERA_REQUEST);
                    }
                }).show();
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(FoodDrinkApplication.getInstance().getFilesDir(),
            System.currentTimeMillis() + ".jpg");

        File load = new File(imageDirectory);
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
            imageDirectory = destination.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        avatar.setImageBitmap(thumbnail);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if(userChoosenTask.equals("Take Photo"))
                    startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), RequestDef.CAMERA_REQUEST);
                    else if(userChoosenTask.equals("Choose from Library"))
                        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider
                                .MediaStore.Images.Media.EXTERNAL_CONTENT_URI),
                            RequestDef.GALLERY_REQUEST);
                } else {
                    AlertDialogUtils.show(ProfileViewActivity.this, R.string.image_loading, R.string
                        .load_image_fail);
                }
                break;
        }
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
                onCaptureImageResult(data);
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
