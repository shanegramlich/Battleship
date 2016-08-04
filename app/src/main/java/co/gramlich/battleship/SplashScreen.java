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
	
	private AutoStartHandler autoStart;

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		autoStart = new AutoStartHandler();
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.splash);
		imageView.setScaleType(ScaleType.FIT_XY);
		setContentView(imageView);
	}

	@Override
	public boolean onTouchEvent(MotionEvent motionEvent) {
		startGame();
		return true;
	}

	private void startGame() {
		autoStart.removeMessages(0);
		Intent intent = new Intent(this, BattleshipActivity.class);
		startActivity(intent);
		finish();
	}

	private class AutoStartHandler extends Handler {
		public AutoStartHandler() {
			sendMessageDelayed(obtainMessage(0), 7000);
		}

		@Override
		public void handleMessage(Message message) {
			startGame();
		}

	}
}
