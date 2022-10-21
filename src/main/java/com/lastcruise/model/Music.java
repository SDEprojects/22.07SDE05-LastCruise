package com.lastcruise.model;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music {
    private static FloatControl musicControl;
    private static Clip clip;
    public static void runAudio(URL path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.loop(2);
            musicControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            musicControl.setValue(-10.0f);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void decreaseMusic(){
        musicControl.setValue(musicControl.getValue() - 6.0f);
    }

    public static void increaseMusic(){
        musicControl.setValue(musicControl.getValue() + 6.0f);
    }

    public static void muteMusic(){
        clip.stop();
    }

    public static void unMuteMusic(){
        clip.start();
    }
}
