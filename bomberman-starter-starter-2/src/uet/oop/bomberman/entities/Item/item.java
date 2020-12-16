package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class item extends Entity {
    protected boolean ischange = true;

    public item(double x, double y, Image img) {
        super(x, y, img);
    }

    public void setIschange(boolean ischange) {
        this.ischange = ischange;
    }

    public boolean isIschange() {
        return ischange;
    }

    public abstract void update();
}
