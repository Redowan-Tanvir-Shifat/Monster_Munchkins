package com.example.return_3.main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundUrls = new URL[14]; // Adjust the size according to the number of sound files

    public Sound() {
        soundUrls[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
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
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrls[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            adjustVolume();
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

    private void adjustVolume() {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float dB = (float) (Math.log(6) / Math.log(10) * 20); // Adjust volume here, 6 means maximum volume
            gainControl.setValue(dB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
