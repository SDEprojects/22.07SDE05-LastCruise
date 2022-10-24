package com.lastcruise.model;
import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
    private static FloatControl musicControl;
    private static Clip clip;
    private static float soundFxVolume = (-10.0f);
    private static float tempFxVolume = (-10.0f);

    public static void runAudio(URL path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.start();
            musicControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            musicControl.setValue(soundFxVolume);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
    public static void decreaseFxVolume(){
        soundFxVolume = musicControl.getValue() - 7.0f;
    }

    public static void increaseFxVolume(){
        soundFxVolume = musicControl.getValue() + 7.0f;
    }

    public static void muteSoundFx(){
        tempFxVolume = soundFxVolume;
        soundFxVolume = musicControl.getMinimum();
    }

    public static void unMuteSoundFx(){
        soundFxVolume = tempFxVolume;
    }
}