package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Explosion_horizontal_left extends bombEffect {
    private List<Image> Explosion_horizontal_left = new ArrayList<>();

    public Explosion_horizontal_left(double x, double y, Image img){
        super(x,y,img);
        Explosion_horizontal_left.add(Sprite.explosion_horizontal_right_last.getFxImage());
        Explosion_horizontal_left.add(Sprite.explosion_horizontal_right_last1.getFxImage());
        Explosion_horizontal_left.add(Sprite.explosion_horizontal_right_last2.getFxImage());
    }

    public Image Explosion_horizontal_left(){
        if(img == Explosion_horizontal_left.get(0) ) return Explosion_horizontal_left.get(1);
        if(img == Explosion_horizontal_left.get(1) ) return Explosion_horizontal_left.get(2);
        else return Explosion_horizontal_left.get(0);
    }
    @Override
    public void update() {
        if(Map.bomb.isEnd == true) {
            if(Map.isokBombEffect[0]== false) Map.checkdeadbybomb(this.x,this.y);
            img = Explosion_horizontal_left();
        }
        else {
            img = null;
        }
    }
}
