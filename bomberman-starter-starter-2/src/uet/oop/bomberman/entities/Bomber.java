package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Map.Map;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {


    private List<Image> imgmoveleft = new ArrayList<>();
    private List<Image> imgmoveright = new ArrayList<>();
    private List<Image> imgmoveup = new ArrayList<>();
    private List<Image> imgmovedown = new ArrayList<>();
    private List<Image> imgdead = new ArrayList<>();

    private Boolean hold = false;
    private String keymove = "";

    public Bomber(int x, int y, Image img) {
        super(x, y, img);

        imgmoveleft.add(Sprite.player_left.getFxImage());
        imgmoveleft.add(Sprite.player_left_1.getFxImage());
        imgmoveleft.add(Sprite.player_left_2.getFxImage());

        imgmoveright.add(Sprite.player_right.getFxImage());
        imgmoveright.add(Sprite.player_right_1.getFxImage());
        imgmoveright.add(Sprite.player_right_2.getFxImage());

        imgmoveup.add(Sprite.player_up.getFxImage());
        imgmoveup.add(Sprite.player_up_1.getFxImage());
        imgmoveup.add(Sprite.player_up_2.getFxImage());

        imgmovedown.add(Sprite.player_down.getFxImage());
        imgmovedown.add(Sprite.player_down_1.getFxImage());
        imgmovedown.add(Sprite.player_down_2.getFxImage());

        imgdead.add(Sprite.player_dead1.getFxImage());
        imgdead.add(Sprite.player_dead2.getFxImage());
        imgdead.add(Sprite.player_dead3.getFxImage());

    }

    public String getKeymove() {
        return keymove;
    }

    public void setKeymove(String keymove) {
        this.keymove = keymove;
    }

    public void setHold(Boolean hold) {
        this.hold = hold;
    }

    private Image Bombermoveleft() {
        if (x >0 && Map.checkcollision(x, y, "Left",0.8,0.25)) x = x - 0.25;
        if (img == imgmoveleft.get(0)) return imgmoveleft.get(1);
        else if (img == imgmoveleft.get(1)) return imgmoveleft.get(2);
        else if (img == imgmoveleft.get(2)) return imgmoveleft.get(0);
        else return imgmoveleft.get(0);
    }

    private Image Bombermoveright(){
        if(x < BombermanGame.WIDTH-1 && Map.checkcollision(x,y,"Right",0.5,0.25) ) x=x+0.25;
        if(img==imgmoveright.get(0)) return imgmoveright.get(1);
        else if(img==imgmoveright.get(1)) return imgmoveright.get(2);
        else if(img==imgmoveright.get(2)) return imgmoveright.get(0);
        else return imgmoveright.get(0);
    }

    private Image Bombermoveup() {
        if (y > 0 && Map.checkcollision(x, y, "Up",0.5,0.25)) y = y - 0.25;
        if (img == imgmoveup.get(0)) return imgmoveup.get(1);
        else if (img == imgmoveup.get(1)) return imgmoveup.get(2);
        else if (img == imgmoveup.get(2)) return imgmoveup.get(0);
        else return imgmoveup.get(0);
    }

    private Image Bombermovedown() {
        if (y < BombermanGame.HEIGHT - 1 && Map.checkcollision(x, y, "Down",0.5,0.25)) y = y + 0.25;
        if (img == imgmovedown.get(0)) return imgmovedown.get(1);
        else if (img == imgmovedown.get(1)) return imgmovedown.get(2);
        else if (img == imgmovedown.get(2)) return imgmovedown.get(0);
        return imgmovedown.get(0);
    }

    @Override
    public void update() {

        if (keymove == "Right" && hold == true) img = Bombermoveright();
        if (keymove == "Up" && hold == true) img = Bombermoveup();
        if (keymove == "Down" && hold == true) img = Bombermovedown();
        if (keymove == "Left" && hold == true) img = Bombermoveleft();
    }
}
