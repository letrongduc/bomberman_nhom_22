package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.event.*;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.graphics.Sprite;


import uet.oop.bomberman.Map.Map;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {


    public static final int WIDTH = 29;
    public static final int HEIGHT = 11;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> nonmovingentities;
    private List<Entity> movingentities=new ArrayList<>();
    private int keyrender=0;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);

    }

    public abstract class AnimationTimerExt extends AnimationTimer {

        private long sleepNs = 0;

        long prevTime = 0;

        public AnimationTimerExt(long sleepMs) {
            this.sleepNs = sleepMs * 1000000;
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
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH-2, Sprite.SCALED_SIZE * HEIGHT-2);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, Color.GREEN);
        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        Map myMap = new Map();
        Bomber myBomber = new Bomber(myMap.bomberX, myMap.bomberY, Sprite.player_right.getFxImage());
        myMap.movingentities.add(myBomber);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        myBomber.setHold(true);
                        myBomber.setKeymove("Up");
                        break;
                    case DOWN:
                        myBomber.setHold(true);
                        myBomber.setKeymove("Down");
                        break;
                    case LEFT:
                        myBomber.setHold(true);
                        myBomber.setKeymove("Left");
                        break;
                    case RIGHT:
                        myBomber.setHold(true);
                        myBomber.setKeymove("Right");
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        myBomber.setHold(false);
                    case DOWN:
                        myBomber.setHold(false);
                    case LEFT:
                        myBomber.setHold(false);
                    case RIGHT:
                        myBomber.setHold(false);
                }
            }
        });
        AnimationTimerExt timer = new AnimationTimerExt(100) {
            @Override
            public void handle() {
                removerender();
                update();
                render();
            }
        };
        timer.start();
    }
    public void removerender(){
        if(movingentities.size()!=0)
        for(int i=0;i<movingentities.size();i++){
            System.out.println(movingentities.get(i).getX()+","+movingentities.get(i).getY());
            gc.clearRect(32*movingentities.get(i).getX(), 32*movingentities.get(i).getY(), 32, 32);
        }

    }
    public void update() {
        nonmovingentities = Map.nonmovingentities;
        movingentities = Map.movingentities;
        movingentities.forEach(Entity::update);
        nonmovingentities.forEach(Entity::update);
    }

    public void render() {
//        for(int i=0;i<movingentities.size();i++){
//            System.out.println(movingentities.get(i).getX()+","+movingentities.get(i).getY());
//            gc.clearRect(movingentities.get(i).getX(), movingentities.get(i).getY(), 32, 32);
//        }
        movingentities.forEach(g -> g.render(gc));
        if(keyrender == 0) {
            nonmovingentities.forEach(g -> g.render(gc));
            keyrender=keyrender+1;
        }
    }
}


