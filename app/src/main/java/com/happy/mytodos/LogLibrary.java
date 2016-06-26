package com.happy.mytodos;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by happy on 2016-05-29.
 */
public class LogLibrary {

    static String tag = "debugging";

    public static void print (String msg) {
        Log.d(tag, msg);
    }
    public static void print(int num) {
        Log.d(tag, "" + num);
    }
    public static void print() {
        Log.d(tag, " ");
    }
    //변수는 같은 이름을 두 번 써줄 수 없지만, 함수는 이름이 같은 게 있을 수 있다!

    public static void error(String msg) {
        Log.e(tag, "******ERROR******");
        Log.e(tag, msg);
        Log.e(tag, "*****************");
    }
    public static void error() {
        Log.e(tag, "*****************");
        Log.e(tag, "******ERROR******");
        Log.e(tag, "*****************");
    }

    //배열을 한 줄씩 프린트하는 로그!
    public static void printEach(int [] arr) {
        Log.w(tag, "---printing an integer array---"); //Log w는 파란색
        for (int i=0; i<arr.length; i=i+1) {
            Log.d(tag, "" + arr[i]);
        }
//        for (int a : arr) {
//            Log.d(tag, "" + a);
//        }
        Log.w(tag, "-------finished printing-------");
    }
    //배열 전체를 한 번에 프린트하는 로그!
    public static void printWhole(int [] arr) {
        Log.w(tag, "---printing an integer array---");
        Log.d(tag, "" + Arrays.toString(arr));
        Log.w(tag, "-------finished printing-------");
    }
    public static void printEach(String [] arr) {
        Log.w(tag, "---printing a string array---");
        for (int i=0; i<arr.length; i=i+1) {
            Log.d(tag, "" + arr[i]);
        }
        Log.w(tag, "------finished printing------");
    }
    public static void printWhole(String [] arr) {
        Log.w(tag, "---printing a string array---");
        Log.d(tag, "" + arr);
        Log.w(tag, "------finished printing------");
    }
    public static void printEach(double [] arr) {
        Log.w(tag, "---printing a double array---");
        for (int i=0; i<arr.length; i=i+1) {
            Log.d(tag, "" + arr[i]);
        }
        Log.w(tag, "------finished printing------");
    }
    public static void printWhole(double [] arr) {
        Log.w(tag, "---printing a double array---");
        Log.d(tag, "" + Arrays.toString(arr));
        Log.w(tag, "------finished printing------");
    }
    public static void printEach(ArrayList arrList) {
        Log.w(tag, "---printing an array list---");
        for (int i=0; i<arrList.size(); i=i+1) {
            Log.d(tag, "" + arrList.get(i));
        }
        Log.w(tag, "-----finished printing-----");
    }
    public static void printWhole(ArrayList arrList) {
        Log.w(tag, "---printing an array list---");
        Log.d(tag, "" + arrList);
        Log.w(tag, "-----finished printing-----");
    }
    public static void printError(int n) {
        Log.e(tag, "******ERROR******");
        Log.e(tag, "" + n);
        Log.e(tag, "*****************");
    }
}
