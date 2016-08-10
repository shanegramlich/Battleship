package co.gramlich.battleship.sprites;



import android.graphics.Canvas;
import android.graphics.PointF;

import co.gramlich.battleship.shared.GameView;

public class Battleship extends Sprite {
	
	private PointF leftGunPosition;
	private PointF rightGunPosition;
	private static Battleship instance;
	
	
	private Battleship( Canvas c) {
		setImage(GameView.skin.getBattleship(), c);
		
		
		
		float magic = 23f/183f;
		float y = Sprite.canvasHeight/2 - this.getHeight();
		float x = (Sprite.canvasWidth - this.getWidth())/2 + this.getWidth()*magic;
		leftGunPosition = new PointF(x,y);
		
		magic = 168f/183f;
		x = (Sprite.canvasWidth - this.getWidth())/2 + this.getWidth()*magic;
		rightGunPosition = new PointF(x,y);
	}
	
//	//Draper's Pseudo Code
//	public Battleship(LookAndFeel skin, Canvas c ){
//		setImage(skin.getBattleship(), c);
//	}
	
	public PointF getLeftGunPosition() {
		return leftGunPosition;
	}
	
	public PointF getRightGunPosition() {
		return rightGunPosition;
	}
	
	
	
	
	
	public static Battleship getInstance(Canvas c) {
		if (instance == null) {
			instance = new Battleship(c);
		}
		return instance;
	}
}
