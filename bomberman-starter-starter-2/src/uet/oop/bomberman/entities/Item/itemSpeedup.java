package uet.oop.bomberman.entities.Item;

import uet.oop.bomberman.entities.Entity;

import javafx.scene.image.Image;

public class itemSpeedup extends item {

    public itemSpeedup(double x, double y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (ischange == false) {
            img = null;
        }
    }
}
