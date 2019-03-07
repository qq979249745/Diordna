package com.qq979249745.wenjianguanli;
import android.widget.*;
import android.view.*;
import com.qq979249745.*;
import java.io.*;
import android.content.*;
import android.graphics.drawable.*;
import android.content.pm.*;
import java.util.*;
import java.text.*;


public class WJAdapter extends BaseAdapter
{
	
	LayoutInflater 填充器;
	ArrayList<File> 列表;
	String 文件后缀;
	Context c;
	public WJAdapter(ArrayList<File> 列表,Context c){
		
		this.列表=列表;
		this.c=c;
		填充器=LayoutInflater.from(c);
	}
	public void set列表(ArrayList<File> 列表){
		this.列表=列表;
	}
	@Override
	public int getCount()
	{
		// TODO: Implement this method
		return 列表 == null ? 0 : 列表.size();
	}

	@Override
	public Object getItem(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	@Override
	public long getItemId(int p1)
	{
		// TODO: Implement this method
		return p1;
	}

	static class 二级优化{
		ImageView 图片;
		TextView 文件夹,文件时间,文件大小;
	}
	@Override
	public View getView(int p1, View p2, ViewGroup p3)
	{
		二级优化 a=new 二级优化();
		if(p2==null)//一级优化
		{
			p2=填充器.inflate(R.layout.mulv,null);
			a.图片=(ImageView) p2.findViewById(R.id.wjlvIV);
			a.文件夹=(TextView)p2.findViewById(R.id.wjlvtv);
			a.文件时间=(TextView) p2.findViewById(R.id.wj时间);
			a.文件大小=(TextView) p2.findViewById(R.id.wj大小);
			p2.setTag(a);
		}
		else{
			a=(二级优化)p2.getTag();
		}
		a.文件夹.setText(列表.get(p1).getName());
		a.文件时间.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(列表.get(p1).lastModified())));
		if(!列表.get(p1).isFile()){
			a.图片.setImageResource(R.drawable.mulvtubiao);
			a.文件大小.setText("");
		}else{
			long fileS=列表.get(p1).length();
			DecimalFormat df = new DecimalFormat("#.00"); 
			String fileSizeString = ""; 
			if (fileS < 1024) { 
				fileSizeString = df.format((double) fileS) + "B"; 
			} else if (fileS < 1048576) { 
				fileSizeString = df.format((double) fileS / 1024) + "K"; 
			} else if (fileS < 1073741824) { 
				fileSizeString = df.format((double) fileS / 1048576) + "M"; 
			} else { 
				fileSizeString = df.format((double) fileS / 1073741824) + "G"; 
			}
			a.文件大小.setText(fileSizeString);
			
			文件后缀=列表.get(p1).getName().toLowerCase();
			if(文件后缀.endsWith(".txt")){
				a.图片.setImageResource(R.drawable.wenbenwenjian);
			}else if(文件后缀.endsWith(".mp3")){
				a.图片.setImageResource(R.drawable.yinyuewenjian);
			}else if(文件后缀.endsWith(".mp4")){
				a.图片.setImageResource(R.drawable.shipinwenjian);
			}else if(文件后缀.endsWith(".jpg")||文件后缀.endsWith("png")){
				a.图片.setImageResource(R.drawable.tupianwenjian);
			}else if(文件后缀.endsWith(".apk")){
				String apkPath=列表.get(p1).getAbsolutePath();
				PackageManager pm = c.getPackageManager();
				PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
				if (info != null) {
					ApplicationInfo apkInfo = info.applicationInfo;
					apkInfo.publicSourceDir = apkPath;
					Drawable icon = apkInfo.loadIcon(pm);
					a.图片.setImageDrawable(icon);
				}else
					a.图片.setImageResource(R.drawable.apkweizhitubiao);
			}else{
				a.图片.setImageResource(R.drawable.weizhiwenjian);
			}
		}
		return p2;
	}

}
