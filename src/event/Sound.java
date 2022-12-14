package event;

import javax.sound.sampled.*;
import java.io.File;

public class Sound {
    private Clip clip;
    private final String[] audio = new String[15];

    public Sound() {
        loadSound();
    }

    private void loadSound() {
        audio[0] = "srcSound/theme_song.wav";
        audio[1] = "srcSound/Explosion.wav";
        audio[2] = "srcSound/timelock.wav";
        audio[3] = "srcSound/life.wav";
        audio[4] = "srcSound/boots.wav";
        audio[5] = "srcSound/bombAdd.wav";
        audio[6] = "srcSound/click.wav";
        audio[7] = "srcSound/playerGetHit.wav";
        audio[8] = "srcSound/bossGetHit.wav";
        audio[9] = "srcSound/surroundBomb.wav";
        audio[10] = "srcSound/readyToPlant.wav";
        audio[11] = "srcSound/bossBombPlanted.wav";
        audio[12] = "srcSound/bossEntered.wav";
        audio[13] = "srcSound/bossMusic.wav";
        audio[14] = "srcSound/bossDead.wav";
    }

    public void setFile(int index) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(audio[index]));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
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
}
