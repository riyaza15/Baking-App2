package com.example.riyaza.bankingapp2.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riyaza.bankingapp2.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;

import com.example.riyaza.bankingapp2.model.Steps;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FragmentVideo extends Fragment {
Steps steps;
    private SimpleExoPlayerView simpleExoPlayerView;
    private SimpleExoPlayer simpleExoPlayer;
    TextView description;
    ImageView noImage;
    private static Bundle mAppState;
    long playbackPosition;
    boolean playWhenReady;


    public FragmentVideo() {

    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_video, container, false);

         description = rootView.findViewById(R.id.detailed_textview);
         simpleExoPlayerView = rootView.findViewById(R.id.video_view);
         noImage= rootView.findViewById(R.id.imageView2);

        steps = getArguments().getParcelable("Steps");
       description.setText(steps.getStepDescription());
        String videoURL = steps.getVideoUrl();
//
        if (savedInstanceState != null) {
            playbackPosition = savedInstanceState.getLong("PlaybackPosition");
            playWhenReady= savedInstanceState.getBoolean("PlaywhenReady");
        }

        if (videoURL.equals("") ) {
            simpleExoPlayerView.setVisibility(View.INVISIBLE);
            noImage.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(R.drawable.novideo)
                    .into(noImage);
        } else {
            simpleExoPlayerView.setVisibility(View.VISIBLE);
            noImage.setVisibility(View.INVISIBLE);
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getActivity(), trackSelector);

            Uri videoURI = Uri.parse(videoURL);

            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoURI, dataSourceFactory, extractorsFactory, null, null);

            simpleExoPlayerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource, true, false);
            simpleExoPlayer.setPlayWhenReady(playWhenReady);
            simpleExoPlayer.seekTo(playbackPosition);

        }
        return rootView;

    }


    @Override
    public void onPause() {
        super.onPause();
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
        }

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        playbackPosition = simpleExoPlayer.getContentPosition();
        playWhenReady = simpleExoPlayer.getPlayWhenReady();
        outState.putLong("PlaybackPosition",playbackPosition);
        outState.putBoolean("PlaywhenReady", playWhenReady);

    }
}