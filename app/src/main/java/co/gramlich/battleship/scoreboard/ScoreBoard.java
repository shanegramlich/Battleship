package co.gramlich.battleship.scoreboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xmlpull.v1.XmlSerializer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreBoard extends Activity {


	private LinearLayout content;
	private List<HighScore> scores;
	private TableLayout scoreList;
	private HighScore myScore;
	private static final int MAX_ENTRIES = 5;
	public static final String FILENAME = "scores.xml";
	private TableRow buttons;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		//get the current score
		Bundle bundle = this.getIntent().getExtras();
		int score = bundle.getInt("score");
		TextView tv = new TextView(this);
		myScore = new HighScore(null, score);

		scores = new ArrayList<HighScore>();
		List<HighScore> tmp = loadScores();
		tmp.add(myScore);
		Collections.sort(tmp);
		for (int i=0; i<MAX_ENTRIES; ++i) {
			scores.add(tmp.get(i));
		}
		if (scores.contains(myScore)) {
			tv.setText("Congratulations! A new high score");
		} else {
			tv.setText("Sorry, no high score this time.");
		}

		scoreList = new TableLayout(this);
		for (HighScore hs : scores) {
			scoreList.addView(hs.createTableRow(this));
		}

		content = new LinearLayout(this);
		content.setOrientation(LinearLayout.VERTICAL);
		content.addView(tv);
		content.addView(scoreList);
		content.setPadding(10, 10, 10, 10);

		buttons = new TableRow(this);
		Button yesButton = new Button(this);
		Button noButton = new Button(this);
		yesButton.setText("Play again");
		noButton.setText("Quit");
		noButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_CANCELED);
				finish();
			}

		});
		yesButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}

		});
		buttons.addView(yesButton);
		buttons.addView(noButton);
		scoreList.addView(buttons);
		setContentView(content);

	}
	
	private FileOutputStream getOutputStream() throws FileNotFoundException {
		FileOutputStream fos;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File sdc = Environment.getExternalStorageDirectory();
			File dir = new File(sdc, "/android/data/" + getPackageName() + "/files/");
			dir.mkdirs();
			File f = new File(dir, FILENAME);
			fos = new FileOutputStream(f);
		} else {
			fos = openFileOutput(FILENAME, Activity.MODE_WORLD_READABLE);
		}
		return fos;
	}

	private FileInputStream getInputStream() throws FileNotFoundException {
		FileInputStream fos;
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File sdc = Environment.getExternalStorageDirectory();
			File dir = new File(sdc, "/android/data/" + getPackageName() + "/files/");
			dir.mkdirs();
			File f = new File(dir, FILENAME);
			fos = new FileInputStream(f);
		} else {
			fos = openFileInput(FILENAME);
		}
		return fos;
	}

	public void updateScores() {
		String name = myScore.getName();
		int score = myScore.getScore();

		scores.remove(myScore);
		myScore = new HighScore(name, score);
		scores.add(myScore);
		Collections.sort(scores);

		//open XML file
		//loop through MAX entries.
		//render and save.
		FileOutputStream fos = null;
		try {
			fos = getOutputStream();
			XmlSerializer serial = Xml.newSerializer();
			StringWriter sw = new StringWriter();
			serial.setOutput(sw);
			serial.startDocument("UTF-8", true);
			serial.startTag("", "scores");
			scoreList.removeAllViews();
			for (int i=0; i<MAX_ENTRIES; i++) {
				HighScore hs = scores.get(i);
				scoreList.addView(hs.createTableRow(this));
				hs.saveState(serial);
			}
			serial.endTag("", "scores");
			serial.endDocument();
			String document = sw.toString();
			fos.write(document.getBytes());
			fos.close();
			scoreList.addView(buttons);
		} catch (Exception e) {
			Log.d("CS203", e.getMessage());
		}

		scoreList.invalidate();
	}

	private List<HighScore> loadScores() {
		List<HighScore> result = new ArrayList<HighScore>();
		result.add(new HighScore("Duncanthrax", 0));
		result.add(new HighScore("Belwit", 0));
		result.add(new HighScore("Frobwit", 0));
		result.add(new HighScore("Timberthrax", 0));
		result.add(new HighScore("Phloid", 0));
		try {
			FileInputStream fis = getInputStream();
			XMLReader xr = XMLReaderFactory.createXMLReader();
			ScoreboardParser dp = new ScoreboardParser();
			xr.setContentHandler(dp);
			xr.setErrorHandler(dp);
			xr.parse(new InputSource(fis));
			result.addAll(dp.getScores());
			fis.close();
		} catch (Exception e) {
			Log.d("CS203", e.getMessage());
		}
		return result;
	}

}
