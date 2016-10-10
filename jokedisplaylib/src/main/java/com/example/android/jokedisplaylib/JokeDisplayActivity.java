package com.example.android.jokedisplaylib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_joke_display);
		textView = (TextView) findViewById(R.id.joketext);
		textView.setText(getIntent().getStringExtra(getString(R.string.jokestr)));
	}
}
