package com.qq979249745.socketTest;
import android.app.*;
import android.os.*;
import com.qq979249745.*;
import java.io.*;
import java.net.*;
import android.widget.*;
import android.view.*;

public class SocketActivity extends Activity
{
	TextView Tv;
	EditText Et;
	ServerSocket 服务端=null;
	Socket 服务端socket=null,客户端socket=null;
	String s;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		Tv=(TextView) findViewById(R.id.web_tv);
		Et=(EditText) findViewById(R.id.web_et);
	}
	public void 打开服务器(View v){
		new Thread(){
				public void run(){
					try
					{
						服务端 = new ServerSocket(8888);
						s="服务器已启动。\n";
						信息栏();
						服务端socket = 服务端.accept();
						s="客户端已连接。\n";
						信息栏();
						DataInputStream dis=new DataInputStream(服务端socket.getInputStream());
						while(dis!=null){
							s = dis.readUTF();
							信息栏();
							sleep(1000);
						}
					}catch (Exception e)
					{}
				}
			}.start();
	}
	public void 连接服务器(View v){
		new Thread(){
			public void run(){
				try{
					客户端socket = new Socket("127.0.0.1", 8888);
					DataInputStream dis=new DataInputStream(客户端socket.getInputStream());
					while(dis!=null){
						s = dis.readUTF();
						信息栏();
						sleep(1000);
					}
				}catch (Exception e)
				{}
			}
		}.start();
	}
	public void 关闭服务器(View v){
		try
		{
			服务端socket.close();
			服务端.close();
			客户端socket.close();
		}catch (Exception e)
		{}
	}
	public void 发送给服务端(View v){
		if(客户端socket!=null){
			new Thread(){
				public void run()
				{
					try{
						DataOutputStream dos=new DataOutputStream(客户端socket.getOutputStream());
						dos.writeUTF("客户端：" + Et.getText()+"\n");
					}catch (IOException e)
					{}
				}}.start();
		}
	}
	public void 发送给客户端(View v){
		if(服务端socket!=null){
			new Thread(){
				public void run(){
					try{
						DataOutputStream dos=new DataOutputStream(服务端socket.getOutputStream());
						dos.writeUTF("服务端："+Et.getText()+"\n");
					}catch (IOException e)
					{}
				}
			}.start();
		}
	}
	public void 信息栏(){
		runOnUiThread(new Runnable(){
				public void run(){
					Tv.setText(Tv.getText()+s);
				}
			});
	}
}
