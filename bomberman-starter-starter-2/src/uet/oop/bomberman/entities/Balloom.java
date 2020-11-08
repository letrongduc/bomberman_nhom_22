package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Balloom extends Entity {
    private List<Image> imgmoveleft=new ArrayList<>();
    private List<Image> imgmoveright=new ArrayList<>();
    private List<Image> imgmoveup=new ArrayList<>();
    private List<Image> imgmovedown=new ArrayList<>();
    private List<Image> imgdead=new ArrayList<>();

    private int MovingLoopindex;
    public Balloom(int x, int y, Image img) {
        super(x, y, img);
        imgmoveleft.add(Sprite.balloom_left1.getFxImage());
        imgmoveleft.add(Sprite.balloom_left2.getFxImage());
        imgmoveleft.add(Sprite.balloom_left3.getFxImage());

        imgmoveright.add(Sprite.balloom_right1.getFxImage());
        imgmoveright.add(Sprite.balloom_right2.getFxImage());
        imgmoveright.add(Sprite.balloom_right3.getFxImage());

        imgmoveup.add(Sprite.balloom_left1.getFxImage());
        imgmoveup.add(Sprite.balloom_left2.getFxImage());
        imgmoveup.add(Sprite.balloom_left3.getFxImage());

        imgmovedown.add(Sprite.balloom_right1.getFxImage());
        imgmovedown.add(Sprite.balloom_right2.getFxImage());
        imgmovedown.add(Sprite.balloom_right3.getFxImage());

        imgdead.add(Sprite.balloom_dead.getFxImage());
        keymove="";
        MovingLoopindex=0;
    }

    private String  getkeymoving(){
        List<String> d= Map.directionposible(x,y);
        Random generator = new Random();
        int key =generator.nextInt(d.size());
        System.out.println(key);
        return d.get(key);
    }

    private Image Balloommoveleft(){
        if(x > 1 && Map.checkcollision(x,y,"a",1,0.2) ) x=x-0.2;
        if(img==imgmoveleft.get(0)) return imgmoveleft.get(1);
        else if(img==imgmoveleft.get(1)) return imgmoveleft.get(2);
        else if(img==imgmoveleft.get(2)) return imgmoveleft.get(0);
        else return imgmoveleft.get(0);
    }

    private Image Balloommoveright(){
        if(x < BombermanGame.WIDTH-2 && Map.checkcollision(x,y,"d",1,0.2) ) x=x+0.2;
        if(img==imgmoveright.get(0)) return imgmoveright.get(1);
        else if(img==imgmoveright.get(1)) return imgmoveright.get(2);
        else if(img==imgmoveright.get(2)) return imgmoveright.get(0);
        else return imgmoveright.get(0);
    }

    private Image Balloommoveup(){
        if(y>1 && Map.checkcollision(x,y,"w",1,0.2) ) y=y-0.2;
        if(img==imgmoveup.get(0)) return imgmoveup.get(1);
        else if(img==imgmoveup.get(1)) return imgmoveup.get(2);
        else if(img==imgmoveup.get(2)) return imgmoveup.get(0);
        else return imgmoveup.get(0);
    }

    private Image Balloommovedown(){
        if(y<=BombermanGame.HEIGHT-2 && Map.checkcollision(x,y,"s",1,0.2) ) y=y+0.2;
        if(img==imgmovedown.get(0)) return imgmovedown.get(1);
        else if(img==imgmovedown.get(1)) return imgmovedown.get(2);
        else if(img==imgmovedown.get(2)) return imgmovedown.get(0);
        return imgmovedown.get(0);
    }
    private void MovingLoop(){
        if (MovingLoopindex==5) MovingLoopindex=0;
        if (MovingLoopindex==0) keymove=getkeymoving();
        MovingLoopindex = MovingLoopindex+1;
        if(keymove=="d") img=Balloommoveright();
        if(keymove=="a") img=Balloommoveleft();
        if(keymove=="w") img=Balloommoveup();
        if(keymove=="s") img=Balloommovedown();
    }

    @Override
    public void update() {
        MovingLoop();
    }
}
