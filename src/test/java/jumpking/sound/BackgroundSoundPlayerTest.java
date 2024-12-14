package jumpking.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.sound.sampled.Clip;

import static org.mockito.Mockito.*;

public class BackgroundSoundPlayerTest {
    private Clip sound;

    @BeforeEach
    public void setUp() {
        sound = Mockito.mock(Clip.class);
    }

    @Test
    public void backgroundSoundPlayerTest() {

        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer(sound);

        backgroundSoundPlayer.setSound(sound);

        backgroundSoundPlayer.start();

        Mockito.verify(sound, times(1)).setMicrosecondPosition(0);
        Mockito.verify(sound, times(1)).start();
        Mockito.verify(sound, times(1)).loop(Clip.LOOP_CONTINUOUSLY);

        backgroundSoundPlayer.stop();
        Mockito.verify(sound, times(1)).stop();
    }
}