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
    private MediaPlayerManager mediaPlayerManager;

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
        mediaPlayerManager = new MediaPlayerManager();

    }

    private void setupButtons() {
        get_1sec_button.setOnClickListener(new ButtonClickListener(this, R.raw.tone1sec));
        get_2sec_button.setOnClickListener(new ButtonClickListener(this, R.raw.tone2sec));
        get_3sec_button.setOnClickListener(new ButtonClickListener(this, R.raw.tone3sec));
    }

    public void playTones(int toneResId) {
        mediaPlayerManager.play(toneResId, this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayerManager.releaseMediaPlayer();
    }
}