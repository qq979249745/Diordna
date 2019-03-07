package com.qq979249745.wuziqi;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.util.*;
import android.view.*;
import android.widget.*;
import com.qq979249745.*;
import java.util.*;

public class Wuziqi extends View
{

    private int mPanelWidth;
    private float mLineHeight;
    private int MAX_LINE=15;

    private Paint mPaint=new Paint();
    private Bitmap white,black;

    private boolean isWhite=true;
    private ArrayList<Point> mWhiteArray=new ArrayList<>(),
    mblackArray=new ArrayList<>();
    private boolean 游戏结束=false;


    public Wuziqi(Context c,AttributeSet a){
        super(c,a);
        //setBackgroundColor(0xd0d0d000);
        init();
    }

    private void init()
    {
        mPaint.setColor(0xD1A18200);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        white=BitmapFactory.decodeResource(getResources(),R.drawable.white);
        black=BitmapFactory.decodeResource(getResources(),R.drawable.black);
        //drawBoard(c);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthsize=MeasureSpec.getSize(widthMeasureSpec),
            widthmode=MeasureSpec.getMode(widthMeasureSpec),
            heightsize=MeasureSpec.getSize(heightMeasureSpec),
            heightmode=MeasureSpec.getMode(heightMeasureSpec);
        int width=Math.min(widthsize,heightsize);
        if(widthmode==MeasureSpec.UNSPECIFIED){
            width=heightsize;
        }else if(heightmode==MeasureSpec.UNSPECIFIED){
            width=widthmode;
        }
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);
        mPanelWidth=w;
        mLineHeight=mPanelWidth*1.0f/MAX_LINE;

        int 棋子边长=(int) (mLineHeight*0.75);
        white=Bitmap.createScaledBitmap(white,棋子边长,棋子边长,false);
        black=Bitmap.createScaledBitmap(black,棋子边长,棋子边长,false);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(游戏结束) 
            return false;
        int action=event.getAction();
        if(action==MotionEvent.ACTION_UP){
            int x=(int) event.getX(),
                y=(int) event.getY();
            Point p=new Point((int)(x/mLineHeight),(int)(y/mLineHeight));
            if(mWhiteArray.contains(p)||mblackArray.contains(p))
                return false;
            if(isWhite){
                mWhiteArray.add(p);
                invalidate();
                游戏结束=检测五子(p,mWhiteArray);
                if(游戏结束){
                    Toast.makeText(getContext(),"白棋赢了",Toast.LENGTH_SHORT).show();
                }
            }else{
                mblackArray.add(p);
                invalidate();
                游戏结束=检测五子(p,mblackArray);
                if(游戏结束){
                    Toast.makeText(getContext(),"黑棋赢了",Toast.LENGTH_SHORT).show();
                }
            }



            isWhite=!isWhite;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        drawBoard(canvas);
        画棋子(canvas);

    }

    private boolean 检测五子(Point p,List<Point> l)
    {
        int 棋子数 = 1;

        for(int i=1;i<5;i++){
            if(l.contains(new Point(p.x-i,p.y))){
                棋子数++;
            }
            if(l.contains(new Point(p.x+i,p.y))){
                棋子数++;
            }

        }
        if(棋子数>=5){

            return true;
        }
        棋子数=1;
        for(int i=1;i<5;i++){
            if(l.contains(new Point(p.x,p.y-i))){
                棋子数++;
            }
            if(l.contains(new Point(p.x,p.y+i))){
                棋子数++;
            }
        }
        if(棋子数>=5){

            return true;
        }
        棋子数=1;
        for(int i=1;i<5;i++){
            if(l.contains(new Point(p.x+i,p.y-i))){
                棋子数++;
            }
            if(l.contains(new Point(p.x-i,p.y+i))){
                棋子数++;
            }
        }
        if(棋子数>=5){

            return true;
        }
        棋子数=1;
        for(int i=1;i<5;i++){
            if(l.contains(new Point(p.x-i,p.y-i))){
                棋子数++;
            }
            if(l.contains(new Point(p.x+i,p.y+i))){
                棋子数++;
            }
        }
        if(棋子数>=5){

            return true;
        }

        return false;
    }

    private void 画棋子(Canvas c)
    {
        for(int i=0,n=mWhiteArray.size();i<n;i++){
            Point p=mWhiteArray.get(i);
            //c.drawText("白",p.x,p.y,mPaint);
            c.drawBitmap(white,(p.x+0.125f)*mLineHeight,(p.y+0.125f)*mLineHeight,null);
        }
        for(int i=0,n=mblackArray.size();i<n;i++){
            Point p=mblackArray.get(i);
            //c.drawText("黑",p.x,p.y,mPaint);

            c.drawBitmap(black,(p.x+0.125f)*mLineHeight,(p.y+0.125f)*mLineHeight,null);
        }
    }

    private void drawBoard(Canvas canvas)
    {
        for(int i=0;i<MAX_LINE;i++){
            int startX=(int) (0.5*mLineHeight),
                endX=mPanelWidth-startX,
                y=(int)((0.5+i)*mLineHeight);
            canvas.drawLine(startX,y,endX,y,mPaint);
            canvas.drawLine(y,startX,y,endX,mPaint);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState()
    {
        Bundle b=new Bundle();
        b.putParcelable("instance",super.onSaveInstanceState());
        b.putBoolean("游戏结束",游戏结束);
        b.putParcelableArrayList("白棋数组",mWhiteArray);
        b.putParcelableArrayList("黑棋数组",mblackArray);
        return b;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state)
    {
        if(state instanceof Bundle){
            Bundle b=(Bundle)state;
            游戏结束=b.getBoolean("游戏结束");
            mWhiteArray=b.getParcelableArrayList("白棋数组");
            mblackArray=b.getParcelableArrayList("黑棋数组");
            super.onRestoreInstanceState(b.getParcelable("instance"));
            return;
        }
        super.onRestoreInstanceState(state);
    }
    public void 再来一局(){
        mWhiteArray.clear();
        mblackArray.clear();
        游戏结束=false;
        isWhite=true;
        invalidate();
    }
    public void 悔棋(){
        游戏结束=false;
        if(isWhite){
            if(!mblackArray.isEmpty()){
                mblackArray.remove(mblackArray.size()-1);
                isWhite=!isWhite;
            }
        }
        else{
            if(!mWhiteArray.isEmpty()){
                mWhiteArray.remove(mWhiteArray.size()-1);
                isWhite=!isWhite;
            }
        }


        invalidate();
    }
}
