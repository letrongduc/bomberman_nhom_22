package uet.oop.bomberman.entities.bombEffect;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

abstract class bombEffect extends Entity {
    public bombEffect(double x, double y, Image img) {
        super(x, y, img);
    }
}
