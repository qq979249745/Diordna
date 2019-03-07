package com.qq979249745.yinghanhuyi;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.qq979249745.*;
import com.qq979249745.yinghanhuyi.asychttp.huichuan;


public class YhhyActivity extends Activity
{
	private TextView 显示结果;
	private ImageView 语音查询;
	private EditText 输入框;
	private Button 查询按钮;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yhhy);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		显示结果=(TextView) findViewById(R.id.显示结果);
		语音查询=(ImageView) findViewById(R.id.语音查询);
		输入框=(EditText) findViewById(R.id.输入框);
		查询按钮=(Button) findViewById(R.id.查询按钮);
		查询按钮.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					String 输入内容=输入框.getText().toString();
					if(输入内容.length()>0)
					try{
						asychttp.getinstance().执行网络请求(contents.搜索路径,输入内容,a);
					}catch(Exception e){
						Toast.makeText(YhhyActivity.this,"错误",Toast.LENGTH_SHORT).show();
					}
				}
			});
	}
	huichuan a=new huichuan()
	{
		public void 成功(String 结果)
		{
			显示结果.setText(parser.解析数据(结果).toString());
			//显示结果.setText(结果);
		}
		public void 失败(String 错误)
		{
			Toast.makeText(YhhyActivity.this,"数据错误"+错误,Toast.LENGTH_SHORT).show();
		}
	};
}
