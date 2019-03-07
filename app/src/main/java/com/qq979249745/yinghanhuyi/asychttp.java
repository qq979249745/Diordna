package com.qq979249745.yinghanhuyi;
import android.os.*;
import org.apache.http.client.methods.*;
import java.util.*;
import org.apache.http.client.*;
import org.apache.http.impl.client.*;
import java.io.*;
import android.util.*;
import org.apache.http.*;
import org.apache.http.util.*;
import android.widget.*;

public class asychttp
{
	private static asychttp m;
	private asychttp()
	{
	}
	public static asychttp getinstance()
	{
		if(null==m)
		{
			synchronized(asychttp.class)
			{
				if(null==m)
				{
					m=new asychttp();
				}
			}
		}
		return m;
	}
	public static final String tag=asychttp.class.getSimpleName();
	public void 执行网络请求(String 路径,String 输入内容,huichuan a)
	{
			StringBuffer buf=new StringBuffer();
			buf.append(路径).append(输入内容);
			HttpGet 请求=new HttpGet(buf.toString());
			new 网络任务(a).execute(请求);
	}
	int 结果码;
	class 网络任务 extends AsyncTask<HttpUriRequest,Void,String>
	{
		huichuan a;
		public 网络任务(huichuan a1)
		{
			a=a1;
		}
		@Override
		protected String doInBackground(HttpUriRequest[] p1)
		{
			HttpClient 客户端=new DefaultHttpClient();
			try
			{
				HttpResponse 反应=客户端.execute(p1[0]);
				结果码=反应.getStatusLine().getStatusCode();
				if(结果码==HttpStatus.SC_OK)
				{
					HttpEntity 实体=反应.getEntity();
					return EntityUtils.toString(实体);
				}
				else
				{
					p1[0].abort();
				}
			}catch(ClientProtocolException e)
			{
				Log.e(tag,"ClientProtocolException");
			}
			catch (IOException e)
			{
				Log.e(tag,"IOException");
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			if(null!=result)
			{
				//Log.e(tag,"成功"+result);
				a.成功(result);
			}else
			{
				//Log.e(tag,"失败"+结果码);
				a.失败("错误-->"+结果码);
			}
		}
	}
	public interface huichuan
	{
		void 成功(String 结果);
		void 失败(String 错误);
	}
}
