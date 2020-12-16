package uet.oop.bomberman.media;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import sun.awt.SunToolkit;

import java.io.File;
import java.nio.file.Paths;

public class mediaPlayer {
    public static AudioClip mediaNewLifePlayer = new AudioClip(Paths.get("sounds/newLife.wav").toUri().toString());
    public static AudioClip mediaMenuPlayer = new AudioClip(Paths.get("sounds/menumusic.wav").toUri().toString());
    public static AudioClip mediaBackgroundPlayer = new AudioClip(Paths.get("sounds/background.wav").toUri().toString());
    public static AudioClip mediaLosePlayer = new AudioClip(Paths.get("sounds/lose.wav").toUri().toString());
    public static AudioClip mediaNextLevelPlayer = new AudioClip(Paths.get("sounds/nextLevel.wav").toUri().toString());
    public static AudioClip mediaExplosionPlayer = new AudioClip(Paths.get("sounds/soundBomb.wav").toUri().toString());
    public static AudioClip mediaGameOverPlayer = new AudioClip(Paths.get("sounds/gameOver.mp3").toUri().toString());
    public static AudioClip mediaWinPlayer = new AudioClip(Paths.get("sounds/winSound.mp3").toUri().toString());

    public mediaPlayer() {
        mediaBackgroundPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaMenuPlayer.setCycleCount(AudioClip.INDEFINITE);
        mediaGameOverPlayer.setCycleCount(AudioClip.INDEFINITE);
    }
}
