package com.qq979249745;
import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.graphics.*;

public class qidongtu extends Activity
{
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.qidongtu);
        tv=(TextView) findViewById(R.id.qidongtuTextView);
        tv.setTextColor(Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
        
        new Thread(){
            public void run(){
				while(true){
                try{
                    sleep(100);
                    runOnUiThread(new Runnable(){
						public void run(){
							tv.setTextColor(Color.rgb(new Random().nextInt(255),new Random().nextInt(255),new Random().nextInt(255)));
						}
					});
                }catch(Exception e)
                {}
				}
               // startActivity(new Intent(qidongtu.this,MainActivity.class));
              //  finish();
            }
        }.start();
		//Timer t=new Timer();
		//t.schedule(new Tt(),100);
        Handler handler = new Handler();
        //当计时结束时，跳转至主界面
        handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(qidongtu.this,MainActivity.class));
                    finish();
                }
            }, 200);
        
    }
}
