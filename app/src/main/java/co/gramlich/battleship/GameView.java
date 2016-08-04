package co.gramlich.battleship;

import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;


import co.gramlich.battleship.scoreboard.ScoreBoard;
import co.gramlich.battleship.skins.LookAndFeel;
import co.gramlich.battleship.skins.RetroLNF;
import co.gramlich.battleship.skins.SteroidsLNF;
import co.gramlich.battleship.sprites.FakeQueue;
import co.gramlich.battleship.sprites.Sprite;
import co.gramlich.battleship.sprites.Sprite.Direction;
import co.gramlich.battleship.sprites.Airplane;
import co.gramlich.battleship.sprites.Battleship;
import co.gramlich.battleship.sprites.Bullet;
import co.gramlich.battleship.sprites.DepthCharge;
import co.gramlich.battleship.sprites.Enemy;
import co.gramlich.battleship.sprites.Gunsmoke;
import co.gramlich.battleship.sprites.Submarine;


public class GameView extends View {
	private Timer timer;
	private boolean paused;
	private boolean backgrounded;
	private List<Enemy> planes;
	private List<Enemy> subs;
	private Paint paint;
	private Bitmap water;
	boolean initialized = false;
	private Battleship battleship;
	private long timeLeft;
	private int score;
	private Canvas canvas;
	//private Bullet leftBullet, rightBullet;
	private FakeQueue<Bullet> bullets;
	//private DepthCharge bomb;
	private FakeQueue<DepthCharge> bombs;
	private Gunsmoke leftGunsmoke, rightGunsmoke;
	private boolean showLeftGunsmoke, showRightGunsmoke;
	private SoundFX fx;
	private BattleshipActivity battleshipActivity;
	float timerTextWidth;
	boolean gameOver;
	public static LookAndFeel skin;
	private RetroLNF retro;
	private SteroidsLNF steroids;
	private SettingsActivity o;

	
	public GameView(Context context) {
		super(context);
		battleshipActivity = (BattleshipActivity)context;
		fx = new SoundFX(context);
		subs = new LinkedList<Enemy>();
		planes = new LinkedList<Enemy>();
		bullets = new FakeQueue<Bullet>();
		bombs = new FakeQueue<DepthCharge>();
		paint = new Paint();
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		initialized = false;
		gameOver = false;
		water = BattleshipActivity.loadBitmap(R.drawable.water);
		score = 0;
		timeLeft = SettingsActivity.getGameLength(getContext());
		paused = backgrounded = false;
		showLeftGunsmoke = showRightGunsmoke = false;
		o = new SettingsActivity();
		switch(o.getSkin(context)){
		case 1:
			skin = new RetroLNF();
			break;
		case 2:
			skin = new SteroidsLNF();
			break;
		}
		//Battleship battleship = new Battleship();
		
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (!initialized) {
			initialized = true;
			this.canvas = canvas;
			Sprite.canvasWidth = canvas.getWidth();
			Sprite.canvasHeight = canvas.getHeight();
			if (Math.min(Sprite.canvasHeight, Sprite.canvasWidth) < 400) {
				paint.setTextSize(20);
			} else {
				paint.setTextSize(40);
			}
			timerTextWidth = paint.measureText(getResources().getString(R.string.time)+": 0:00");
			battleship = Battleship.getInstance(canvas);
			battleship.setBottom(canvas.getHeight()/2);
			battleship.setCenterX(canvas.getWidth()/2);

			leftGunsmoke = new Gunsmoke(canvas);
			leftGunsmoke.setRight(battleship.getLeftGunPosition().x);
			leftGunsmoke.setBottom(1);

			rightGunsmoke = new Gunsmoke(canvas);
			rightGunsmoke.setBottom(battleship.getRightGunPosition().y);
			rightGunsmoke.setLeft(battleship.getRightGunPosition().x);

			for (int i = 0; i< SettingsActivity.getNumPlanes(getContext()); ++i) {
				planes.add(new Airplane(canvas));
			}
			for (int i = 0; i< SettingsActivity.getNumSubs(getContext()); ++i) {
				subs.add(new Submarine(canvas));
			}
			timer = new Timer();
			timer.subscribe(planes);
			timer.subscribe(subs);
			//timer.subscribe(this);
		}
		if (gameOver || paused) {
			canvas.drawColor(Color.YELLOW);
		} else {
			canvas.drawColor(Color.WHITE);
		}
		drawWater(canvas);
		battleship.draw(canvas);
		for (Enemy e : planes) {
			e.draw(canvas);
		}
		for (Enemy e : subs) {
			e.draw(canvas);
		}
		for (Bullet b : bullets) {
			b.draw(canvas);
		}
		if (showLeftGunsmoke) {
			leftGunsmoke.draw(canvas);
			showLeftGunsmoke = false;
		}
		if (showRightGunsmoke) {
			rightGunsmoke.draw(canvas);
			showRightGunsmoke = false;
		}
		for (DepthCharge b : bombs) {
			b.draw(canvas);
		}

		paint.setColor(skin.getTextColor());
		
		String scoreText = getResources().getString(R.string.score)+": " + score;
		canvas.drawText(scoreText, 5, canvas.getHeight()/2 - paint.ascent(), paint);

		String timerText = String.format("Time Left"+": %d:%02d", timeLeft/60, timeLeft%60);
		canvas.drawText(timerText, canvas.getWidth()-timerTextWidth-5, canvas.getHeight()/2 - paint.ascent(), paint);

		if (paused) {
			String paused1 = getResources().getString(R.string.pause_option);
			String paused2 = getResources().getString(R.string.continue_playing);
			float pausedWidth = paint.measureText(paused1);
			canvas.drawText(paused1, (getWidth()-pausedWidth)/2, getHeight()*0.25f, paint);
			pausedWidth = paint.measureText(paused2);
			canvas.drawText(paused2, (getWidth()-pausedWidth)/2, getHeight()*0.25f - paint.ascent(), paint);
		}
		//debugging
		//paint.setTextSize(10);
		//c.drawText(""+bullets.size(), 5, 10, paint);
	}

