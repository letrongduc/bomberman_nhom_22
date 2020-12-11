package uet.oop.bomberman.entities.Item;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class itemBigbomb extends Entity {
    public itemBigbomb(double x, double y, Image img)
    {
        super(x,y,img);
    }

    @Override
    public void setIschange(boolean ischange) {
        super.setIschange(ischange);
    }

    @Override
    public void update() {
            if(ischange== false){
                img=null;
            }
    }
}
