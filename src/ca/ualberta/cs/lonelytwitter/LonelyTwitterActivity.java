//need to use Serialization library GSON

package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;



public class LonelyTwitterActivity extends Activity {
	// static means all objects share this same value. Final means that it cannot be changed
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	
	//added
	private ArrayAdapter<String> adapter;
	private ArrayList<String> tweets;
	//----
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				//saveInFile(text, new Date(System.currentTimeMillis()));
				//removed to stop it from closing when hitting save:  finish();
				//added
				tweets.add(text);
				
				saveInFile(text, new Date(System.currentTimeMillis()));
				
				adapter.notifyDataSetChanged(); //tells it that we changed the dataset

			}
		});
	}

	@Override
	protected void onStart() {

		//ArrayList<User> array = new ArrayList<User>();

		
		
		super.onStart();
		tweets = loadFromFile();
		adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private ArrayList<String> loadFromFile() {
		Gson gson = new Gson();
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			/*
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}
			*/
			FileInputStream fis = openFileInput(FILENAME);
			//BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			InputStreamReader in = new InputStreamReader(fis);
			// Taken from http://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/index.html Jan 2015
			Type typeOfT = new TypeToken<ArrayList<String>>(){}.getType();
			tweets = gson.fromJson(in, typeOfT);
			fis.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets;
	}
	
	private void saveInFile(String text, Date date) {
		Gson gson = new Gson();
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); //open file output
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			gson.toJson(tweets, osw);
			osw.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}