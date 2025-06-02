package com.project.homework;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private List<ColorInfo> colorList;
    private GridLayout colorGrid;

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorGrid = findViewById(R.id.colorGrid);
        initializeColorData(); // This will now have fewer colors
        createColorButtons();
    }

    private void initializeColorData() {
        colorList = new ArrayList<>();
        colorList.add(new ColorInfo("Red", R.color.app_red, R.raw.rouge, R.color.white));
        colorList.add(new ColorInfo("Blue", R.color.app_blue, R.raw.bleu, R.color.white));
        colorList.add(new ColorInfo("Green", R.color.app_green, R.raw.vert, R.color.white));
        colorList.add(new ColorInfo("Yellow", R.color.app_yellow, R.raw.jaune, R.color.black)); // Yellow needs black text
        colorList.add(new ColorInfo("Black", R.color.app_noir, R.raw.noir, R.color.white));
        colorList.add(new ColorInfo("White", R.color.app_blanc, R.raw.blanc, R.color.black)); // White needs black text
        colorList.add(new ColorInfo("Orange", R.color.app_orange, R.raw.orange, R.color.white));
        colorList.add(new ColorInfo("Purple", R.color.app_violet, R.raw.violet, R.color.white));
    }

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    private void createColorButtons() {
        int buttonSizeInDp = 100;
        int marginInDp = 8;

        // Convert dp to pixels
        final float scale = getResources().getDisplayMetrics().density;
        int buttonSizeInPx = (int) (buttonSizeInDp * scale + 0.5f);
        int marginInPx = (int) (marginInDp * scale + 0.5f);


        for (final ColorInfo colorInfo : colorList) {
            Button colorButton = new Button(this);
            colorButton.setText(colorInfo.getEnglishName());
            colorButton.setBackgroundColor(ContextCompat.getColor(this, colorInfo.getColorResource()));
            colorButton.setTextColor(ContextCompat.getColor(this, colorInfo.getTextColorResource()));

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = buttonSizeInPx;
            params.height = buttonSizeInPx;
            params.setMargins(marginInPx, marginInPx, marginInPx, marginInPx);
            colorButton.setLayoutParams(params);

            colorButton.setOnClickListener(v -> playSound(colorInfo.getAudioResource()));
            colorGrid.addView(colorButton);
        }
    }

    private void playSound(int audioResourceId) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = MediaPlayer.create(this, audioResourceId);
        if (mediaPlayer != null) {
            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                mediaPlayer = null;
            });
            mediaPlayer.setOnErrorListener((mp, what, extra) -> {
                mp.release();
                mediaPlayer = null;
                return true;
            });
            mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}