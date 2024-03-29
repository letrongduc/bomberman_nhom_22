package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.bombEffect.Explosion_horizontal;
import uet.oop.bomberman.entities.movingentities.movingEntity;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.util.Duration;
import uet.oop.bomberman.media.mediaPlayer;

public class Bomb extends bombEffect {
    private List<Image> Bombsmall = new ArrayList<>();
    private List<Image> Bombbig = new ArrayList<>();
    private List<Image> Bomb_exploded = new ArrayList<>();
    public static int idbomb = 1;
    public static boolean isexploded = false;

    private int timedelaycount = 0;
    private int timeexploded = 0;

    public boolean isEnd = false;


    public Bomb(double x, double y, Image img) {
        super(x, y, img);
        isexploded = true;
        Bombbig.add(Sprite.bomb.getFxImage());
        Bombbig.add(Sprite.bomb_1.getFxImage());

        Bombsmall.add(Sprite.bomb_1.getFxImage());
        Bombsmall.add(Sprite.bomb_2.getFxImage());

        Bomb_exploded.add(Sprite.bomb_exploded.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded1.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded2.getFxImage());
    }

    public Image Bombpreparingexplode() {
        if (idbomb == 1) {
            if (img == Bombsmall.get(0)) return Bombsmall.get(1);
            else return Bombsmall.get(0);
        } else {
            if (img == Bombbig.get(0)) return Bombbig.get(1);
            else return Bombbig.get(0);
        }
    }

    public Image Bombexploded() {
        if (img == Bomb_exploded.get(0)) return Bomb_exploded.get(1);
        if (img == Bomb_exploded.get(1)) return Bomb_exploded.get(2);
        else return Bomb_exploded.get(0);
    }

    public Image looptime() {
        if (timedelaycount == 24) {
            isEnd = true;
            if (timeexploded != 6) {
                Map.checkdeadbybomb(this.x, this.y);
                timeexploded = timeexploded + 1;
                return Bombexploded();
            } else {
                isexploded = false;
                isEnd = false;
                return null;
            }
        } else {
            if (timedelaycount == 23) {
                mediaPlayer.mediaExplosionPlayer.play();
            }
            timedelaycount = timedelaycount + 1;

            if (timedelaycount % 2 == 0) {
                return Bombpreparingexplode();
            } else return img;
        }
    }
    @Override
    public void update() {
        img = looptime();
    }
}
