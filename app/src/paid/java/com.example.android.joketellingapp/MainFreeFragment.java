package com.example.android.joketellingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

/**
 * Created by lavanya on 10/10/16.
 */
public class MainFreeFragment extends Fragment {

	private static final String TAG = MainFreeFragment.class.getSimpleName();
	private ProgressBar mProgressBar;

	//PublisherInterstitialAd mPublisherInterstitialAd = null;
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View root = inflater.inflate(R.layout.free_fragment, container, false);
		Button button = (Button) root.findViewById(R.id.jokebutton);
		mProgressBar = (ProgressBar) root.findViewById(R.id.progressbar);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				fetchJoke();
			}
		});

		return root;
	}


	public void fetchJoke() {
		new FetchJokeTask(getContext(), mProgressBar).execute();
	}

}
