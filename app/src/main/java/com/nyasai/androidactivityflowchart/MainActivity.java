package com.nyasai.androidactivityflowchart;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    String textViewText="ここにログが表示されます";
    String saveText;
    static SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sp = getSharedPreferences("log",MODE_APPEND);
        textViewText=sp.getString("saveString","\n");
        setText(textViewText);

        textViewText="onCreate";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();

        Button b=(Button) this.findViewById(R.id.button1);
        b.setOnClickListener(this);
        Button b_cls = (Button) this.findViewById(R.id.button_crear);
        b_cls.setOnClickListener(this);



    }


    @Override
    protected void onStart(){
        super.onStart();
        textViewText="onStart";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart(){
        super.onStart();
        textViewText="onRestart";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        textViewText="onResume";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause(){
        super.onPause();
        textViewText="onPause";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop(){
        super.onStop();
        textViewText="onStop";
        setText(textViewText);
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy(){
        textViewText="onDestroy";
        //setText(textViewText);
        super.onDestroy();
        Toast.makeText(this,textViewText,Toast.LENGTH_SHORT).show();
    }

    public void setText(String text){
        String date = getNowTime();
        TextView tv=(TextView)findViewById(R.id.mytextview);
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());
        saveText+="\n"+date+"\t\t"+text;
        tv.append("\n"+date+"\t\t"+text);

    }

    public static String getNowTime(){
        final DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
        Date date = new Date(System.currentTimeMillis());
        return df.format(date);
    }

    @Override
    public void onClick(View v){
        sp = getSharedPreferences("log",MODE_APPEND);
        final SharedPreferences.Editor editor = sp.edit();
        TextView tv = (TextView) findViewById(R.id.mytextview);

        switch (v.getId()){
            case R.id.button1:
                textViewText="onPause";
                setText(textViewText);
                textViewText="onStop";
                setText(textViewText);
                textViewText="onDestroy\n";
                setText(textViewText);
                editor.putString("saveString",saveText).commit();
                finish();
                break;
            case R.id.button_crear:
                editor.clear().commit();
                tv.setText("");
                saveText = "";
                break;

        }
    }


}
