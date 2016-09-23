package com.example.oureda.udstudio.classes;

import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by Zhuangh7 on 2016/9/23.
 */
public class freshFromServer extends Thread{


    @Override
    public void run() {
        try {
            Socket s = new Socket("123.206.208.46", 7788);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String temp = br.readLine();
            Log.d("log",temp);
            //Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.d("log","connect server failed");
            e.printStackTrace();
        }
    }
}
