package com.qq979249745.wuziqi;

import android.app.*;
import android.os.*;
import android.view.*;
import com.qq979249745.*;

public class WzqActivity extends Activity 
{
    Wuziqi wzq;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.wuziqi);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        
        wzq=(Wuziqi) findViewById(R.id.wuzhiqi);
    }
    public void 再来一局(View v){
        wzq.再来一局();
    }
    public void 悔棋(View v){
        wzq.悔棋();
    }
}

