package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingentities.movingEntity;

public abstract class bombEffect extends Entity {
    public bombEffect(double x, double y, Image img) {
        super(x, y, img);
    }

    public abstract void update();
}
