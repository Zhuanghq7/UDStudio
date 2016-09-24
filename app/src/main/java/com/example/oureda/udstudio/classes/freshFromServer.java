package com.example.oureda.udstudio.classes;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.oureda.udstudio.MainActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zhuangh7 on 2016/9/23.
 */
public class freshFromServer extends Thread{

    private MainActivity activity;

    private void log(String s){
        Log.d("log",s);
    }
    public freshFromServer(MainActivity act){
        activity = act;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket("123.206.208.46", 7788);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String temp = br.readLine();//得到控件数量
            log("控件数量 "+temp);
            int num = Integer.parseInt(temp);
            for(int i = 0;i<num;i++) {
                MainActivity.addViews(br.readLine());
            }
            activity.updateSuccessful = 1;
        } catch (Exception e) {
            Log.d("log","connect server failed");
            activity.createToast("fail to connect server");
            activity.updateSuccessful = 2;
            e.printStackTrace();
        }
    }
}
