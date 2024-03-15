package com.bevia.playingtones;

import android.view.View;

public class ButtonClickListener implements View.OnClickListener {
    private final MainActivity mainActivity;
    private final int toneResId;

    public ButtonClickListener(MainActivity mainActivity, int toneResId) {
        this.mainActivity = mainActivity;
        this.toneResId = toneResId;
    }

    @Override
    public void onClick(View v) {
        mainActivity.playTones(toneResId);
    }
}

