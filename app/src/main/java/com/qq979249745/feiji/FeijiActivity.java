package com.qq979249745.feiji;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

public class FeijiActivity extends Activity implements DialogInterface.OnClickListener 
{

	DaFeiJiGameView view = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow ( ).setFlags ( WindowManager.LayoutParams.FLAG_FULLSCREEN, 
								WindowManager.LayoutParams.FLAG_FULLSCREEN );//设置全屏

		view = new DaFeiJiGameView(this); 
		setContentView(view);


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			view.stop();

			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("你要退出吗？");
			alert.setNeutralButton("退出", this);
			alert.setNegativeButton("继续", this);
			alert.create().show();

			return false;
		}

		return super.onKeyDown(keyCode, event);
	}

	public void onClick(DialogInterface dialog, int which) {

		if (which == -2) {
			view.start();
		} else {
			finish();
			//android.os.Process.killProcess(android.os.Process.myPid());
		}

	}

}

