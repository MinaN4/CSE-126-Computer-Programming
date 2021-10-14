
package spacerunnergame;
import javafx.scene.media.AudioClip;
public class Sound extends Thread
{
    public  AudioClip music = new AudioClip (this.getClass().getResource("/GameGraphics/audio.mp3").toString());
    public Sound()
    {
        runSound();
    }
    
    private void runSound()
    {
        music.play();
    }
}
