package co.gramlich.battleship.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;

import java.util.Random;

import co.gramlich.battleship.BattleshipActivity;
import co.gramlich.battleship.skins.Skins;
import co.gramlich.battleship.shared.TickListener;

public abstract class Sprite implements TickListener {
	protected Bitmap image;
	protected RectF bounds;
	protected PointF velocity;
	protected Paint paint;
	private int width, height;
	public static int canvasWidth;	//dirty hack
	public static int canvasHeight;	//dirty hack
	static Skins skin;
	protected Random random = new Random();

	public Sprite() {
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		bounds = new RectF();
		velocity = new PointF();
	}
	
	public Sprite(Skins skin) {
		//super(GameView.skin);
		paint = new Paint();
		paint.setStyle(Style.STROKE);
		bounds = new RectF();
		velocity = new PointF();
		
	}

	public void setImage(int id, Canvas canvas) {
		image = BattleshipActivity.loadBitmap(id);
		width = image.getScaledWidth(canvas);
		height = image.getScaledHeight(canvas);
		bounds.left = 0;
		bounds.right = width;
		bounds.top = 0;
		bounds.bottom = height;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, bounds.left, bounds.top, paint);
	}

	public void move() {
		bounds.offset(velocity.x, velocity.y);
	}

	public boolean collidesWith(Sprite thatSprite) {
		return thatSprite != null && this.bounds.intersect(thatSprite.bounds);
	}
	

	public void setBottom(float b) {
		float dy = b - bounds.bottom;
		bounds.offset(0, dy);
	}

	public void setRight(float r) {
		float dx = r - bounds.right;
		bounds.offset(dx, 0);
	}

	public void setLeft(float lf) {
		bounds.offsetTo(lf, bounds.top);
	}

	public void setCenterX(float x) {
		setLeft(x - width/2);
	}
	
	public void setCenterY(float y) {
		setTop(y - height/2);
	}

	public void setTop(float t) {
		bounds.offsetTo(bounds.left, t);
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}

	@Override
	public void tick() {
		move();
	}


}
