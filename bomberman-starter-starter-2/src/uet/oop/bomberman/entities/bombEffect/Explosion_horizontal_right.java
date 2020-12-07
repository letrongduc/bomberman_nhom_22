package uet.oop.bomberman.entities.bombEffect;
import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
public class Explosion_horizontal_right extends bombEffect {
    private List<Image> explosion_horizontal_right = new ArrayList<>();

    public Explosion_horizontal_right(double x, double y, Image img){
        super(x,y,img);
        explosion_horizontal_right.add(Sprite.explosion_horizontal_left_last.getFxImage());
        explosion_horizontal_right.add(Sprite.explosion_horizontal_left_last1.getFxImage());
        explosion_horizontal_right.add(Sprite.explosion_horizontal_left_last2.getFxImage());
    }

    public Image explosion_horizontal_right(){
        if(img == explosion_horizontal_right.get(0) ) return explosion_horizontal_right.get(1);
        if(img == explosion_horizontal_right.get(1) ) return explosion_horizontal_right.get(2);
        else return explosion_horizontal_right.get(0);
    }
    @Override
    public void update() {
        if(Map.bomb.isEnd == true) {
            if(Map.isokBombEffect[1]== false)Map.checkdeadbybomb(this.x,this.y);
            img = explosion_horizontal_right();
        }
        else {
            img = null;
        }
    }
}
