package com.example.return_3.main;

import javax.sound.sampled.*;
import java.net.URL;

public class Sound {
    private Clip clip;
    private URL[] soundUrls = new URL[20];
    FloatControl fc;
    public float volume = 0.6f; // Default volume
    public int  volumeScale = 3;

    public Sound() {
        // Initialize sound URLs
        //soundUrls[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
        soundUrls[0] = getClass().getResource("/sound/bgMusic.wav");
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
        soundUrls[15] = getClass().getResource("/sound/monsterIsland.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundUrls[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();
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

    public void checkVolume() {
        switch (volumeScale){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }


}
