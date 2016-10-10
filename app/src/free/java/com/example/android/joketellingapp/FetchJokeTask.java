package com.example.android.joketellingapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lavanya.myapplication.backend.myApi.MyApi;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

/**
 * Created by lavanya on 10/8/16.
 */
public class FetchJokeTask extends AsyncTask<Void, Void, String> {
	private static final String TAG = FetchJokeTask.class.getSimpleName();
	private static MyApi myApiService = null;
	private InterstitialAd mInterstitialAd;
	ProgressBar mProgressBar;
	private Context context;

	public FetchJokeTask(Context context) {
		this.context = context;
	}

	public FetchJokeTask(Context context, ProgressBar progressBar) {
		this.context = context;
		mProgressBar = progressBar;

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		if (mProgressBar != null) {
			mProgressBar.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected String doInBackground(Void... voids) {
		if (myApiService == null) {  // Only do this once
			MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
					new AndroidJsonFactory(), null)
					//	.setRootUrl("http://10.0.2.2:8080/_ah/api/")
					.setRootUrl("https://joketellingapp-145906.appspot.com/_ah/api/")
					.setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
						@Override
						public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
							abstractGoogleClientRequest.setDisableGZipContent(true);
						}
					});

			myApiService = builder.build();
		}

		try {
			return myApiService.telljoke().execute().getData();
		} catch (IOException e) {
			return e.getMessage();
		}
	}


	@Override
	protected void onPostExecute(final String result) {
		Log.d(TAG, "the result is" + result);
		//Toast.makeText(context, result, Toast.LENGTH_LONG).show();
		mInterstitialAd = new InterstitialAd(context);
		mInterstitialAd.setAdUnitId(context.getString(R.string.interstitial_unit_id));
		mInterstitialAd.setAdListener(new AdListener() {

			@Override
			public void onAdLoaded() {
				super.onAdLoaded();
				if (mProgressBar != null) {
					mProgressBar.setVisibility(View.GONE);
				}
				mInterstitialAd.show();
			}

			@Override
			public void onAdFailedToLoad(int errorCode) {
				super.onAdFailedToLoad(errorCode);
				if (mProgressBar != null) {
					mProgressBar.setVisibility(View.GONE);
				}
				startjokeActivity(result);
			}

			@Override
			public void onAdClosed() {
				startjokeActivity(result);
			}
		});
		loadInterstitialAd();

	}

	private void startjokeActivity(String result) {
		Intent intent = new Intent(context, com.example.android.jokedisplaylib.JokeDisplayActivity.class);
		intent.putExtra("jokes", result);
		context.startActivity(intent);
	}

	private void loadInterstitialAd() {
		AdRequest adRequest = new AdRequest.Builder()
				//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("4867513FE210DD1D74E0FF3EA301E4C0")
				.build();
		mInterstitialAd.loadAd(adRequest);

	}
}
