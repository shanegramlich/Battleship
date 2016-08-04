package co.gramlich.battleship;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class BattleshipActivity extends Activity {
	private static Resources resources;
	private GameView gameView;
	public static final int HIGH_SCORE_DIALOG = 1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		resources = getResources();
		gameView = new GameView(this);
		setContentView(gameView);
		initializeSound();
	}

	private void initializeSound() {
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d("BattleshipLog", "inside onpause");
		gameView.goToBackground(true);
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d("BattleshipLog", "inside onstop");
		gameView.stop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		//onResume();
		Log.d("BattleshipLog", "inside onrestart");
		gameView.resume();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d("BattleshipLog", "inside onresume");
		gameView.goToBackground(false);
		//gameView.resume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("BattleshipLog", "inside ondestroy");
		//gameView.stop();
	}


	@Override
	protected void onActivityResult(int requestCode,int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == HIGH_SCORE_DIALOG) {
			if (resultCode == RESULT_OK) {
				gameView.restart();
			} else {
				finish();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
			case R.id.about:
				//startActivity(new Intent(this, About.class));
				DialogInterface.OnClickListener aboutClickListener = new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						gameView.goToBackground(false);
					}
				};
				AlertDialog.Builder aboutBox = new AlertDialog.Builder(this);
				gameView.goToBackground(true);
				aboutBox.setOnCancelListener(new DismissHandler());
				aboutBox.setMessage(getResources().getString(R.string.about_message)).setTitle(R.string.app_name).setPositiveButton(getResources().getString(R.string.yes), aboutClickListener)
						.show();
				return true;
			case R.id.options:
				startActivity(new Intent(this, SettingsActivity.class));
				return true;
			case R.id.quit:
				showQuitDialog();
				return true;
			case R.id.pause:
				gameView.pauseButtonClicked();
				return true;
		/*case R.id.sound:
			gameView.toggleSound();
			return true;*/
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onBackPressed() {
		showQuitDialog();
	}

	private void showQuitDialog() {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        if (which == DialogInterface.BUTTON_POSITIVE) {
		            finish();
		        } else {
		        		gameView.goToBackground(false);
		        }
		    }
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		gameView.goToBackground(true);
		builder.setOnCancelListener(new DismissHandler());
		builder.setMessage(getResources().getString(R.string.are_you_sure)).setPositiveButton(getResources().getString(R.string.yes), dialogClickListener)
		    .setNegativeButton(getResources().getString(R.string.no), dialogClickListener).show();

	}

	public static Bitmap loadBitmap(int id) {
		Drawable drawable = resources.getDrawable(id);
		BitmapDrawable bitmapDrawable = (BitmapDrawable)drawable;
		return bitmapDrawable.getBitmap();
	}

	public class DismissHandler implements DialogInterface.OnCancelListener {
		@Override
		public void onCancel(DialogInterface dialog) {
			Log.d("BattleshipLog", "dialog canceled!");
			gameView.goToBackground(false);
		}
	}
}