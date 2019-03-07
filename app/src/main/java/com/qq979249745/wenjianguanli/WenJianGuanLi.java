package com.qq979249745.wenjianguanli;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.view.ViewGroup.*;
import android.widget.*;
import android.widget.AdapterView.*;
import com.qq979249745.*;
import java.io.*;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import java.util.*;

public class WenJianGuanLi extends Activity implements OnItemClickListener,OnItemLongClickListener
{
    ListView lv;
	TextView tv;
	ArrayList<File> 列表;
	File 当前目录;
	WJAdapter adapter;
	PopupWindow 长按弹出窗;
	EditText 编辑框;
	AlertDialog.Builder 对话窗;
	int 被长按项;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.wenjianguanli);
        //设置状态栏为透明
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		lv=(ListView) findViewById(R.id.文件管理lv);
		tv=(TextView) findViewById(R.id.文件管理tv);
		tv.setText(Environment.getExternalStorageDirectory().toString());
		遍历目录();
		adapter=new WJAdapter(列表,this);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);
		
    }
	@Override
	public void onItemClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		if(列表.get(p3).isDirectory()){
			tv.setText(列表.get(p3).toString());
			遍历目录();
			adapter.set列表(列表);
			adapter.notifyDataSetChanged();
		}else if(列表.get(p3).getName().toLowerCase().endsWith(".txt")){
			Intent i=new Intent(WenJianGuanLi.this,WenJianBianJi.class);
			i.putExtra("文件路径",列表.get(p3).getAbsolutePath());
			startActivity(i);
		}
	}
	@Override
	public boolean onItemLongClick(AdapterView<?> p1, View p2, int p3, long p4)
	{
		被长按项=p3;
		LayoutInflater i=LayoutInflater.from(this);
		View 我的view=i.inflate(R.layout.wjchangan,null);
		长按弹出窗=new PopupWindow(我的view,(int)(getWindowManager().getDefaultDisplay().getWidth()*0.618),LayoutParams.WRAP_CONTENT);//单位px
		我的view.findViewById(R.id.新建文件夹).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					长按弹出窗.dismiss();
					新建文件夹(true);
					对话窗.show();
				}
			});
		我的view.findViewById(R.id.新建文件).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					长按弹出窗.dismiss();
					新建文件夹(false);
					对话窗.show();
				}
			});
		我的view.findViewById(R.id.删除).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					长按弹出窗.dismiss();
					删除提示();
					对话窗.show();
				}
			});
		我的view.findViewById(R.id.重命名).setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View p1)
				{
					长按弹出窗.dismiss();
					重命名();
					对话窗.show();
				}
			});
		长按弹出窗.setFocusable(true);
		长按弹出窗.showAtLocation(p2,Gravity.CENTER,0,0);
		return true;
	}
    public void 遍历目录(){
		File f=new File(tv.getText().toString());
		File[] 列表1=f.listFiles();
		列表=new ArrayList<File>();
		for(File f1:列表1){
			列表.add(f1);
		}
		Collections.sort(列表,new 按时间降序());
		

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(keyCode==KeyEvent.KEYCODE_BACK){
			当前目录=new File(tv.getText().toString());
			if(!当前目录.toString().equals("/storage/emulated/0")){
				tv.setText(当前目录.getParent());
				遍历目录();
				adapter.set列表(列表);
				adapter.notifyDataSetChanged();
			}else
			this.finish();
		}
		return true;
	}
	public void 新建文件夹(final boolean b){
		编辑框=new EditText(WenJianGuanLi.this);
			对话窗=new AlertDialog.Builder(WenJianGuanLi.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
			.setTitle("新建"+(b?"文件夹":"文件")).setView(编辑框)
			.setPositiveButton("确认",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					String s=tv.getText()+"/"+编辑框.getText().toString();
					File f=new File(s);
					try
					{
						if (b)
							Toast.makeText(WenJianGuanLi.this, "新建文件夹" + (f.mkdirs() ?"成功": "失败"), Toast.LENGTH_SHORT).show();
						else
							Toast.makeText(WenJianGuanLi.this, "新建文件" + (f.createNewFile() ?"成功": "失败"), Toast.LENGTH_SHORT).show();
					}
					catch (IOException e)
					{}
					遍历目录();
					adapter.set列表(列表);
					adapter.notifyDataSetChanged();
				}
			}).setNegativeButton("取消",null);
	}
	public void 删除提示(){
		对话窗=new AlertDialog.Builder(WenJianGuanLi.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
			.setTitle("确定删除"+列表.get(被长按项)+"吗？")
			.setPositiveButton("确认",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					删除(列表.get(被长按项));
					Toast.makeText(WenJianGuanLi.this, "删除" + (列表.get(被长按项).exists() ?"失败": "成功"), Toast.LENGTH_SHORT).show();
					遍历目录();
					adapter.set列表(列表);
					adapter.notifyDataSetChanged();
				}
			}).setNegativeButton("取消",null);
	}
	public void 重命名(){
		编辑框=new EditText(WenJianGuanLi.this);
		编辑框.setText(列表.get(被长按项).getName());
		对话窗=new AlertDialog.Builder(WenJianGuanLi.this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT)
			.setTitle("重命名").setView(编辑框)
			.setPositiveButton("确认",new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					String s=tv.getText()+"/"+编辑框.getText().toString();
					File f=new File(s);
					if(f.exists())
						Toast.makeText(WenJianGuanLi.this, "改名字已存在", Toast.LENGTH_SHORT).show();
					else{
						Toast.makeText(WenJianGuanLi.this, "重命名" + (列表.get(被长按项).renameTo(f)?"成功": "失败"), Toast.LENGTH_SHORT).show();
						遍历目录();
						adapter.set列表(列表);
						adapter.notifyDataSetChanged();
					}
				}
			}).setNegativeButton("取消",null);
	}
	public void 删除(File f){
		if(f.isDirectory()){
			File[] f1=f.listFiles();
			for(File f2:f1){
				if(f2.isDirectory())
					删除(f2);
				f2.delete();
			}
		}
		f.delete();
	}
}
class 按时间降序 implements Comparator<File>
{

	@Override
	public int compare(File file1, File file2)
	{
		if(file1.lastModified() < file2.lastModified()) {
			return 1;// 最后修改的文件在前
		} else {
			return -1;
		}
	}

	
}
