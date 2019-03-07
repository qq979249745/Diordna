package com.qq979249745.pingtu;
import android.app.*;
import android.os.*;
import com.qq979249745.*;
import android.view.*;
import android.widget.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.view.View.*;
import android.view.GestureDetector.*;
import android.view.animation.*;

public class PingTuActivity extends Activity
{
	private GridLayout gridlayout;
	private ImageView[][] 图片=new ImageView[3][4];
	private Bitmap 大图;
	private ImageView 空图片;
	private GameData gd空图片;
	private GestureDetector 手势;
	
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		手势 = new GestureDetector(this, new OnGestureListener(){

				@Override
				public boolean onDown(MotionEvent p1)
				{return false;}
				public void onShowPress(MotionEvent p1)
				{}
				public boolean onSingleTapUp(MotionEvent p1)
				{return false;}
				public boolean onScroll(MotionEvent p1, MotionEvent p2, float p3, float p4)
				{return false;}
				public void onLongPress(MotionEvent p1)
				{}
				public boolean onFling(MotionEvent p1, MotionEvent p2, float p3, float p4)
				{
					int i=gd空图片.x,j=gd空图片.y;
					boolean 左右=Math.abs(p1.getX()-p2.getX())>Math.abs(p1.getY()-p2.getY())?true:false;
					if(左右){
						if(p1.getX()>p2.getX()){
							j++;//左移
						}else{
							j--;
						}
					}else{
						if(p1.getY()>p2.getY()){
							i++;
						}else{
							i--;
						}
					}
					if(i<3&&j<4&&i>=0&&j>=0)
						移(图片[i][j]);
					return true;
				}
			});
		setContentView(R.layout.pingtu);
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        
		gridlayout=(GridLayout) findViewById(R.id.pingtuGridLayout);
		大图=((BitmapDrawable)getResources().getDrawable(R.drawable.pingtu1)).getBitmap();
		
	 	WindowManager wm=this.getWindowManager();
 		int 屏宽=wm.getDefaultDisplay().getWidth(),
			屏高=wm.getDefaultDisplay().getHeight();
		Bitmap 大图1=Bitmap.createScaledBitmap(大图,屏宽,屏宽*3/4,true);
		int 宽=大图1.getWidth();
		int 高=大图1.getHeight();
		宽=宽/4;
		高=高/3;
		for(int i=0;i<3;i++){
			for(int j=0;j<4;j++){
				Bitmap bm=Bitmap.createBitmap(大图1,j*宽,i*高,宽,高);
				图片[i][j]=new ImageView(this);
				图片[i][j].setImageBitmap(bm);
				图片[i][j].setPadding(1,1,1,1);
				gridlayout.addView(图片[i][j]);
				GameData gd=new GameData(i,j,i,j,bm);
				图片[i][j].setTag(gd);
				图片[i][j].setOnClickListener(new OnClickListener(){
					public void onClick(View v){
						移((ImageView)v);
					}
				});
			}
		}
		图片[2][3].setImageBitmap(null);
		空图片=图片[2][3];
		gd空图片=(GameData) 空图片.getTag();
	}
	public void 打乱顺序(View v){
		for(int i=0;i<100;i++){
			交换图片((int)(Math.random()*i)%3,(int)(Math.random()*i)%4);
		}
		交换图片(2,3);
	}
	public void 交换图片(int x,int y){
			if(x==gd空图片.x&&y==gd空图片.y)
				return;
			GameData gd=(GameData) 图片[x][y].getTag();
			图片[x][y].setImageBitmap(null);
			空图片.setImageBitmap(gd.bm);
			gd.x=gd空图片.x;
			gd.y=gd空图片.y;

			gd空图片.bm=gd.bm;
			gd空图片.x=x;
			gd空图片.y=y;
			图片[x][y].setTag(gd空图片);
			空图片.setTag(gd);
			空图片=图片[x][y];
		
		
	}
	public void 移(ImageView iv){
		final GameData gd=(GameData) iv.getTag();
		TranslateAnimation 动画=null;
		
		if(gd.x==gd空图片.x-1&&gd.y==gd空图片.y){//下移
			动画=new TranslateAnimation(0.1f,0.1f,0.1f,iv.getHeight());
		}else if(gd.x==gd空图片.x+1&&gd.y==gd空图片.y){//上移
			动画=new TranslateAnimation(0.1f,0.1f,0.1f,-iv.getHeight());
		}else if(gd.x==gd空图片.x&&gd.y==gd空图片.y-1){//右移
			动画=new TranslateAnimation(0.1f,iv.getWidth(),0.1f,0.1f);
		}else if(gd.x==gd空图片.x&&gd.y==gd空图片.y+1){//左移
			动画=new TranslateAnimation(0.1f,-iv.getWidth(),0.1f,0.1f);
		}
		if(动画!=null){
			动画.setDuration(100);
			//动画.setFillAfter(true);
			动画.setAnimationListener(new Animation.AnimationListener(){

					@Override
					public void onAnimationStart(Animation p1)
					{
						// TODO: Implement this method
					}

					@Override
					public void onAnimationEnd(Animation p1)
					{
						交换图片(gd.x,gd.y);
						GameData gd;
						
						for(int i=0;i<3;i++){
							for(int j=0;j<4;j++){
								gd=(GameData) 图片[i][j].getTag();
								if(gd.x1!=i||gd.y1!=j)
									return;
							}
						}
						Toast.makeText(PingTuActivity.this,"拼图成功",Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onAnimationRepeat(Animation p1)
					{
						// TODO: Implement this method
					}
				});
			iv.startAnimation(动画);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return 手势.onTouchEvent(event);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev)
	{
		手势.onTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
}

class GameData{
	int x,y,x1,y1;
	Bitmap bm;
	public GameData(int x, int y, int x1, int y1,Bitmap bm)
	{
		this.x = x;
		this.y = y;
		//x1,y1图片正确的位置
		this.x1 = x1;
		this.y1 = y1;
		this.bm=bm;
	}
}
