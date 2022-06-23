package Model.Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Path;


public class Sound {
    Clip clip;
    Path[] soundURL = new Path[30];
    public Sound() {
            soundURL[0] = Path.of("resources\\sounds\\BlueBoyAdventure.wav");
            soundURL[1] = Path.of("resources\\sounds\\coin.wav");
            soundURL[2] = Path.of("resources\\sounds\\powerup.wav");
            soundURL[3] = Path.of("resources\\sounds\\unlock.wav");
            soundURL[4] = Path.of("resources\\sounds\\fanfare.wav");

    }
    public void setFile(int i)
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(String.valueOf(soundURL[i])));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }


}
