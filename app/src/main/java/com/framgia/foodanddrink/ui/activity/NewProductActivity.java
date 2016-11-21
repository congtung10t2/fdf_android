package com.framgia.foodanddrink.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.RequestDef;
import com.framgia.foodanddrink.data.model.FoodDrinkItem;
import com.framgia.foodanddrink.data.model.ShopItem;
import com.framgia.foodanddrink.utils.AlertDialogUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class NewProductActivity extends AppCompatActivity implements OnClickListener {
    private ImageView avatar;
    private String imageDirectory;
    ShopItem shopItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        initViews();
    }

    private void initViews() {
        Intent intent = getIntent();
        shopItem = intent.getParcelableExtra(Constants.SHOP_INDEX_KEY);
        avatar = (ImageView) findViewById(R.id.image_product_image);
        findViewById(R.id.btn_ok).setOnClickListener(this);
        findViewById(R.id.btn_camera_avt).setOnClickListener(this);
    }

    private void loadPhoto() {
        new AlertDialog.Builder(this)
            .setTitle(R.string.take_pic)
            .setMessage(R.string.ask_for_pic_content)
            .setPositiveButton(R.string.gallery,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider
                                .MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RequestDef.GALLERY_REQUEST);
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

    public void newProducts(ShopItem shopItem) {

        String title = ((AutoCompleteTextView) findViewById(R.id.input_product_name)).getText()
            .toString();
        String desc = ((AutoCompleteTextView) findViewById(R.id.input_description)).getText()
            .toString();
        String price = ((EditText) findViewById(R.id.input_price)).getText().toString();

        shopItem.list.add(new FoodDrinkItem(title, desc, price, imageDirectory));
        AlertDialogUtils.show(NewProductActivity.this, "Congratulation",
            getString(R.string.product_added_content));
    }

    public void onClick(View view) {
        int btnId = view.getId();
        switch (btnId) {
            case R.id.btn_ok:
                newProducts(shopItem);
                finish();
                break;
            case R.id.btn_camera_avt:
                loadPhoto();
                break;
            default:
                break;
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(FoodDrinkApplication.getInstance().getFilesDir(),
            System.currentTimeMillis() + ".jpg");
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
    protected void onActivityResult(@RequestDef int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            AlertDialogUtils.show(NewProductActivity.this, R.string.image_loading, R.string
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