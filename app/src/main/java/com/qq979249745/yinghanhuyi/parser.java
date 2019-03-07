package com.qq979249745.yinghanhuyi;
import org.json.*;
import java.util.*;

public class parser
{
	public static result 解析数据(String 结果)
	{
		try
		{
			JSONObject rootobj=new JSONObject(结果);
			if(rootobj.getInt("errorCode")==0)
			{
				result 结果1=new result();
				if(rootobj.has("query"))
				{
					String 关键字=rootobj.getString("query");
					结果1.关键字=关键字;
				}
				if(rootobj.has("translation"))
				{
					结果1.有道翻译=rootobj.getString("translation");
				}
				if(rootobj.has("basic"))
				{
					JSONObject basic=rootobj.getJSONObject("basic");
					/*if(basic.has("phonetic"))
					{
						String 中音=basic.getString("phonetic");
						结果1.中文音标=中音;
					}*/
					if(basic.has("us-phonetic"))
					{
						String 美音=basic.getString("us-phonetic");
						结果1.美式发音=美音;
					}
					if(basic.has("uk-phonetic"))
					{
						String 英音=basic.getString("uk-phonetic");
						结果1.英式发音=英音;
					}
					if(basic.has("explains"))
					{
						JSONArray 基本=basic.getJSONArray("explains");
						List<String> explains=new ArrayList<String>();
						for(int i=0;i<基本.length();i++)
						{
							explains.add(基本.getString(i));
						}
						结果1.基本释义=explains;
					}
				}
				if(rootobj.has("web"))
				{
					List<web> webs=new ArrayList<web>();
					JSONArray webarray=rootobj.getJSONArray("web");
					for(int i=0;i<webarray.length();i++)
					{
						JSONObject webobj=webarray.getJSONObject(i);
						web web1=new web();
						String key=webobj.getString("key");
						web1.key=key;
						List<String> values=new ArrayList<String>();
						JSONArray va=webobj.getJSONArray("value");
						for(int j=0;j<va.length();j++)
						{
							values.add(va.getString(j));
						}
						web1.values=values;
						webs.add(web1);
					}
					结果1.webs=webs;
				}
				return 结果1;
			}
			else{
				result 结果1=new result();
				结果1.关键字=rootobj.getString("");
				return 结果1;
			}
		}catch(JSONException e){
			
		}
		return null;
	}
}
