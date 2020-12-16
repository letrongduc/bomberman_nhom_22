package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import uet.oop.bomberman.Map.GameOver;
import uet.oop.bomberman.Map.menu;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.entities.Item.item;
import uet.oop.bomberman.entities.bombEffect.bombEffect;
import uet.oop.bomberman.entities.movingentities.Bomber;
import uet.oop.bomberman.entities.movingentities.movingEntity;
import uet.oop.bomberman.entities.nonmovingentities.Brick;
import uet.oop.bomberman.media.mediaPlayer;

import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.nonmovingentities.nonMovingEntity;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static String bomber = "live";

    public static GraphicsContext gc;

    private List<nonMovingEntity>  nonmovingentities;
    private List<nonMovingEntity>  nonmovingrerenderentities;
    private List<movingEntity> movingentities=new ArrayList<>();
    private List<bombEffect> bombeffects;
    private List<item> item;
    public static int keyrender=0;
    public static Stage window;



//    public static Bomber myBomber;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    public static void setBomber(String bomber) {
        BombermanGame.bomber = bomber;
    }

    public abstract class AnimationTimerExt extends AnimationTimer {

        private long sleepNs = 0;

        long prevTime = 0;

        public AnimationTimerExt(long sleepMs) {
            this.sleepNs = sleepMs * 500000;
        }

        @Override
        public void handle(long now) {
            // some delay

            if ((now - prevTime) < sleepNs) {

                return;
            }
            prevTime = now;
            handle();
        }

        public abstract void handle();

    }
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        new mediaPlayer();
        new menu();
//        stage.setResizable(false);
        window.setTitle("Game Start");
        window.show();

        AnimationTimerExt timer = new AnimationTimerExt(100) {
            @Override
            public void handle() {
                if(keyrender!=0)
                {
                    System.out.println("handling");
                    removerender();
                    update();
                    render();
                    try {
                        Map.deleteEntities();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (Map.Bomberlife == 0) {
                        Map.map = null;
                        GameOver.go = new GameOver();
                        mediaPlayer.mediaBackgroundPlayer.stop();
                    }
                    window.setTitle("Live: " + Map.map.Bomberlife);
                }
            }
        };
        timer.start();
    }

    public void removerender(){
        if(movingentities!= null)
        {
            if (movingentities.size() != 0)
                for (int i = 0; i < movingentities.size(); i++) {
//            System.out.println(movingentities.get(i).getX()+","+movingentities.get(i).getY());
                    gc.clearRect(32 * movingentities.get(i).getX(), 32 * movingentities.get(i).getY(), 32, 32);
                }
        }
        if(nonmovingrerenderentities!= null) {
            if (nonmovingrerenderentities.size() != 0)
                for (int i = 0; i < nonmovingrerenderentities.size(); i++) {
//            System.out.println(movingentities.get(i).getX()+","+movingentities.get(i).getY());
                    if(nonmovingrerenderentities.get(i) instanceof Brick == true && nonmovingrerenderentities.get(i).isIschange() ==true)
                    {

                        gc.clearRect(32 * nonmovingrerenderentities.get(i).getX(), 32 * nonmovingrerenderentities.get(i).getY(), 32, 32);
                    }
                }
        }
        if(bombeffects!= null){
            for(int i=0;i<bombeffects.size();i++){
                gc.clearRect(32 * bombeffects.get(i).getX(), 32 * bombeffects.get(i).getY(), 32, 32);
            }
        }
        if(item!=null){
            for(int i=0;i<item.size();i++){
                if (item.get(i).isIschange()== true){
                    gc.clearRect(32 * item.get(i).getX(), 32 * item.get(i).getY(), 32, 32);
                }
            }
        }
    }
    public void update() {
        if(keyrender != 0)
        {
            nonmovingentities = Map.nonmovingentities;
            nonmovingrerenderentities = Map.nonmovingrerenderentities;
            movingentities = Map.movingentities;
            bombeffects=Map.bombeffects;
            item = Map.item;
            nonmovingentities.forEach(Entity::update);
            nonmovingrerenderentities.forEach(Entity::update);
            movingentities.forEach(Entity::update);
            bombeffects.forEach(Entity::update);
            item.forEach(Entity::update);
        }
    }

    public void render() {
        if(keyrender!=0)
        {
            System.out.println("---rendering---");
            if (keyrender == 1) {
                System.out.println("ok");
                nonmovingentities.forEach(g -> g.render(gc));
                keyrender = keyrender + 1;
            }
            if (nonmovingrerenderentities != null) nonmovingrerenderentities.forEach(g -> g.render(gc));
            if (item != null) item.forEach(g -> g.render(gc));
            movingentities.forEach(g -> g.render(gc));
            if(bombeffects!=null) bombeffects.forEach(g -> g.render(gc));
        }
    }
}


