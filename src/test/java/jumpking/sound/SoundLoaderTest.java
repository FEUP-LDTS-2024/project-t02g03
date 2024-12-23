package jumpking.sound;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SoundLoaderTest {
    private AudioInputStream audioInput;
    private Clip musicClip;

    @BeforeEach
    public void setUp() {
        this.audioInput = Mockito.mock(AudioInputStream.class);
        this.musicClip = Mockito.mock(Clip.class);
    }

    @Test
    public void loadSoundSuccess() throws Exception {
        Clip sound = new SoundLoader().loadSound(audioInput, musicClip);
        BackgroundSoundPlayer backgroundSoundPlayer = new BackgroundSoundPlayer(sound);

        verify(musicClip, Mockito.times(1)).open(audioInput);
        assertEquals(sound, backgroundSoundPlayer.getSound());
    }

    @Test
    public void loadSoundReturnNotNull() throws Exception {
        SoundLoader soundLoader = new SoundLoader();
        Clip result = soundLoader.loadSound(audioInput, musicClip);

        assertNotNull(result, "The loadSound method should not return null.");
    }

    @Test
    public void loadSoundFailure() throws Exception {
        doThrow(new FileNotFoundException()).when(musicClip).open(Mockito.any());
        SoundLoader soundLoader = new SoundLoader();
        Exception exception = assertThrows(Exception.class, () -> {
            soundLoader.loadSound(audioInput, musicClip);
        });
        assertEquals("Unable to load sound file!", exception.getMessage());
    }
}
