package com.example.android.joketellingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


/**
 * Created by lavanya on 10/9/16.
 */
public class MainFreeFragment extends Fragment {
	private static final String TAG = MainFreeFragment.class.getSimpleName();
	private ProgressBar mProgressBar;
	
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.free_fragment, container, false);
		Button button = (Button) root.findViewById(R.id.jokebutton);
		mProgressBar = (ProgressBar) root.findViewById(R.id.progressbar);
		final AdView adView = (AdView) root.findViewById(R.id.adView);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fetchJoke();
			}
		});

		AdRequest adRequest = new AdRequest.Builder()
				//.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice("4867513FE210DD1D74E0FF3EA301E4C0")
				.build();
		adView.loadAd(adRequest);
		return root;
	}


	public void fetchJoke() {
		new FetchJokeTask(getContext(), mProgressBar).execute();
	}

}
