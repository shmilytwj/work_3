package com.example.work_4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
    private final static String SharedPreferencesFileName="config";
    private final static String Key_UserID="UserID";
    private final static String Key_UserName="UserName";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        preferences=getSharedPreferences(SharedPreferencesFileName,
                MODE_PRIVATE);
        editor=preferences.edit();
        Button btSave=(Button)findViewById(R.id.Save);
        Button btnRead=(Button)findViewById(R.id.Read);
        final EditText UserID=findViewById(R.id.StudentID);
        final EditText UserName=findViewById(R.id.StudentName);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String id=UserID.getText().toString();
//                String name=UserName.getText().toString();
//                editor.putString(Key_UserID,id);
//                editor.putString(Key_UserName,name);
//                editor.apply();
                //写入文件
//                OutputStream out=null;
//                try{
//                    String Information="information.txt";
//                    FileOutputStream fileOutputStream=openFileOutput(Information,MODE_PRIVATE);
//                    out=new BufferedOutputStream(fileOutputStream);
//                    String content="ID:"+UserID.getText().toString()+"\n"+"Name:"+UserName.getText().toString();
//                    try{
//                        out.write(content.getBytes(StandardCharsets.UTF_8));
//                    }finally {
//                        if(out!=null)
//                            out.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
      OutputStream out=null;
      try {
          if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
          {
              String content = "ID:"+UserID.getText().toString()+"Name:"+UserName.getText().toString();
              String MyFileName="Information.txt";
              ContextWrapper cw = new ContextWrapper(getApplicationContext());

              File directory = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
              File file = new File(directory,  MyFileName);

              FileOutputStream fileOutputStream = new FileOutputStream(file);
              out = new BufferedOutputStream(fileOutputStream);
              try {
                  out.write(content.getBytes(StandardCharsets.UTF_8));
              } finally {
                  if (out != null)
                      out.close();
              }
          }
      }
      catch (Exception e){
          e.printStackTrace();
      }
            }

        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String id=preferences.getString(Key_UserID,null);
//                String name=preferences.getString(Key_UserName,null);
//                if(id!=null && name!=null){
//                    Toast.makeText(MainActivity.this,"学生ID:"+id+"学生Name:"+name,Toast.LENGTH_LONG).show();
//                }
//                else
//                    Toast.makeText(MainActivity.this,"无数据",Toast.LENGTH_LONG).show();
                //文件读取
//                InputStream in=null;
//                try{
//                    String Information="information.txt";
//                    FileInputStream fileInputStream=openFileInput(Information);
//                    in =new BufferedInputStream(fileInputStream);
//                    int c;
//                    StringBuilder stringBuilder=new StringBuilder("");
//                    try{
//                        while((c=in.read())!=-1){
//                            stringBuilder.append((char)c);
//
//                        }
//                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
//                    }finally {
//                        if(in!=null)
//                            in.close();
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
                InputStream in=null;
                try {
                    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String MyFileName="Information.txt";
                        ContextWrapper cw = new ContextWrapper(getApplicationContext());

                        File directory = cw.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
                        File file = new File(directory,  MyFileName);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        in = new BufferedInputStream(fileInputStream);
                        int c;
                        StringBuilder stringBuilder = new StringBuilder("");
                        try {
                            while ((c = in.read()) != -1) {
                                stringBuilder.append((char) c);
                            }
                            Toast.makeText(MainActivity.this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
                        } finally {
                            if (in != null)
                                in.close();
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }


}