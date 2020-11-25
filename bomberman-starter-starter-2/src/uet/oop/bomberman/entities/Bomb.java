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
    static private boolean isexploded =false;
    private int timedelaycount =0;
    private int timeexploded=0;
    public boolean isEnd = false;

    public Bomb(double x,double y,Image img){
        super(x,y,img);
        Bombbig.add(Sprite.bomb.getFxImage());
        Bombbig.add(Sprite.bomb_1.getFxImage());

        Bombsmall.add(Sprite.bomb_1.getFxImage());
        Bombsmall.add(Sprite.bomb_2.getFxImage());

        Bomb_exploded.add(Sprite.bomb_exploded.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded1.getFxImage());
        Bomb_exploded.add(Sprite.bomb_exploded2.getFxImage());

        explosion_horizontal.add(Sprite.explosion_horizontal.getFxImage());
        explosion_horizontal.add(Sprite.explosion_horizontal1.getFxImage());
        explosion_horizontal.add(Sprite.explosion_horizontal2.getFxImage());

        explosion_horizontal_left.add(Sprite.explosion_horizontal_left_last.getFxImage());
        explosion_horizontal_left.add(Sprite.explosion_horizontal_left_last1.getFxImage());
        explosion_horizontal_left.add(Sprite.explosion_horizontal_left_last2.getFxImage());

        explosion_horizontal_right.add(Sprite.explosion_horizontal_right_last.getFxImage());
        explosion_horizontal_right.add(Sprite.explosion_horizontal_right_last1.getFxImage());
        explosion_horizontal_right.add(Sprite.explosion_horizontal_right_last2.getFxImage());

        explosion_vertical.add(Sprite.explosion_vertical.getFxImage());
        explosion_vertical.add(Sprite.explosion_vertical1.getFxImage());
        explosion_vertical.add(Sprite.explosion_vertical2.getFxImage());

        explosion_vertical_down.add(Sprite.explosion_vertical_down_last.getFxImage());
        explosion_vertical_down.add(Sprite.explosion_vertical_down_last1.getFxImage());
        explosion_vertical_down.add(Sprite.explosion_vertical_down_last2.getFxImage());

        explosion_vertical_top.add(Sprite.explosion_vertical_top_last.getFxImage());
        explosion_vertical_top.add(Sprite.explosion_vertical_top_last1.getFxImage());
        explosion_vertical_top.add(Sprite.explosion_vertical_top_last2.getFxImage());

    }

    public Image Bombpreparingexplode(){
        if(idbomb==1){
            if(img == Bombsmall.get(0) )return Bombsmall.get(1);
            else return Bombsmall.get(0);
        }
        else
        {
            if(img== Bombbig.get(0)) return Bombbig.get(1);
            else return Bombbig.get(0);
        }
    }
    public Image explosion_horizontal_exploded(Image img){
        if(img == explosion_horizontal.get(0) ) return explosion_horizontal.get(1);
        if(img == explosion_horizontal.get(1) ) return explosion_horizontal.get(2);
        else return explosion_horizontal.get(0);
    }
    public Image Bombexploded(){
            if(img==Bomb_exploded.get(0)) return Bomb_exploded.get(1);
            if(img==Bomb_exploded.get(1)) return Bomb_exploded.get(2);
            else return Bomb_exploded.get(0);
    }
    public Image looptime(){
        System.out.println(timeexploded);
        System.out.println(isEnd);
            if (timedelaycount == 16)
            {
                isexploded=true;
                if(timeexploded!= 6){
                    timeexploded=timeexploded+1;
                    if(timedelaycount % 2 == 0 ) return Bombexploded();
                    else return img;
                }
                else {
                    isexploded=false;
                    isEnd =true;
                    return null;
                }
            }
            else {
                timedelaycount=timedelaycount+1;
                if(timedelaycount % 2 == 0 ) return Bombpreparingexplode();
                else return img;
            }
    }
    


    @Override
    public void update() {
        img = looptime();
    }
}
