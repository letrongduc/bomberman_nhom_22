package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Explosion_vertical_down extends bombEffect {
    private List<Image> explosion_vertical_down = new ArrayList<>();

    public Explosion_vertical_down(double x, double y, Image img) {
        super(x, y, img);
        explosion_vertical_down.add(Sprite.explosion_vertical_down_last.getFxImage());
        explosion_vertical_down.add(Sprite.explosion_vertical_down_last1.getFxImage());
        explosion_vertical_down.add(Sprite.explosion_vertical_down_last2.getFxImage());
    }

    public Image explosion_vertical_down() {
        if (img == explosion_vertical_down.get(0)) return explosion_vertical_down.get(1);
        if (img == explosion_vertical_down.get(1)) return explosion_vertical_down.get(2);
        else return explosion_vertical_down.get(0);
    }

    @Override
    public void update() {
        if (Map.bomb.isEnd == true) {
            if (Map.isokBombEffect[3] != 1 && Map.isokBombEffect[3] != 2) Map.checkdeadbybomb(this.x, this.y);
            img = explosion_vertical_down();
        } else {
            img = null;
        }
    }
}
