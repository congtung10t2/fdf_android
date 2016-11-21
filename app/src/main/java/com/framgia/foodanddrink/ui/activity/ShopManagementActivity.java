package com.framgia.foodanddrink.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.framgia.foodanddrink.FoodDrinkApplication;
import com.framgia.foodanddrink.R;
import com.framgia.foodanddrink.data.Constants;
import com.framgia.foodanddrink.data.RequestDef;
import com.framgia.foodanddrink.data.model.ShopItem;
import com.framgia.foodanddrink.ui.adapter.ShopAdapter;
import com.framgia.foodanddrink.utils.AlertDialogUtils;
import com.framgia.foodanddrink.utils.DataTests;
import com.framgia.foodanddrink.utils.UserStorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopManagementActivity extends AppCompatActivity implements View.OnClickListener,
    ShopAdapter.OnItemClickListener {
    private ShopAdapter adapter;
    private RecyclerView shopItemView;
    private AutoCompleteTextView inputName;
    private AutoCompleteTextView inputDesc;
    public static int shopIndex = 0;
    public static ShopItem currentShop;
    private String imgAvatar = "null";
    private String imageCover = "null";
    private ImageView inputAvatar;
    private ImageView inputCover;
    private boolean avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_management);
        initViews();
    }

    private void initViews() {
        inputName = (AutoCompleteTextView) findViewById(R.id.input_shop_name);
        inputDesc = (AutoCompleteTextView) findViewById(R.id.input_shop_desc);
        shopItemView = (RecyclerView) findViewById(R.id.content_shop_manager);
        shopItemView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new ShopAdapter(UserStorage.getInstance().itemShops);
        adapter.setLayoutId(R.layout.shop_child_item_row);
        adapter.setItemClickListener(this);
        shopItemView.setAdapter(adapter);
        inputAvatar = (ImageView) findViewById(R.id.input_img_avatar);
        inputAvatar.setOnClickListener(this);
        inputCover = (ImageView) findViewById(R.id.input_img_cover);
        inputCover.setOnClickListener(this);
        findViewById(R.id.btn_request_shop).setOnClickListener(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_request_shop:
                ShopItem item = new ShopItem(inputName.getText().toString(), inputDesc.getText()
                    .toString(), imgAvatar, imageCover);
                UserStorage.getInstance().itemShops.add(item);
                adapter.notifyDataSetChanged();
                AlertDialogUtils.show(this, R.string.congratulation, R.string.new_shop_noticed);
                break;
            case R.id.input_img_cover:
                avatar = false;
                loadPhoto();
                break;
            case R.id.input_img_avatar:
                avatar = true;
                loadPhoto();
                break;
                //add new shop
        }
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
        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
            if(avatar)
                imgAvatar = destination.getAbsolutePath();
            else
                imageCover = destination.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageView result = avatar? inputAvatar : inputCover;
        result.setImageBitmap(thumbnail);
    }

    @Override
    protected void onActivityResult(@RequestDef int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            AlertDialogUtils.show(ShopManagementActivity.this, R.string.image_loading, R.string
                .load_image_fail);
            return;
        }
        switch (requestCode) {
            case RequestDef.CAMERA_REQUEST:
                onCaptureImageResult(data);
                break;
            case RequestDef.GALLERY_REQUEST:
                Uri selectedImage = data.getData();
                ImageView result = avatar? inputAvatar : inputCover;
                result.setImageURI(selectedImage);
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(ShopManagementActivity.this, ShopDetailActivity.class);
        currentShop = UserStorage.getInstance().itemShops.get(position);
        intent.putExtra(Constants.SHOP_INDEX_KEY, currentShop);
        shopIndex = position;
        startActivity(intent);
    }
}
