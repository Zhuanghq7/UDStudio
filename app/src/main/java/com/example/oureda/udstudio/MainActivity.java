package com.example.oureda.udstudio;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private ImageView topBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        topBar = (ImageView)findViewById(R.id.TopBar);

        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        int screemHeight = dm2.heightPixels;
        int screemWidth = dm2.widthPixels;
        //get Screem size

        topBar.setAdjustViewBounds(true);
        topBar.setMaxWidth(screemWidth);
        topBar.setMaxHeight((int)(screemWidth/3.2));
        topBar.setBackgroundColor(Color.WHITE);
        topBar.setImageResource(R.drawable.topbar);
    }
}
