package com.gradiuss.game.highscore;



import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.gradiuss.game.R;

public class SQLViewActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		Highscore info = new Highscore(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
		
	}

}
