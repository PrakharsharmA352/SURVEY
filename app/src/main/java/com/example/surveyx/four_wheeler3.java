package com.example.surveyx;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

public class four_wheeler3 extends AppCompatActivity implements View.OnClickListener{
        RecyclerView recyclerView;
        TextView textView;
        Button button;
        ArrayList<Uri> list;
        RecyclerAdaptor adaptor;
        String colum[]={
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE};
        // declr button of videoview
        Button btn,btn1,submit;
final static int REQUEST_VIDEO_CAPTURED = 1;
        Uri uriVideo = null;
        VideoView videoviewPlay;
//end of viedo view
public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;

private ImageView imageView1;
// One Preview Image
private ImageView imageView;

        // constant to compare
        // the activity result code
        int SELECT_PICTURE = 200;

@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_four_wheeler3);
        list=new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        textView=findViewById(R.id.textView);
        button=findViewById(R.id.button);
        adaptor=new RecyclerAdaptor(list);
        submit=(Button)findViewById(R.id.submit);
        recyclerView.setLayoutManager(new GridLayoutManager(four_wheeler3.this,4));
        recyclerView.setAdapter(adaptor);
        imageView1 = findViewById(R.id.my_avatar_imageview);
        btn1=(Button) findViewById(R.id.btnselect1);
        btn1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        if (checkAndRequestPermissions1(four_wheeler3.this)) {
        chooseImage1(four_wheeler3.this);
        }
        }
        });
        submit.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent submitint=new Intent(getApplicationContext(),viewScreenFourWheeler.class);
        startActivity(submitint);
        }
        });
        button.setOnClickListener(this);
      /*  if((ActivityCompat.checkSelfPermission(
                this,colum[0])!= PackageManager.PERMISSION_GRANTED)&&
                (ActivityCompat.checkSelfPermission(
                        this,colum[1])!= PackageManager.PERMISSION_GRANTED))*//*{

        }*/
        // register the UI widgets with their appropriate IDs
        btn= findViewById(R.id.btnselect);
        imageView = findViewById(R.id.viewImage);

        // handle the Choose Image button to trigger
        // the image chooser function
        btn.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        //imageChooser();
        if (checkAndRequestPermissions(four_wheeler3.this)) {
        chooseImage(four_wheeler3.this);
        }
        }
        });
        //video view code from there
        Button buttonRecording = (Button)findViewById(R.id.recording);
        Button buttonPlayback = (Button)findViewById(R.id.playback);
        videoviewPlay = (VideoView)findViewById(R.id.videoview);
        buttonRecording.setOnClickListener(new Button.OnClickListener(){

@Override
public void onClick(View view) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(intent, REQUEST_VIDEO_CAPTURED);
        }});

        buttonPlayback.setOnClickListener(new Button.OnClickListener(){

@Override
public void onClick(View view) {
        // TODO Auto-generated method stub
        if(uriVideo == null){
        // Toast.makeText(MainActivity2.this,"No Video", Toast.LENGTH_LONG.show();
        Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
        }else{
        Toast.makeText(getApplicationContext(),"Playback: " + uriVideo.getPath(),Toast.LENGTH_LONG).show();
        videoviewPlay.setVideoURI(uriVideo);
        videoviewPlay.start();
        }
        }});
        //end there
        }
private void chooseImage(Context context){
final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit" }; // create a menuOption Array
        // create a dialog for showing the optionsMenu
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set the items in builder
        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialogInterface, int i) {
        if(optionsMenu[i].equals("Take Photo")){
        // Open the camera and get the photo
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 0);
        }
        else if(optionsMenu[i].equals("Choose from Gallery")){
        // choose from  external storage
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 1);
        }
        else if (optionsMenu[i].equals("Exit")) {
        dialogInterface.dismiss();
        }
        }
        });
        builder.show();
        }
