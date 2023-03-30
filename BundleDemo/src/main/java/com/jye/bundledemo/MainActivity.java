package com.jye.bundledemo;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });
    }

    private void write() {
        try {
            File file = new File(getCacheDir(), "parcel_test.txt");
            if (!file.exists()) {
                boolean result = file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(file);

            Parcel parcel = Parcel.obtain();
            parcel.writeValue(new Person("张三"));
            parcel.writeValue(new Person("李四"));

            fos.write(parcel.marshall());

            parcel.setDataPosition(0);
            parcel.recycle();

            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Bundle myBundle = new Bundle();
//        Parcel bndlData = Parcel.obtain();
//        Parcel pcelData = Parcel.obtain();
//        //Bundle对象将携带的键值对数量为2
//        pcelData.writeInt(2);
//
//        //第一个键值对的key值，直接写入字符串，省略了key的长度
//        pcelData.writeString("test");
//        pcelData.writeInt(4); //value类型VAL_PACELABLE，4代表为对象
//        pcelData.writeString("com.jye.bundledemo.MyParcelable"); //name of Class Loader
//        pcelData.writeString("Hello, world!"); //mData
//
//        //写入第二个键值对，key为CSDN，直接写入字符串，省略了key的长度
//        pcelData.writeString("CSDN");
//        pcelData.writeInt(0); //VAL_STRING代表value类型为字符串
//        pcelData.writeString("Tr0e"); //value值
//
//        int length = pcelData.dataSize();
//        bndlData.writeInt(length); //Bundle对象携带的数据总长度
//        bndlData.writeInt(0x4c444E42); //Bundle魔数
//        bndlData.appendFrom(pcelData, 0, length);
//        bndlData.setDataPosition(0);
//        myBundle.readFromParcel(bndlData);
//        Log.d(TAG, myBundle.toString());
    }
}