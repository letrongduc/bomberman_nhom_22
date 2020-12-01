package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;


public class Brick extends Entity {
    private List<Image> destroyedBrick=new ArrayList<>();
    private int counttime =0;
    private int destroydelaycount=1;
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        destroyedBrick.add(Sprite.brick_exploded.getFxImage());
        destroyedBrick.add(Sprite.brick_exploded1.getFxImage());
        destroyedBrick.add(Sprite.brick_exploded2.getFxImage());
    }

    public Image destroyingBrick(){
        if (img == destroyedBrick.get(0)) return destroyedBrick.get(1);
        if (img == destroyedBrick.get(1)) return destroyedBrick.get(2);
        if (img == destroyedBrick.get(2)) return null;
        if( img ==null ) return null;
        else return destroyedBrick.get(0);
    }

    @Override
    public void update() {
        if (ischange== true)
        {
            for (int i = 0; i < Map.nonmovingrerenderentities.size(); i++)
                if (Map.nonmovingrerenderentities.get(i) == this) {
                    if (counttime == 40) {
                        if (destroydelaycount % 3 == 0) {
                            img = destroyingBrick();
                            destroydelaycount++;
                        } else if (img != null) destroydelaycount++;
                    } else counttime = counttime + 1;
                }
        }
    }
}
