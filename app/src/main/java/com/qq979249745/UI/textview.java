package com.qq979249745.UI;
import android.app.*;
import android.graphics.drawable.*;
import android.os.*;
import android.text.*;
import android.text.method.*;
import android.view.*;
import android.widget.*;
import com.qq979249745.*;

public class textview extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.ui_tv);
        TextView 带图片的tv = (TextView) findViewById(R.id.带图片的tv),
        百度一下=(TextView) findViewById(R.id.百度一下);  
        Drawable[] drawable = 带图片的tv.getCompoundDrawables();  
        // 数组下表0~3,依次是:左上右下  
        drawable[1].setBounds(100, 0, 200, 200);  
        带图片的tv.setCompoundDrawables(drawable[0], drawable[1], drawable[2],drawable[3]);
       
        String s1 = "<font color='green'><a href = 'http://m.blog.csdn.net/coder_pig/article/details/46997821'>textview详讲</a></font>";
        百度一下.setText(Html.fromHtml(s1));
        百度一下.setMovementMethod(LinkMovementMethod.getInstance());
    
	}
	
}
