package com.qq979249745.lanya;
import android.app.*;
import android.os.*;
import com.qq979249745.*;
import android.widget.*;
import android.bluetooth.*;
import android.view.View.*;
import android.view.*;
import android.content.*;
import java.util.*;

public class LanYa extends Activity implements OnClickListener
{
	
	private TextView tv;
	private Button 蓝牙开关;
	private EditText 蓝牙名;
	private ListView 已配对列表;
	private ListView 可用设备列表;
	private BluetoothAdapter 蓝牙;
	private String bluetoothStatus="蓝牙状态：";
	private Set<BluetoothDevice> set1;
	private String[] string1;
	private ArrayAdapter 适配器;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lanya);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		tv=(TextView) findViewById(R.id.蓝牙状态);
		蓝牙开关=(Button) findViewById(R.id.蓝牙开关按钮);
		蓝牙名=(EditText) findViewById(R.id.蓝牙名);
		已配对列表=(ListView) findViewById(R.id.已配对列表);
		可用设备列表=(ListView) findViewById(R.id.可用设备列表);
		
		蓝牙=BluetoothAdapter.getDefaultAdapter();
		
		if(蓝牙!=null){
			蓝牙开关.setOnClickListener(this);
			蓝牙名.setText(蓝牙.getName());
			BroadcastReceiver mReceiver = new BroadcastReceiver() {

				@Override
				public void onReceive(Context context, Intent intent) {

					switch (intent.getAction()) {
						case BluetoothAdapter.ACTION_STATE_CHANGED:
							int blueState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
							switch (blueState) {
								case BluetoothAdapter.STATE_TURNING_ON:
									bluetoothStatus="蓝牙正在打开";
									break;
								case BluetoothAdapter.STATE_ON:
									bluetoothStatus="蓝牙已打开";

									break;
								case BluetoothAdapter.STATE_TURNING_OFF:
									bluetoothStatus="蓝牙正在关闭";
									//Ble.toReset(mContext);
									break;
								case BluetoothAdapter.STATE_OFF:
									bluetoothStatus="蓝牙已关闭";

									//Ble.toReset(mContext);
									break;
								case BluetoothAdapter.STATE_CONNECTING:
									bluetoothStatus="蓝牙正在连接…";
									break;
							}
							更新文本();
							break;

					}

				}
			};

			this.registerReceiver(mReceiver, makeFilter());
			更新文本();
			
			//适配器=new ArrayAdapter(this,android.R.layout.simple_list_item_1,set1);
		}
		
		
	}
	private IntentFilter makeFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        return filter;
    }

    
	@Override
	public void onClick(View p1)
	{
		//if(!ba.isEnabled())
			//Toast.makeText(this,ba.enable()?"打开成功":"打开失败",Toast.LENGTH_SHORT).show();

		switch(p1.getId()){
			case R.id.蓝牙开关按钮:
				if(!蓝牙.isEnabled()){
					Toast.makeText(this,蓝牙.enable()?"打开成功":"打开失败",Toast.LENGTH_SHORT).show();
					
				}else{
					Toast.makeText(this,蓝牙.disable()?"关闭蓝牙成功":"关闭蓝牙失败",Toast.LENGTH_SHORT).show();
				}
					
		}
	
	}
	public void 更新文本(){
		蓝牙.getState();
		tv.setText(bluetoothStatus);
		蓝牙开关.setText((蓝牙.isEnabled()?"关闭":"打开")+"蓝牙");
		set1=蓝牙.getBondedDevices();
		for(BluetoothDevice bd:set1){
		
		}
	}
	
}
