package com.qq979249745.yinghanhuyi;
import java.util.*;

public class web
{
	public String key;
	public List<String> values;
	@Override
	public String toString()
	{
		return key+"∶"+得到网络释义();
	}
	public String 得到网络释义()
	{
		StringBuffer buf=new StringBuffer();
		if(null!=values&&values.size()>0)
		{
			for(int i=0;i<values.size();i++)
			buf.append(values.get(i)).append("，");
			return buf.deleteCharAt(buf.length()-1).toString();
		}
		return "无";
	}
	
}