private void chooseImage1(Context context){
final CharSequence[] optionsMenu = {"Take Photo", "Choose from Gallery", "Exit" }; // create a menuOption Array
        // create a dialog for showing the optionsMenu
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // set the items in builder
        builder.setItems(optionsMenu, new DialogInterface.OnClickListener() {
@Override
public void onClick(DialogInterface dialogInterface, int i) {
        if(optionsMenu[i].equals("Take Photo")){
        // Open the camera and get the photo
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, 2);
        }
        else if(optionsMenu[i].equals("Choose from Gallery")){
        // choose from  external storage
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , 3);
        }
        else if (optionsMenu[i].equals("Exit")) {
        dialogInterface.dismiss();
        }
        }
        });
        builder.show();
        }
// function to check permission
public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
        Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded
        .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
        ActivityCompat.requestPermissions(context, listPermissionsNeeded
        .toArray(new String[listPermissionsNeeded.size()]),
        REQUEST_ID_MULTIPLE_PERMISSIONS);
        return false;
        }
        return true;
        }
public static boolean checkAndRequestPermissions1(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
        Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
        Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded
        .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
        ActivityCompat.requestPermissions(context, listPermissionsNeeded
        .toArray(new String[listPermissionsNeeded.size()]),
        REQUEST_ID_MULTIPLE_PERMISSIONS);
        return false;
        }
        return true;
        }
// Handled permission Result
@Override
public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        switch (requestCode) {
        case REQUEST_ID_MULTIPLE_PERMISSIONS:
        if (ContextCompat.checkSelfPermission(four_wheeler3.this,
        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(getApplicationContext(),
        "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
        .show();
        } else if (ContextCompat.checkSelfPermission(four_wheeler3.this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(getApplicationContext(),
        "FlagUp Requires Access to Your Storage.",
        Toast.LENGTH_SHORT).show();
        } else {
        chooseImage(four_wheeler3.this);
        }
        break;
        }
        }
// this function is triggered when user
// selects the image from the imageChooser
public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ///CODE OF VIDEO VIEW STRT
        if (resultCode == RESULT_OK) {
        if (requestCode == REQUEST_VIDEO_CAPTURED) {
        uriVideo = data.getData();
        Toast.makeText(four_wheeler3.this, uriVideo.getPath(), Toast.LENGTH_LONG).show();
        }
        }

        //END THERE
        if (resultCode != RESULT_CANCELED) {
        switch (requestCode) {
        case 0:
        if (resultCode == RESULT_OK && data != null) {
        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
        Bitmap selectedImage1 = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(selectedImage);
        //   imageView1.setImageBitmap(selectedImage1);
        }
        break;
        case 1:
        if (resultCode == RESULT_OK && data != null) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        if (selectedImage != null) {
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        String picturePath1 = cursor.getString(columnIndex);
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        //    imageView1.setImageBitmap(BitmapFactory.decodeFile(picturePath1));
        cursor.close();
        }
        }
        }
        break;
        case 2:
        if (resultCode == RESULT_OK && data != null) {
        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
        Bitmap selectedImage1 = (Bitmap) data.getExtras().get("data");
        // imageView.setImageBitmap(selectedImage);
        imageView1.setImageBitmap(selectedImage1);
        }
        break;
        case 3:
        if (resultCode == RESULT_OK && data != null) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        if (selectedImage != null) {
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        if (cursor != null) {
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        String picturePath1 = cursor.getString(columnIndex);
        //  imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        imageView1.setImageBitmap(BitmapFactory.decodeFile(picturePath1));
        cursor.close();
        }
        }
        }
        }
        }
        /////FROM THIS THE MULTIPLE IMAGE CONDITION START
        if(requestCode==123 && resultCode==RESULT_OK){
        if(data.getClipData()!=null){
        int x=data.getClipData().getItemCount();
        //  x=11;
        for(int i=0;i<5;i++){
        list.add(data.getClipData().getItemAt(i).getUri());
        }
        adaptor.notifyDataSetChanged();
        // textView.setText("Image("+list.size()+")");
        }else if(data.getData()!=null){
        String imgurl=data.getData().getPath();
        list.add(Uri.parse(imgurl));

        }
        }
        }
@Override
public void onClick(View view) {

        switch (view.getId()){
        case R.id.button:
        openGalley();
        break;
        }

        }

private void openGalley() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selcet Picture"),123);
        }



        }
