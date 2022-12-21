package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sound {
    private boolean musicSwitch;
    public void playBGM(String path) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(inputStream);
            if (!this.getMusicSwitch()) {
                clip.start();
                System.out.println("music on");
                System.out.println(getMusicSwitch());
            } else {
                clip.stop();
                System.out.println("music off");
                System.out.println(getMusicSwitch());
            }
        } catch (UnsupportedAudioFileException | IOException u) {
            u.printStackTrace();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMusicSwitch(boolean musicSwitch) {
        this.musicSwitch = musicSwitch;
    }

    public boolean getMusicSwitch() {
        return musicSwitch;
    }
}