	private void drawWater(Canvas c) {
		float waterline = c.getHeight()/2 - water.getScaledHeight(c);
		float ww = water.getScaledWidth(c);
		for (int x=0; x<c.getWidth(); x+=ww) {
			c.drawBitmap(water, x, waterline, paint);
		}
	}
	
	public void restart() {
		gameOver = false;
		score = 0;
		timeLeft = SettingsActivity.getGameLength(getContext());
		paused = false;
		backgrounded = false;
		showLeftGunsmoke = showRightGunsmoke = false;
		timer.unsubscribe(bullets);
		bullets.clear();
		timer.unsubscribe(bombs);
		bombs.clear();
		timer.restart();
	}

	public boolean isPaused() {
		return paused;
	}

	public void pauseButtonClicked() {
		paused = !paused;
		invalidate();
	}

	public void goToBackground(boolean bg) {
		backgrounded = bg;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!paused) {
			float x=0,y=0;
			if (event.getAction() == MotionEvent.ACTION_DOWN
					||
					(event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN) {	
				int pointerCount = event.getPointerCount();
				for (int p = 0; p < pointerCount; p++) {
					x = event.getX(p);
					y = event.getY(p);
					if (y < getHeight()/2) {
						if (x < getWidth() / 2) {
							fireLeftGun();
						} else {
							fireRightGun();
						}
					} else {
						dropDepthCharge();
					}
				}
			}
		} else {
			paused = false;
			invalidate();
		}
		return true;
	}

	private void fireRightGun() {
		//		float magic = 168f/183f;
		//		float y = Sprite.canvasHeight/2 - battleship.getHeight();
		//		float x = (Sprite.canvasWidth - battleship.getWidth())/2 + battleship.getWidth()*magic;
		if (newBullet(battleship.getRightGunPosition(), Direction.LEFT_TO_RIGHT)) {
			//			gunsmoke.setBottom(y);
			//			gunsmoke.setLeft(x);
			showRightGunsmoke = true;
			fx.rightGun();
		}
	}

	private void fireLeftGun() {
		//		float magic = 23f/183f;
		//		float y = Sprite.canvasHeight/2 - battleship.getHeight();
		//		float x = (Sprite.canvasWidth - battleship.getWidth())/2 + battleship.getWidth()*magic;
		if (newBullet(battleship.getLeftGunPosition(), Direction.RIGHT_TO_LEFT)) {
			//			gunsmoke.setBottom(y);
			//			gunsmoke.setRight(x);
			showLeftGunsmoke = true;
			fx.leftGun();
		}
	}

	private boolean newBullet(PointF p, Direction d) {
		if (!(SettingsActivity.getRapidGuns(getContext()))) {
			//			Bullet existing = bullets.peekLast();
			//			if (existing != null 
			//					&& existing.direction() == d
			//					&& existing.isVisible()) {
			//				return false;
			//			}
			List<Bullet> existing = bullets.peekLast2();
			if (existing != null) {
				Bullet b1 = existing.get(0);
				Bullet b2 = existing.get(1);
				if ((b1.direction() == d && b1.isVisible())
						|| (b2.direction() == d && b2.isVisible())) {
					return false;
				}
			}
		}
		Bullet b = new Bullet(new PointF(p.x,p.y), d);
		timer.subscribe(b);
		Bullet doomed = bullets.add(b);
		if (doomed != null) {
			timer.unsubscribe(doomed);
		}
		return true;
	}

