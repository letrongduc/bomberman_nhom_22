package uet.oop.bomberman.entities.movingentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balloom extends movingEntity {
    private List<Image> imgmoveleft = new ArrayList<>();
    private List<Image> imgmoveright = new ArrayList<>();
    private List<Image> imgmoveup = new ArrayList<>();
    private List<Image> imgmovedown = new ArrayList<>();
    private List<Image> imgdead = new ArrayList<>();

    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        imgmoveleft.add(Sprite.balloom_left1.getFxImage());
        imgmoveleft.add(Sprite.balloom_left2.getFxImage());
        imgmoveleft.add(Sprite.balloom_left3.getFxImage());

        imgmoveright.add(Sprite.balloom_right1.getFxImage());
        imgmoveright.add(Sprite.balloom_right2.getFxImage());
        imgmoveright.add(Sprite.balloom_right3.getFxImage());

        imgmoveup.add(Sprite.balloom_left1.getFxImage());
        imgmoveup.add(Sprite.balloom_left2.getFxImage());
        imgmoveup.add(Sprite.balloom_left3.getFxImage());

        imgmovedown.add(Sprite.balloom_right1.getFxImage());
        imgmovedown.add(Sprite.balloom_right2.getFxImage());
        imgmovedown.add(Sprite.balloom_right3.getFxImage());

        imgdead.add(Sprite.balloom_dead.getFxImage());
        keymove = "";
        MovingLoopindex = 0;
    }

    private String getkeymoving() {
        List<String> d = Map.directionposible(x, y);
        Random generator = new Random();
        int key = generator.nextInt(d.size());
        return d.get(key);
    }

    private Image Balloommoveleft() {
        if (x > 0 && Map.checkcollisionmoving(x, y, "Left", 1, 0.1))
            x = (double) Math.round((x - 0.1) * 10) / 10;
        if (img == imgmoveleft.get(0)) return imgmoveleft.get(1);
        else if (img == imgmoveleft.get(1)) return imgmoveleft.get(2);
        else if (img == imgmoveleft.get(2)) return imgmoveleft.get(0);
        else return imgmoveleft.get(0);
    }

    private Image Balloommoveright() {
        if (x < BombermanGame.WIDTH - 1 && Map.checkcollisionmoving(x, y, "Right", 1, 0.1))
            x = (double) Math.round((x + 0.1) * 10) / 10;
        if (img == imgmoveright.get(0)) return imgmoveright.get(1);
        else if (img == imgmoveright.get(1)) return imgmoveright.get(2);
        else if (img == imgmoveright.get(2)) return imgmoveright.get(0);
        else return imgmoveright.get(0);
    }

    private Image Balloommoveup() {
        if (y > 0 && Map.checkcollisionmoving(x, y, "Up", 1, 0.1))
            y = (double) Math.round((y - 0.1) * 10) / 10;
        if (img == imgmoveup.get(0)) return imgmoveup.get(1);
        else if (img == imgmoveup.get(1)) return imgmoveup.get(2);
        else if (img == imgmoveup.get(2)) return imgmoveup.get(0);
        else return imgmoveup.get(0);
    }

    private Image Balloommovedown() {
        if (y < BombermanGame.HEIGHT - 1 && Map.checkcollisionmoving(x, y, "Down", 1, 0.1))
            y = (double) Math.round((y + 0.1) * 10) / 10;
        if (img == imgmovedown.get(0)) return imgmovedown.get(1);
        else if (img == imgmovedown.get(1)) return imgmovedown.get(2);
        else if (img == imgmovedown.get(2)) return imgmovedown.get(0);
        return imgmovedown.get(0);
    }

    private void MovingLoop() {
        if (MovingLoopindex == 10) MovingLoopindex = 0;
        if (MovingLoopindex == 0) keymove = getkeymoving();
        MovingLoopindex = MovingLoopindex + 1;
        if (keymove == "Left") img = Balloommoveleft();
        if (keymove == "Right") img = Balloommoveright();
        if (keymove == "Up") img = Balloommoveup();
        if (keymove == "Down") img = Balloommovedown();
    }

    private Image Balloomdead() {
        return imgdead.get(0);
    }

    @Override
    public void update() {
        if (isdead == false) {
            MovingLoop();
        } else {
            countisdead = countisdead + 1;
            if (countisdead <= 12) img = Balloomdead();
            else img = null;
        }
    }
}
