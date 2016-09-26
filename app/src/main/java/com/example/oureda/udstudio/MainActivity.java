package com.example.oureda.udstudio;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oureda.udstudio.adapter.mainAdapter;
import com.example.oureda.udstudio.classes.freshFromServer;
import com.example.oureda.udstudio.classes.item;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private boolean firstUpdated = true;
    private ImageView topBar;
    private TextView textView;
    private ListView listView;
    private LinearLayout refreshbar;
    private ImageButton refreshbutton;
    private TextView appName;
    private RelativeLayout firstRefresh;
    public boolean updating = false;
    public int screemWidth,screemHeight;
    private int animationTime = 500;
    private ArrayList<item> list;

    public static int updateSuccessful = 0;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    private GoogleApiClient client;

    public boolean checkNetworkInfo() {
        ConnectivityManager conMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }

    public void createToast(String s){
        Looper.prepare();
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        Looper.loop();
    }
    public void refresh(){
        updateSuccessful = 0;
        updating = true;
        new freshFromServer(MainActivity.this).start();
        while(updateSuccessful == 0){

        }
        if(updateSuccessful == 1){
            Toast.makeText(getApplicationContext(),"update successful", Toast.LENGTH_SHORT).show();
            moveViews();
        }else{
            Toast.makeText(getApplicationContext(),"update failed",Toast.LENGTH_SHORT).show();
        }
    }
    public void moveViews(){
        if(firstUpdated){
            removeTopBar();//把topbar移掉(下面的按钮会有闪现的效果=- =干脆删除吧
            disRefreshBar();
            disListView();
            //remove old view
            textView.setVisibility(View.GONE);//
            firstRefresh.setVisibility(View.GONE);
            firstUpdated = false;
        }else{
            Toast.makeText(getApplicationContext(),"you click the secondRefresh button", Toast.LENGTH_SHORT).show();
        }
    }
    public void disListView(){
        listView.setVisibility(View.VISIBLE);
        listView = (ListView)findViewById(R.id.listView);
        mainAdapter adapter = new mainAdapter(getApplicationContext(),1,list);
        listView.setAdapter(adapter);
    }
    public void disRefreshBar() {
        refreshbutton = (ImageButton) findViewById(R.id.refreshbutton);
        appName = (TextView) findViewById(R.id.refreshbarName);

        //imageButton
        refreshbutton.setBackgroundResource(R.drawable.refresh);
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("log", "clickSecondRefresh");
                if(checkNetworkInfo()){
                    refresh();
                }else{
                    Log.d("log","no internet connected");
                    Toast.makeText(getApplicationContext(), "please check your net connect", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //refreshbar
        refreshbar.setBackgroundColor(Color.GRAY);
        refreshbar.setVisibility(View.VISIBLE);
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(animationTime);
        animationSet.addAnimation(alphaAnimation);
        refreshbar.startAnimation(animationSet);
        //appName
        appName.setTextSize((int)(((float)22/480)*(float)screemWidth));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        topBar = (ImageView) findViewById(R.id.TopBar);
        textView = (TextView) findViewById(R.id.textview);
        //listView = (ListView) findViewById(R.id.listview);
        refreshbar = (LinearLayout) findViewById(R.id.refreshbar);
        firstRefresh = (RelativeLayout) findViewById(R.id.firstRefresh);

        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        screemWidth = dm2.widthPixels;
        screemHeight = dm2.heightPixels;

        Log.d("log","width:"+screemWidth+" Height:"+screemHeight);

        //refreshbar
        //topBar
        topBar.setAdjustViewBounds(true);
        topBar.setMaxWidth(screemWidth);
        topBar.setMaxHeight((int) (screemWidth / 3.2));
        topBar.setBackgroundColor(Color.BLACK);
        topBar.setImageResource(R.drawable.topbar);
        //textView
        textView.setText("click to update");
        textView.setTextSize(20);
        textView.setClickable(true);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"23333",Toast.LENGTH_LONG).show();
                Log.d("log", "clickFirstRefresh");
                if(checkNetworkInfo()){
                    refresh();
                }else{
                    Log.d("log","no internet connected");
                    Toast.makeText(getApplicationContext(), "please check your net connect", Toast.LENGTH_SHORT).show();
                }

            }
        });
        firstRefresh.setClickable(true);
        firstRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"23333",Toast.LENGTH_LONG).show();
                Log.d("log", "click");
                if(checkNetworkInfo()){
                    refresh();
                }else{
                    Log.d("log","no internet connected");
                    Toast.makeText(getApplicationContext(), "please check your net connect", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void addViews(String s,int number){
        //根据服务器数据动态创建
        String name = null;
        int funNum = -1;
        String[] funs;
        String log = "";

        int funsI = 0;
        for(int i = 0;i<s.length();i++){
            if(name == null && s.charAt(i) == ';') {
                name = s.substring(0, i);
                log+="getName:"+name;
                s=s.substring(i+1);
                break;
            }
        }

        for(int i = 0;i<s.length();i++) {
            if(name!=null&&funNum==-1&&s.charAt(i)==';') {
                funNum = Integer.parseInt(s.substring(0, i));
                log+="funNum:" + funNum;
                s = s.substring(i + 1);
                break;
            }
        }

        funs = new String[funNum];
        int oldI = 0;
        for(int i = 0;i<s.length();i++) {
            if(s.charAt(i)==';'){
                funs[funsI++] = s.substring(oldI,i);
                oldI = i+1;
                log+="fun "+(funsI)+" :"+funs[funsI-1];
            }
        }

        funs[funs.length-1] = s.substring(oldI);
        log+="fun "+funs.length+" :"+funs[funs.length-1];
        Log.d("log",log);

        /*items[number] = new LinearLayout(this);
        items[number].setId(number);*/
        list.add(new item(name,funNum,funs));
       // items[number].setOrientation();
    }
    private void removeTopBar(){
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,-1f
        );

        translateAnimation.setDuration(animationTime/2);
        animationSet.addAnimation(translateAnimation);
        topBar.startAnimation(animationSet);
        topBar.setVisibility(View.GONE);
        firstUpdated = false;
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.oureda.udstudio/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.oureda.udstudio/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