	private void dropDepthCharge() {
		//if rapid-fire is off, AND if there's already
		//a depth-charge sinking, then don't do anything
		if (!(SettingsActivity.getRapidDC(getContext()))) {
			DepthCharge existing = bombs.peekLast();
			if (existing != null) {
				if (existing.isSinking()) {
					return;
				}
			}
		}
		//Otherwise, launch a new depth-charge
		DepthCharge b = new DepthCharge(canvas);
		timer.subscribe(b);
		DepthCharge doomed = bombs.add(b);
		if (doomed != null) {
			timer.unsubscribe(doomed);
		}

	}

	private void checkForCollisions() {
		Bullet used = null;
		DepthCharge usedc = null;
		for (Enemy e : planes) {
			for (Bullet b : bullets) {
				if (e.collidesWith(b)) {
					used = b;
					//if (e.collidesWith(leftBullet) || e.collidesWith(rightBullet)) {
					score += e.getPointValue();
					e.explode();
					fx.planeExplode();
					break;//nice try
				}
			}
			bullets.remove(used);
		}
		for (Enemy e : subs) {
			for (DepthCharge bomb : bombs) {
				if (e.collidesWith(bomb)) {
					usedc = bomb;
					score += e.getPointValue();
					e.explode();
					fx.subExplode();
					//bomb = null;
					break;
				}
			}
			bombs.remove(usedc);
		}

	}

	public void stop() {
		timer.removeMessages(0);
	}

	public void resume() {
		timer.restart();
	}

//	@Override
//	public void tick() {
//		if (gameOver) {
//			Bundle bundle = new Bundle();
//			bundle.putInt("score", score);
//			Intent newIntent = new Intent(GameView.this.getContext(), ScoreBoard.class);
//			newIntent.putExtras(bundle);
//			timer.removeMessages(0);
//			battleshipActivity.startActivityForResult(newIntent, BattleshipActivity.HIGH_SCORE_DIALOG);
//			return;
//		}
//		if (!(paused || backgrounded)) {
//			timer.timeNow = System.currentTimeMillis();
//			if (timer.timeNow - timer.timeBefore >= 1000) {
//				--timeLeft;
//				timer.timeBefore = timer.timeNow;
//			}
//			if (timeLeft < 1) {
//				gameOver = true;
//				backgrounded = true;
//				invalidate();
//			}
//			checkForCollisions();
//			invalidate();
//		}
//	}

	//	private void showToast(String msg) {
	//		Toast toast = Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT);
	//		toast.setGravity(Gravity.CENTER, 0, 0);
	//		toast.show();
	//	}
	
	
	private class Timer extends Handler {

		long timeNow, timeBefore;
		private List<TickListener> listeners;

		public Timer() {
			super();
			listeners = new LinkedList<TickListener>();
			restart();
		}

		public void restart() {
			timeBefore = System.currentTimeMillis();
			handleMessage(obtainMessage(0));
		}

		public void subscribe(TickListener tl) {
			listeners.add(tl);
		}

		public void subscribe(List<? extends TickListener> tls) {
			listeners.addAll(tls);
		}

		public void unsubscribe(TickListener tl) {
			listeners.remove(tl);
		}

		public void unsubscribe(Iterable<? extends TickListener> tls) {
			for (TickListener tl : tls) {
				listeners.remove(tl);
			}
		}

		@Override
		public void handleMessage(Message m) {
			if (gameOver) {
				Bundle bundle = new Bundle();
				bundle.putInt("score", score);
				Intent newIntent = new Intent(GameView.this.getContext(), ScoreBoard.class);
				newIntent.putExtras(bundle);
				timer.removeMessages(0);
				battleshipActivity.startActivityForResult(newIntent, BattleshipActivity.HIGH_SCORE_DIALOG);
				return;
			}
			if (!(paused || backgrounded)) {
				timeNow = System.currentTimeMillis();
				if (timeNow - timeBefore >= 1000) {
					--timeLeft;
					timeBefore = timer.timeNow;
				}
				if (timeLeft < 1) {
					gameOver = true;
					backgrounded = true;
					invalidate();
				}
				checkForCollisions();
				invalidate();
			}

			for (TickListener tl : listeners) {
				tl.tick();
			}
			removeMessages(0);
			sendMessageDelayed(obtainMessage(0), 50);
		}

	}

}

