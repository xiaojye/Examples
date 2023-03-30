package com.jye.jnidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyNatives myNatives = new MyNatives();
        int i = myNatives.nativeAdd(1, 2);
        Log.d("MyNatives", "nativeAdd: " + i);
        String s = myNatives.nativeGetString();
        Log.d("MyNatives", "nativeGetString: " + s);
    }
}