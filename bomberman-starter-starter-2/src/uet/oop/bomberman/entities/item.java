package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public abstract class item {
    protected int x;
    protected int y;
    protected Image img;

    public item(int x, int y, Image img){
        this.x = x;
        this.y = y;
        this.img = img;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void update();
}
