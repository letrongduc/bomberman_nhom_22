package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Explosion_vertical extends bombEffect {

    private List<Image> explosion_vertical = new ArrayList<>();

    public Explosion_vertical(double x, double y, Image img){
        super(x,y,img);
        explosion_vertical.add(Sprite.explosion_vertical.getFxImage());
        explosion_vertical.add(Sprite.explosion_vertical1.getFxImage());
        explosion_vertical.add(Sprite.explosion_vertical2.getFxImage());
    }

    public Image explosion_vertical(){
        if(img == explosion_vertical.get(0) ) return explosion_vertical.get(1);
        if(img == explosion_vertical.get(1) ) return explosion_vertical.get(2);
        else return explosion_vertical.get(0);
    }
    @Override
    public void update() {
        if(Map.bomb.isEnd == true) {
            if(this.y<Map.bomb.getY()&&Map.isokBombEffect[2]== false)Map.checkdeadbybomb(this.x,this.y);
            else if(this.y>Map.bomb.getY()&&Map.isokBombEffect[3]== false) Map.checkdeadbybomb(this.x,this.y);

            img = explosion_vertical();
        }
        else {
            img = null;
        }
    }
}
