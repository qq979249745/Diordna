package com.qq979249745.jiajiemi;
import android.app.*;
import android.os.*;
import com.qq979249745.*;
import android.widget.*;
import android.view.*;
import android.util.*;

public class JiaJieMiActivity extends Activity
{
	private EditText et;
	private TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jjmactivity);
		et=(EditText) findViewById(R.id.jjmEt);
		tv=(TextView) findViewById(R.id.jjmTv);
		
	}
	public void 加密(View v){
		String s=et.getText().toString();
		String 密文=Base64.encodeToString(s.getBytes(),Base64.CRLF);
		tv.setText(tv.getText().toString()+"\n"+s+"的密文为："+密文);
	}
	public void 解密(View v){
		String s=et.getText().toString();
		
		byte[] b=Base64.decode(s,Base64.DEFAULT);
		String s1=new String(b);
		tv.setText(tv.getText().toString()+"\n"+s+"的明文为："+new String(b));
	}
}
