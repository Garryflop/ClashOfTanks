package com.company.singleton;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundManager {
    private static volatile SoundManager instance;

    private SoundManager() {
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            synchronized (SoundManager.class) {
                if (instance == null) {
                    instance = new SoundManager();
                }
            }
        }
        return instance;
    }

    public void playSound(String soundFilePath) {
        try {
            // Попытка получить ресурс из classpath
            URL url = getClass().getClassLoader().getResource(soundFilePath);
            if (url == null) {
                System.err.println("Sound file not found: " + soundFilePath);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound: " + e.getMessage());
        }
    }

    public void playMusic(String musicFilePath) {
        try {
            URL url = getClass().getClassLoader().getResource(musicFilePath);
            if (url == null) {
                System.err.println("Music file not found: " + musicFilePath);
                return;
            }
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }
}
