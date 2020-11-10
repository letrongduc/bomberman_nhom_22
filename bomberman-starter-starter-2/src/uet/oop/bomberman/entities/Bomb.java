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
public class Bomb extends Entity{
    private List<Image> Bombsmall = new ArrayList<>();
    private List<Image> Bombbig = new ArrayList<>();
    private List<Image> Bomb_exploded =new ArrayList<>();
    private List<Image> explosion_horizontal = new ArrayList<>();
    private List<Image> explosion_horizontal_left = new ArrayList<>();
    private List<Image> explosion_horizontal_right = new ArrayList<>();
    private List<Image> explosion_vertical  = new ArrayList<>();
    private List<Image> explosion_vertical_down = new ArrayList<>();
    private List<Image> explosion_vertical_top = new ArrayList<>();
    private int idbomb=1;


    public Bomb(int x,int y,Image img){
        super(x,y,img);
        Bombsmall.add(Sprite.bomb.getFxImage());
        Bombsmall.add(Sprite.bomb_1.getFxImage());

        Bombbig.add(Sprite.bomb_1.getFxImage());
        Bombbig.add(Sprite.bomb_2.getFxImage());

        Bomb_exploded.add(Sprite.bomb_exploded.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded1.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded2.getFxImage());
    }
    

    @Override
    public void update() {

    }
}
