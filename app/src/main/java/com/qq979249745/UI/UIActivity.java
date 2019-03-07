package com.qq979249745.UI;
import android.app.*;
import android.os.*;
import android.view.*;
import com.qq979249745.*;
import android.widget.*;
import android.widget.AdapterView.*;
import android.content.*;


public class UIActivity extends Activity
{
	ListView ui_lv;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.ui_main);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		ui_lv=(ListView) findViewById(R.id.ui_lv);
		String 内容[]={"文本框(详讲)","按钮"};
		ArrayAdapter<String> 适配器=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,内容);
		ui_lv.setAdapter(适配器);
		//Toast.makeText(this,"。",Toast.LENGTH_SHORT).show();
		ui_lv.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
				{
					if(p3==0)
					startActivity(new Intent(UIActivity.this,textview.class));
					finish();
				}
		});
	}
	
}
