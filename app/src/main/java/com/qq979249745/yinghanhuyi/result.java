package com.qq979249745.yinghanhuyi;
import java.util.*;

public class result
{
	public String 关键字,有道翻译,美式发音,英式发音;
	public List<String> 基本释义;
	public List<web> webs;

	@Override
	public String toString()
	{
		return ""+检查是否为空(关键字)+"\n有道翻译"+检查是否为空(有道翻译)+"\n美式发音∶"+检查是否为空(美式发音)+"\n英式发音∶"+检查是否为空(英式发音)+
		"\n基本释义∶"+得到基本释义()+"\n网络释义∶"+得到web释义();
	}
	public String 检查是否为空(String 单词)
	{
		return 单词==null?"无":单词;
	}
	public String 得到基本释义()
	{
		StringBuffer buf=new StringBuffer();
		if(null!=基本释义&&基本释义.size()>0)
		{
			for(int i=0;i<基本释义.size();i++)
				buf.append(基本释义.get(i)).append("，");
			return buf.deleteCharAt(buf.length()-1).toString();
		}
		return "无";
	}
	public String 得到web释义()
	{
		StringBuffer buf=new StringBuffer();
		if(null!=webs&&webs.size()>0)
		{
			for(int i=0;i<webs.size();i++)
				buf.append(webs.get(i).toString()).append("；");
			return buf.deleteCharAt(buf.length()-1).toString();
		}
		return "无";
	}
	
}
