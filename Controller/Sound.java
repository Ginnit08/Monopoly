package Controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Sound {
    private boolean musicSwitch;
    public void playBGM(String path, boolean value) {
        try {
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            if (!value) {
                clip.open(inputStream);
                clip.loop(-1);
                setMusicSwitch(true);
                System.out.println("music on");
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
