package co.gramlich.battleship.skins;

import android.graphics.Canvas;
import android.graphics.PointF;

public interface Skins {
	
	//returns the color in which the SCORE and TIME
	//text should be rendered.
	int getTextColor();
	
	//Here's how these three methods are intended to work.
	//The first, drawBackground, is called first. It should
	//set the background color and anything else you want
	//to appear BEHIND the battleship. Then your
	//GameView should draw the battleship. Then it should
	//call drawForeground, which draws anything that should
	//go in FRONT of the battleship but BEHIND the enemies.
	//Then GameView should draw the enemies, missiles, etc.
	//Finally, it should call drawOverlay in case you want
	//to render anything that goes in FRONT of everything
	//else. In my default "retro" implementation, I only
	//implemented drawBackground (which sets the background
	//color to white, then draws the water).
	//I left drawForeground and drawOverlay "stubbed-out."
	void drawBackground(Canvas c);
	void drawForeground(Canvas c);
	void drawOverlay(Canvas c);
	
	//These methods simply return the integer code
	//(from R.java) for their respective bitmaps.
	int getBattleship();
	int getLargeAirplaneLeft();
	int getMediumAirplaneLeft();
	int getSmallAirplaneLeft();
	int getLargeAirplaneRight();
	int getMediumAirplaneRight();
	int getSmallAirplaneRight();
	int getLargeSubmarineLeft();
	int getMediumSubmarineLeft();
	int getSmallSubmarineLeft();
	int getLargeSubmarineRight();
	int getMediumSubmarineRight();
	int getSmallSubmarineRight();
	int getGunsmoke();
	int getAirplaneExplosion();
	int getSubmarineExplosion();
	int getDepthCharge();
	int getWater();

	//These two methods return the position, in relative
	//coordinates between 0..1, of where the mouths of
	//the cannons are, relative to the battleship image.
	//For the X coordinate, 0 is the far left side of the
	//image and 1 is the far right side. For the Y
	//coordinate, 0 is the bottom of the image, and 1 is
	//the top of the image.
	//For example, in my default "retro" implementation,
	//getLeftGunPosition() returns (0.125683f, 1f) and
	//getRightGunPosition() returns (0.918033f, 1f).
	PointF getLeftGunPosition();
	PointF getRightGunPosition();

}
