package co.gramlich.battleship.scoreboard;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;


public class ScoreboardParser extends DefaultHandler {
	private List<HighScore> archive;
	
	@Override
	public void startDocument() {
		Log.d("CS203", "getting ready to parse some scores!");
		archive = new ArrayList<HighScore>();
	}
	
	@Override
	public void startElement(String junk1, String junk2,
			String tagName, Attributes attr) {
		Log.d("CS203", "inside startelement; tag=" + tagName);
		if (tagName.equals("highscore")) {
			String name = attr.getValue("name");
			int score = Integer.parseInt(attr.getValue("score"));
			Log.d("CS203", "reading " + name + " and " + score);
			HighScore d = new HighScore(name, score);
			archive.add(d);
		}
	}
	
	public List<HighScore> getScores() {
		return archive;
	}

}
