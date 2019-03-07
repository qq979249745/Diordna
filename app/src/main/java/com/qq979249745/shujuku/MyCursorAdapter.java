package com.qq979249745.shujuku;
import android.view.*;
import android.database.*;
import com.qq979249745.*;
import android.app.*;
import android.database.sqlite.*;
import android.content.*;
import android.widget.*;
import android.view.View.*;

public class MyCursorAdapter extends CursorAdapter implements OnLongClickListener
{

	private Mysqlite 我的数据库;
	private EditText 编辑框;
	private Context 上下文;
	
	public MyCursorAdapter(Context context,Cursor cursor,int f,Mysqlite 我的数据库){
		super(context,cursor,f);
		this.我的数据库=我的数据库;
		this.上下文=context;
	}
	@Override
	public View newView(Context p1, Cursor p2, ViewGroup p3)
	{
		View view=LayoutInflater.from(p1).inflate(R.layout.shujukuitem,null);
		return view;
	}

	@Override
	public void bindView(View p1, final Context p2, Cursor p3)
	{
		final TextView 时间=(TextView) p1.findViewById(R.id.ItemId),
			姓名=(TextView) p1.findViewById(R.id.Item姓名),
			年龄=(TextView) p1.findViewById(R.id.Item年龄);
		String id=p3.getString(p3.getColumnIndex(Mysqlite.id));
		时间.setText(id);
		时间.setTag(id);
		姓名.setTag(id);
		年龄.setTag(id);
		姓名.setText(p3.getString(p3.getColumnIndex(Mysqlite.名字)));
		年龄.setText(p3.getString(p3.getColumnIndex(Mysqlite.年龄)));

		姓名.setOnClickListener(new OnClickListener(){
			public void onClick(final View v){
				编辑框=new EditText(p2);
				编辑框.setText(((TextView)v).getText());
				new AlertDialog.Builder(p2)
					.setTitle("修改姓名").setView(编辑框)
					.setPositiveButton("确认",new DialogInterface.OnClickListener(){
						@Override
						public void onClick(DialogInterface p1, int p2)
						{
							修改((String)(v.getTag()),编辑框.getText().toString(),Mysqlite.名字);
						}
					})
					.setNegativeButton("取消",null)
					.show();
				//Toast.makeText(p2,((TextView)v).getText(),Toast.LENGTH_SHORT).show();
			}
		});
		年龄.setOnClickListener(new OnClickListener(){
				public void onClick(final View v){
					编辑框=new EditText(p2);
					编辑框.setText(((TextView)v).getText());
					new AlertDialog.Builder(p2)
						.setTitle("修改年龄").setView(编辑框)
						.setPositiveButton("确认",new DialogInterface.OnClickListener(){
							@Override
							public void onClick(DialogInterface p1, int p2)
							{
								修改((String)(v.getTag()),编辑框.getText().toString(),Mysqlite.年龄);
							}
						})
						.setNegativeButton("取消",null)
						.show();
					//Toast.makeText(p2,((TextView)v).getText(),Toast.LENGTH_SHORT).show();
				}
			});
		时间.setOnLongClickListener(this);
		姓名.setOnLongClickListener(this);
		年龄.setOnLongClickListener(this);
		
	}
	@Override
	public boolean onLongClick(View p)
	{
		SQLiteDatabase db=我的数据库.getWritableDatabase();
		if(db.delete(Mysqlite.表名,Mysqlite.id+"="+(String)p.getTag(),null)>0)
		Toast.makeText(上下文,(String)p.getTag()+"已被删除",Toast.LENGTH_SHORT).show();
		//db.execSQL("update "+Mysqlite.表名+" set "+Mysqlite.id+" = "+Mysqlite.id+"-1 where "+Mysqlite.id+" > "+(String)p.getTag());
		//db.execSQL("alter table "+Mysqlite.表名+" AUTO_INCREMENT = AUTO_INCREMENT - 1");
		changeCursor(db.rawQuery("select * from "+Mysqlite.表名,null));
		db.close();
		return true;
	}
	public void 修改(String id,String 修改的内容,String 位置){
		SQLiteDatabase db=我的数据库.getWritableDatabase();
		//db.execSQL(sql);
		
		ContentValues cv=new ContentValues();  

        //cv.put(Mysqlite.ID,1);
		cv.put(位置,修改的内容);
		//cv.put(Mysqlite.年龄,12);
		db.update(Mysqlite.表名,cv,Mysqlite.id+"=?",new String[]{id});
		
		changeCursor(db.rawQuery("select * from "+Mysqlite.表名,null));
		db.close();
	}
}
