package co.gramlich.battleship.sprites;



import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;

import co.gramlich.battleship.shared.GameView;

public class Battleship extends Sprite {
	private PointF leftGunPosition;
	private PointF rightGunPosition;
	private static Battleship battleship;

	private Battleship( Canvas canvas) {
		setImage(GameView.skin.getBattleship(), canvas);
		leftGunPosition = GameView.skin.getLeftGunPosition();
		rightGunPosition = GameView.skin.getRightGunPosition();
//		Log.d("BattleshipLog", "Shane Y:" + leftGunPosition.y + " X:" + leftGunPosition.x);
//		Log.d("BattleshipLog", "Shane Y:" + rightGunPosition.y + " X:" + rightGunPosition.x);

//		float magic = 23f/183f;
//		float y = Sprite.canvasHeight/2 - this.getHeight();
//		float x = (Sprite.canvasWidth - this.getWidth())/2 + this.getWidth()*magic;
//		leftGunPosition = new PointF(x,y);
//		magic = 168f/183f;
//		x = (Sprite.canvasWidth - this.getWidth())/2 + this.getWidth()*magic;
//		rightGunPosition = new PointF(x,y);
	}
	
	public PointF getLeftGunPosition() {
		return leftGunPosition;
	}
	
	public PointF getRightGunPosition() {
		return rightGunPosition;
	}

	public static Battleship getBattleship(Canvas canvas) {
		if (battleship == null) {
			battleship = new Battleship(canvas);
		}
		return battleship;
	}
}
