package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Oneal extends Entity {
    private List<Image> imgMoveLeft=new ArrayList<>();
    private List<Image> imgMoveRight=new ArrayList<>();
    private List<Image> imgMoveUp=new ArrayList<>();
    private List<Image> imgMoveDown=new ArrayList<>();
    private List<Image> imgDead=new ArrayList<>();

    private int MovingLoopindex;

    private double speed;
    private double loop;

    public Oneal(int x, int y, Image img) {
        super(x, y, img);

        imgMoveLeft.add(Sprite.oneal_left1.getFxImage());
        imgMoveLeft.add(Sprite.oneal_left2.getFxImage());
        imgMoveLeft.add(Sprite.oneal_left3.getFxImage());

        imgMoveRight.add(Sprite.oneal_right1.getFxImage());
        imgMoveRight.add(Sprite.oneal_right2.getFxImage());
        imgMoveRight.add(Sprite.oneal_right3.getFxImage());

        imgMoveUp.add(Sprite.oneal_left1.getFxImage());
        imgMoveUp.add(Sprite.oneal_left2.getFxImage());
        imgMoveUp.add(Sprite.oneal_left3.getFxImage());

        imgMoveDown.add(Sprite.oneal_right1.getFxImage());
        imgMoveDown.add(Sprite.oneal_right2.getFxImage());
        imgMoveDown.add(Sprite.oneal_right3.getFxImage());

        keymove = "";
        MovingLoopindex = 0;
        speed = 0;
        loop = 0;
    }

    private String getkeymoving() {
        List<String> d = Map.directionposible(x, y);
        Random generator = new Random();
        int key = generator.nextInt(d.size());
        return d.get(key);
    }

    private Image onealMoveLeft() {
        if (x > 0 && Map.checkcollision(x, y, "Left", 1, 0.1)) x = (double) Math.round((x - speed) * 100) / 100;
        if (img == imgMoveLeft.get(0)) return imgMoveLeft.get(1);
        else if (img == imgMoveLeft.get(1)) return imgMoveLeft.get(2);
        else if (img == imgMoveLeft.get(2)) return imgMoveLeft.get(0);
        else return imgMoveLeft.get(0);
    }

    private Image onealMoveRight() {
        if (x < BombermanGame.WIDTH - 1 && Map.checkcollision(x, y, "Right", 1, 0.1)) x = (double) Math.round((x + speed) * 100) / 100;
        if (img == imgMoveRight.get(0)) return imgMoveRight.get(1);
        else if (img == imgMoveRight.get(1)) return imgMoveRight.get(2);
        else if (img == imgMoveRight.get(2)) return imgMoveRight.get(0);
        else return imgMoveRight.get(0);
    }

    private Image onealMoveUp() {
        if (y > 0 && Map.checkcollision(x, y, "Up", 1, 0.1)) y = (double) Math.round((y - speed) * 100) / 100;
        if (img == imgMoveUp.get(0)) return imgMoveUp.get(1);
        else if (img == imgMoveUp.get(1)) return imgMoveUp.get(2);
        else if (img == imgMoveUp.get(2)) return imgMoveUp.get(0);
        else return imgMoveUp.get(0);
    }

    private Image onealMoveDown() {
        if (y < BombermanGame.HEIGHT - 1 && Map.checkcollision(x, y, "Down", 1, 0.1)) y = (double) Math.round((y + speed) * 100) / 100;
        if (img == imgMoveDown.get(0)) return imgMoveDown.get(1);
        else if (img == imgMoveDown.get(1)) return imgMoveDown.get(2);
        else if (img == imgMoveDown.get(2)) return imgMoveDown.get(0);
        else return imgMoveDown.get(0);
    }

    private void MovingLoop() {
        if (MovingLoopindex == loop) MovingLoopindex = 0;

        if (MovingLoopindex == 0) {
            keymove = getkeymoving();
            speed = 0.1;
            loop = 10;
        }
//        System.out.println("bomberX,bomberY = " + Map.myBomber.getX() + " " + Map.myBomber.getY());
//        System.out.println("x, y = " + x + " "  + y);

        if(Map.myBomber.getY() == y && Map.myBomber.getX() != x && Map.checkLocalHor(x, y) == true && MovingLoopindex == 0){
//            System.out.println("trung ngang");
            speed = 0.25;
            loop = 4;
            if(x > Map.myBomber.getX()){
                keymove = "Left";
            } else {
                keymove = "Right";
            }

        } else if(Map.myBomber.getX() == x && Map.myBomber.getY() != y && Map.checkLocalVer(x, y) == true && MovingLoopindex == 0){
//            System.out.println("trung doc");
            speed = 0.25;
            loop = 4;
            if(y > Map.myBomber.getY()){
                keymove = "Up";
            } else {
                keymove = "Down";
            }
        }
        MovingLoopindex = MovingLoopindex + 1;
        if (keymove == "Left") img = onealMoveLeft();
        if (keymove == "Right") img = onealMoveRight();
        if (keymove == "Up") img = onealMoveUp();
        if (keymove == "Down") img = onealMoveDown();
    }

    @Override
    public void update() {
        MovingLoop();
    }
}
