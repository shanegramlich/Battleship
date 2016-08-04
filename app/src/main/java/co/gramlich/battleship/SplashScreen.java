package co.gramlich.battleship;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class SplashScreen extends Activity {
	
	private TwoSecondTimer timer;

	private class TwoSecondTimer extends Handler {
		public TwoSecondTimer() {
			sendMessageDelayed(obtainMessage(0), 7000);
		}
		
		@Override
		public void handleMessage(Message m) {
			startGame();
		}
		
	}
	@Override
	public void onCreate(Bundle b) {
		super.onCreate(b);
		timer = new TwoSecondTimer();
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.splash);
		iv.setScaleType(ScaleType.FIT_XY);
		setContentView(iv);
	}
	
	private void startGame() {
		timer.removeMessages(0);
		Intent i = new Intent(this, Main.class);
		startActivity(i);
		finish();		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent m) {
		startGame();
		return true;
	}
}
