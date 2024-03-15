package com.bevia.playingtones;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.Toast;

public class MediaPlayerManager {
    private MediaPlayer mediaPlayer;

    public void play(int toneResId, Context context) {
        releaseMediaPlayer(); // Release any previously used MediaPlayer instance
        mediaPlayer = MediaPlayer.create(context, toneResId);

        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(mp -> releaseMediaPlayer());
            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                releaseMediaPlayer();
                return false;
            });
            mediaPlayer.start(); // Start playback
        } else {
            Toast.makeText(context, "Failed to create MediaPlayer", Toast.LENGTH_SHORT).show();
        }
    }

    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
