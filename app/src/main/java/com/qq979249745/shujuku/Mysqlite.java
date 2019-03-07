package com.qq979249745.shujuku;
import android.content.*;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.*;
import java.util.*;
import android.database.*;

public class Mysqlite extends SQLiteOpenHelper
{
	public static final String 数据库名="qq979249745.db",
			表名="person",
			id="_id",
			名字="name",
			年龄="age";
	public static final int 数据库版本号=2;
	public Mysqlite(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
		super(context,name,factory,version);
	}
	public Mysqlite(Context context){
		super(context,数据库名,null,数据库版本号);
	}
	
	@Override
	public void onCreate(SQLiteDatabase p1)
	{
		
		String sql="create table "+表名+
		"("+id+" integer primary key autoincrement,"+名字
		+" varchar(10),"+年龄+" Integer)";
		
//		String sql="create table "+表名+
//			"("+时间+" Integer,"+名字
//			+" varchar(10),"+年龄+" Integer)";
		p1.execSQL(sql);
		
		/*SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//事务。。执行多条sql语句可以减少时间
		p1.beginTransaction();
		for(int i=0;i<30;i++){
			//sql="insert into "+Mysqlite.表名+" values("+i+",'张三"+i+"',20)";
			ContentValues cv=new ContentValues();
			cv.put(id,sdf.format(Calendar.getInstance().getTime())+i);
			cv.put(名字,"张三"+i);
			cv.put(年龄,i*3);
			p1.insert(表名,null,cv);
			
			//p1.execSQL(sql);
		}
		p1.setTransactionSuccessful();
		p1.endTransaction();*/
	}

	@Override
	public void onUpgrade(SQLiteDatabase p1, int p2, int p3)
	{
		// TODO: Implement this method
	}

	@Override
	public void onOpen(SQLiteDatabase db)
	{
		super.onOpen(db);
	}
	
}
