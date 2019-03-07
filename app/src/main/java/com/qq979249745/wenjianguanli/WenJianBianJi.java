package com.qq979249745.wenjianguanli;
import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.widget.*;
import com.qq979249745.*;
import java.io.*;
import org.apache.http.util.*;

public class WenJianBianJi extends Activity
{
	EditText et;
	File 文件路径;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wenjianbj);
		et=(EditText) findViewById(R.id.文件编辑et);
		//文字中间加横线
		//et.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); 
		//文字下面加横线
		//et.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
		文件路径=new File(getIntent().getStringExtra("文件路径"));
		setTitle(文件路径.getName());
		try
		{
			/*FileInputStream in=new FileInputStream(文件路径);
			InputStreamReader 读中文文件=new InputStreamReader(in,"utf-8");
			sb = new StringBuffer();
			while(读中文文件.ready())
				sb.append((char)读中文文件.read());
			et.setText(sb);
			in.close();
			读中文文件.close();*/
			FileInputStream fin = new FileInputStream(文件路径);
			int length = fin.available(); 
			byte [] buffer = new byte[length]; 
			fin.read(buffer);
			s=EncodingUtils.getString(buffer,"UTF-8");
			et.setText(s);
			fin.close();     
		}
		catch (IOException e)
		{}
	}
	@Override
	public void onBackPressed()
	{
		if(!s.equals(et.getText().toString())){
			new AlertDialog.Builder(WenJianBianJi.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
				.setTitle("文件已被修改，是否保存？")
				.setPositiveButton("是",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						try
						{
							/*DataOutputStream out=new DataOutputStream(
								new FileOutputStream(文件路径.getAbsolutePath()));
							out.writeUTF(et.getText().toString());
							out.close();*/
							FileOutputStream fout = new FileOutputStream(文件路径);
							byte [] bytes = et.getText().toString().getBytes(); 
							fout.write(bytes); 
							fout.close(); 
						}
						catch (Exception e)
						{}
						finish();
					}
				}).setNegativeButton("否", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						finish();
					}
				}).show();
		}else{
			finish();
		}
	}
	
}
