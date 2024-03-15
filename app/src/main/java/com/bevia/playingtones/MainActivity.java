package com.bevia.playingtones;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button get_1sec_button, get_2sec_button, get_3sec_button;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        get_1sec_button = findViewById(R.id.get_1sec_button);
        get_2sec_button = findViewById(R.id.get_2sec_button);
        get_3sec_button = findViewById(R.id.get_3sec_button);

        setupButtons();

    }

    private void setupButtons() {
        get_1sec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTones(R.raw.tone1sec);
            }
        });

        get_2sec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTones(R.raw.tone2sec);
            }
        });

        get_3sec_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTones(R.raw.tone3sec);
            }
        });
    }

    private void playTones(int toneResId) {
        releaseMediaPlayer(); // Release any previously used MediaPlayer instance
        mediaPlayer = MediaPlayer.create(this, toneResId);

        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releaseMediaPlayer(); // Release MediaPlayer resources when playback completes
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // Handle errors that may occur during playback
                    releaseMediaPlayer(); // Release MediaPlayer resources in case of error
                    return false;
                }
            });

            mediaPlayer.start(); // Start playback
        } else {
            // Handle the case where MediaPlayer creation fails
            Toast.makeText(this, "Failed to create MediaPlayer", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer(); // Release MediaPlayer resources when the activity is destroyed
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}