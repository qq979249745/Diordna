package com.qq979249745;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.qq979249745.UI.*;
import com.qq979249745.wuziqi.*;
import com.qq979249745.wenjianguanli.*;
import com.qq979249745.socketTest.*;
import com.qq979249745.yinghanhuyi.*;
import com.sunglab.bigbanghd.*;
import com.qq979249745.feiji.*;
import com.qq979249745.pingtu.*;
import com.qq979249745.shujuku.*;
import com.qq979249745.jiajiemi.*;
import com.qq979249745.lanya.*;

public class MainActivity extends Activity 
{
	ListView lv_主界面;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
        setContentView(R.layout.main);
		//设置状态栏为透明
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		lv_主界面=(ListView) findViewById(R.id.List_主界面);
		String[] 内容={"常用基本UI控件","socket聊天室","五子棋游戏","文件管理","英汉互译",
		"魔幻粒子","打飞机","拼图游戏","数据库例子","加密解密","蓝牙"};
		ArrayAdapter<String> 适配器=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,内容);
		lv_主界面.setAdapter(适配器);
		lv_主界面.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if(p3==0)
					startActivity(new Intent(MainActivity.this,UIActivity.class));
					else if(p3==1)
					startActivity(new Intent(MainActivity.this,SocketActivity.class));
                    else if(p3==2)
                    startActivity(new Intent(MainActivity.this,WzqActivity.class));
                    else if(p3==3)
                    startActivity(new Intent(MainActivity.this,WenJianGuanLi.class));
					else if(p3==4)
					startActivity(new Intent(MainActivity.this,YhhyActivity.class));
					else if(p3==5)
					startActivity(new Intent(MainActivity.this,s.class));
					else if(p3==6)
					startActivity(new Intent(MainActivity.this,FeijiActivity.class));
					else if(p3==7)
					startActivity(new Intent(MainActivity.this,PingTuActivity.class));
					else if(p3==8)
					startActivity(new Intent(MainActivity.this,SJKActivity.class));
					else if(p3==9)
					startActivity(new Intent(MainActivity.this,JiaJieMiActivity.class));
					else if(p3==10)
					startActivity(new Intent(MainActivity.this,LanYa.class));
					
				}
		});
    }
}
