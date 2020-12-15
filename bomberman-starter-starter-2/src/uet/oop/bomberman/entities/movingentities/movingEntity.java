package uet.oop.bomberman.entities.movingentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class movingEntity extends Entity {
    protected boolean isdead= false;
    protected String keymove="";
    protected int countisdead=0;
    protected int MovingLoopindex;

    public movingEntity(double x, double y, Image img) {
        super(x, y, img);
    }


    public void setIsdead(boolean isdead) {
        this.isdead = isdead;
    }


    public String getKeymove() {
        return keymove;
    }
    public void setKeymove(String keymove) {
        this.keymove = keymove;
    }


    public abstract void update();
}
