package com.example.android.joketellingapp;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.concurrent.ExecutionException;

/**
 * Created by lavanya on 10/9/16.
 */
public class FetchJokeTestCase extends ApplicationTestCase<Application> {
	private static final String TAG = FetchJokeTask.class.getSimpleName();

	public FetchJokeTestCase(Class<Application> applicationClass) {
		super(applicationClass);
	}

	public void testEmptyString() {
		FetchJokeTask fetchJokeTask = new FetchJokeTask(getContext());
		fetchJokeTask.execute();
		try {
			String joke = fetchJokeTask.get();
			Log.d(TAG, "the joke is" + joke);
			assertNotNull(joke);
			assertTrue(joke.length() > 0);
		} catch (InterruptedException | ExecutionException e) {
			//e.printStackTrace();
			Log.e(TAG, Log.getStackTraceString(e));
		}
	}
}
