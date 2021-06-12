package com.example.ass_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

public class Video3 extends AppCompatActivity {

    String vedioURL3 = "https://www.youtube.com/watch?v=KpNqGctH-p0";

    PlayerView playerView3;
    SimpleExoPlayer player;
    private Button button3;

    private  boolean PlayWhenReady = true;
    private  int CurrentWindow = 0;
    private  long PlayBackPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video3);

        playerView3 = findViewById(R.id.video_view3);
        button3 = findViewById(R.id.vedio3);
    }

    public void initVedio(){
        player = ExoPlayerFactory.newSimpleInstance(this);
        playerView3.setPlayer(player);

        Uri uri = Uri.parse(vedioURL3);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this , "Ass-4");
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);

        player.setPlayWhenReady(PlayWhenReady);
        player.seekTo(CurrentWindow,PlayBackPosition);
        player.prepare(mediaSource,false,false);
    }

    public void releaseVedio(){
        if (player != null){
            PlayWhenReady =  player.getPlayWhenReady();
            PlayBackPosition = player.getContentPosition();
            CurrentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initVedio();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            initVedio();
        }
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideo3();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseVedio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseVedio();
    }



    public void openVideo3() {
        Intent intent = new Intent(this, Video3.class);
        startActivity(intent);
    }
}