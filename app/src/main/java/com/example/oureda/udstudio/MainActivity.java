package com.example.oureda.udstudio;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView topBar;
    private TextView textView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        topBar = (ImageView)findViewById(R.id.TopBar);
        //frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        textView = (TextView) findViewById(R.id.textview);

        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        int screemWidth = dm2.widthPixels;
        int screemHeight = dm2.heightPixels;
        //get Screem size

        topBar.setAdjustViewBounds(true);
        topBar.setMaxWidth(screemWidth);
        topBar.setMaxHeight((int)(screemWidth/3.2));
        topBar.setBackgroundColor(Color.BLACK);
        topBar.setImageResource(R.drawable.topbar);

        textView.setText("click to update");
        //textView.setBackgroundColor(Color.BLUE);
        textView.setTextSize(20);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"23333",Toast.LENGTH_LONG).show();
                Log.d("log","click");
            }
        });


    }
}
