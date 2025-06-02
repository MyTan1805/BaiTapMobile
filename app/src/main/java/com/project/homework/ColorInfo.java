package com.project.homework;

public class ColorInfo {
    private final String englishName;
    private final int colorResource;    // e.g., R.color.app_red
    private final int audioResource;    // e.g., R.raw.rouge
    private final int textColorResource; // e.g., R.color.white or R.color.black

    public ColorInfo(String englishName, int colorResource, int audioResource, int textColorResource) {
        this.englishName = englishName;
        this.colorResource = colorResource;
        this.audioResource = audioResource;
        this.textColorResource = textColorResource;
    }

    public String getEnglishName() {
        return englishName;
    }

    public int getColorResource() {
        return colorResource;
    }

    public int getAudioResource() {
        return audioResource;
    }

    public int getTextColorResource() {
        return textColorResource;
    }
}
