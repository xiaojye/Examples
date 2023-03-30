package com.jye.jnidemo;

import android.util.Log;

public class MyNatives {

    static {
        System.loadLibrary("MyNatives");
    }

    /**
     * 计算两个整数之和
     *
     * @param num1
     * @param num2
     * @return
     */
    public native int nativeAdd(int num1, int num2);

    public native String nativeGetString();

    public void printHello() {
        Log.d("MyNatives", "Hello, world!");
    }
}
