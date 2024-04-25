package com.example.return_3.main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundUrls = new URL[20];
    private FloatControl gainControl;
    private float volume = 0.6f; // Default volume
    private int volumeScale = 3;

    public Sound() {
        // Initialize sound URLs
        //soundUrls[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundUrls[1] = getClass().getResource("/sound/coin.wav");
        soundUrls[2] = getClass().getResource("/sound/powerup.wav");
        soundUrls[3] = getClass().getResource("/sound/unlock.wav");
        soundUrls[4] = getClass().getResource("/sound/fanfare.wav");
        soundUrls[5] = getClass().getResource("/sound/hitmonster.wav");
        soundUrls[6] = getClass().getResource("/sound/receivedamage.wav");
        soundUrls[7] = getClass().getResource("/sound/swordswing.wav");
        soundUrls[8] = getClass().getResource("/sound/levelup.wav");
        soundUrls[9] = getClass().getResource("/sound/cursor.wav");
        soundUrls[10] = getClass().getResource("/sound/burning.wav");
        soundUrls[11] = getClass().getResource("/sound/cuttree.wav");
        soundUrls[12] = getClass().getResource("/sound/gameover.wav");
        soundUrls[13] = getClass().getResource("/sound/stairs.wav");
        soundUrls[14] = getClass().getResource("/sound/newSword.wav");
        soundUrls[0] = getClass().getResource("/sound/bgMusic.wav");

        // Set default volume
        volume = volumeScaleToFloat(volumeScale);
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrls[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            adjustVolume(); // Adjust volume when loading the sound file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }

    public void increaseVolume() {
        if (volumeScale < 5) {
            volumeScale++;
            volume = volumeScaleToFloat(volumeScale);
            adjustVolume();
        }
    }

    public void decreaseVolume() {
        if (volumeScale > 0) {
            volumeScale--;
            volume = volumeScaleToFloat(volumeScale);
            adjustVolume();
        }
    }

    private void adjustVolume() {
        try {
            float dB = volumeToDecibel(volume);
            gainControl.setValue(dB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private float volumeToDecibel(float volume) {
        return (float) (Math.log(volume) / Math.log(10) * 20);
    }

    private float volumeScaleToFloat(int volumeScale) {
        switch (volumeScale) {
            case 0: return 0.0001f; // to avoid -Infinity
            case 1: return 0.1f;
            case 2: return 0.316f;
            case 3: return 0.6f;
            case 4: return 0.8f;
            case 5: return 1.0f;
            default: return 1.0f;
        }
    }
}
