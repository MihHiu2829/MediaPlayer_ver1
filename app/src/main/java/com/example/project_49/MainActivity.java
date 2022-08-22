package com.example.project_49;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.animation.AnimationUtils;

import com.example.project_49.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
        private ActivityMainBinding binding ;
        private MediaPlayer player ;
    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        player =MediaPlayer.create(this, R.raw.sound) ;
        binding.ivPlaySound.setOnClickListener(v-> playMusic());

    }

    private void playMusic() {
        binding.ivPlaySound.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        if(player.isPlaying())
        {
                player.pause();
            binding.ivPlaySound.setImageLevel(0);
        }else{
            player.start();
            binding.ivPlaySound.setImageLevel(1);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(isPause){
            player.start();
            binding.ivPlaySound.setImageLevel(1);
            isPause = false   ;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(player.isPlaying())
        {
            player.pause();
            binding.ivPlaySound.setImageLevel(0);
            isPause = true ;
        }
    }
    @Override
    protected void onDestroy() {
        player.reset();
        super.onDestroy();
    }
}