package com.qq979249745.shujuku;
import android.app.*;
import android.os.*;
import com.qq979249745.*;
import android.view.*;
import android.widget.*;
import android.database.sqlite.*;
import android.database.*;
import android.text.*;
import android.view.View.*;
import android.widget.AdapterView.*;
import android.content.*;
import java.text.*;
import java.util.*;

public class SJKActivity extends Activity
{
	private ListView lv;
	private EditText 搜索栏;
	private TextView 添加;
	private Mysqlite 我的数据库;
	private Cursor cursor;
	private MyCursorAdapter adapter;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shujukuactivity);
		
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		lv=(ListView) findViewById(R.id.数据库lv);
		搜索栏=(EditText) findViewById(R.id.数据库搜索栏);
		添加=(TextView)findViewById(R.id.数据库添加);
		我的数据库=new Mysqlite(this);
		SQLiteDatabase db=我的数据库.getWritableDatabase();

		//db.delete(Mysqlite.表名,Mysqlite.年龄+"=3",null);
		cursor=db.rawQuery("select * from "+Mysqlite.表名,null);
	
		//cursor=db.query(Mysqlite.表名,new String[]{Mysqlite.时间,Mysqlite.名字,Mysqlite.年龄},null,null,null,null,null,null);
		
		
		//db.close();在这里写会出错
		
		adapter=new MyCursorAdapter(this,cursor,CursorAdapter.FLAG_AUTO_REQUERY,我的数据库);
		lv.setAdapter(adapter);
		
	//	简单的数据库适配器
//		SimpleCursorAdapter sca=new SimpleCursorAdapter(this,R.layout.shujukuitem,cursor,
//			new String[]{Mysqlite.时间,Mysqlite.名字,Mysqlite.年龄},
//			new int[]{R.id.Item时间,R.id.Item姓名,R.id.Item年龄},
//			SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//		lv.setAdapter(sca);
	
		db.close();
		
		//Toast.makeText(this,db==null?"空":"不为空",Toast.LENGTH_SHORT).show();
		搜索栏.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
	
				}

				@Override
				public void afterTextChanged(Editable p1)
				{
					String s=p1.toString();
					SQLiteDatabase db=我的数据库.getWritableDatabase();
					cursor=db.rawQuery("select * from "+Mysqlite.表名+" where "+Mysqlite.id+" like ? or "+Mysqlite.名字+" like ? or "+Mysqlite.年龄+" like ?",new String[]{"%"+s+"%","%"+s+"%","%"+s+"%"});
					adapter.changeCursor(cursor);
					db.close();
				}
			});
		添加.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				SQLiteDatabase db=我的数据库.getWritableDatabase();
				ContentValues cv=new ContentValues();
				
				cv.put(Mysqlite.名字,"");
				
				db.insertOrThrow(Mysqlite.表名,null,cv);
				adapter.changeCursor(db.rawQuery("select * from "+Mysqlite.表名,null));
				db.close();
			}
		});
	}
}
