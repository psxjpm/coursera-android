package course.examples.broadcastreceiver.singlebroadcastdynamicregistration;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SingleBroadcast extends Activity {

	//Intent Action String
	private static final String CUSTOM_INTENT = "course.examples.BroadcastReceiver.show_toast";
	
	//Intent filter object passing action string
	private final IntentFilter intentFilter = new IntentFilter(CUSTOM_INTENT);
	
	//BroadcastReceiver instance
	private final Receiver receiver = new Receiver();

	private LocalBroadcastManager mBroadcastMgr;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//receiving intents WITHIN application (Dentro del mismo contexto)
		mBroadcastMgr = LocalBroadcastManager
				.getInstance(getApplicationContext());
		
		//PASO 1: Register receiver
		mBroadcastMgr.registerReceiver(receiver, intentFilter);

		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				//Send Broadcast
				mBroadcastMgr.sendBroadcast(new Intent(CUSTOM_INTENT));
			}
		});
	}

	@Override
	protected void onDestroy() {
		mBroadcastMgr.unregisterReceiver(receiver);
		super.onDestroy();
	}
}
