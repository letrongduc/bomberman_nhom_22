package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Explosion_horizontal extends Entity {
    private List<Image> explosion_horizontal = new ArrayList<>();

    public Explosion_horizontal(double x, double y, Image img){
        super(x,y,img);
        explosion_horizontal.add(Sprite.explosion_horizontal.getFxImage());
        explosion_horizontal.add(Sprite.explosion_horizontal1.getFxImage());
        explosion_horizontal.add(Sprite.explosion_horizontal2.getFxImage());
    }

    public Image explosion_horizontal(){
        if(img == explosion_horizontal.get(0) ) return explosion_horizontal.get(1);
        if(img == explosion_horizontal.get(1) ) return explosion_horizontal.get(2);
        else return explosion_horizontal.get(0);
    }
    @Override
    public void update() {
        if(Map.bomb.isEnd == true) img=explosion_horizontal();
        else img=null;
    }

}
