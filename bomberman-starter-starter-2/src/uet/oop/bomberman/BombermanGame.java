package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

import javafx.event.*;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.entities.*;

import uet.oop.bomberman.graphics.Sprite;


import uet.oop.bomberman.Map.Map;

import javax.swing.*;
import javax.xml.bind.annotation.XmlType;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class BombermanGame extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static String bomber = "live";

    private GraphicsContext gc;
    private Canvas canvas;

    public static MediaPlayer mediaMenuPlayer =
            new MediaPlayer(new Media(new File("sounds/menumusic.wav").toURI().toString()));



    private List<Entity> nonmovingentities;
    private List<Entity> nonmovingrerenderentities;
    private List<Entity> movingentities=new ArrayList<>();
    private List<Entity> item;
    private int keyrender=0;
    private Stage window;
    private Scene scene1;
    private Scene scene2;


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
        mediaMenuPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaMenuPlayer.seek(Duration.ZERO);
            }
        });

        window = stage;

        // tao scene menu
        Image img1 = new Image("file:menu.jpg");
        ImageView imgv1 = new ImageView(img1);
        imgv1.setFitHeight(Sprite.SCALED_SIZE * HEIGHT-2);
        imgv1.setFitWidth(Sprite.SCALED_SIZE * WIDTH-2);

        Label label1 = new Label("Bấm cách để chơi game >.<");
        label1.setTextFill(Color.WHITE);
        label1.setFont(new Font("Arial", 30));
        label1.setTranslateY(Sprite.SCALED_SIZE * HEIGHT-100);
        label1.setTranslateX((Sprite.SCALED_SIZE * WIDTH-2) / 2 - 180);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label1);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

        Group layout = new Group();
        layout.getChildren().addAll(imgv1, label1);

        scene1 = new Scene(layout, Sprite.SCALED_SIZE * WIDTH-2, Sprite.SCALED_SIZE * HEIGHT-2);
        // Them scene vao stage
        if(bomber.equals("live")){
            window.setScene(scene1);
            mediaMenuPlayer.play();
        }
//        stage.setResizable(false);
        window.setTitle("game");
        window.show();

        //tao scene gameover
        Image img2 = new Image("file:gameover.jpg");
        ImageView imgv2 = new ImageView(img2);
        imgv2.setFitHeight(Sprite.SCALED_SIZE * HEIGHT-2);
        imgv2.setFitWidth(Sprite.SCALED_SIZE * WIDTH-2);

        Label label2 = new Label("Chơi lại (Y/N)?");
        label2.setTextFill(Color.WHITE);
        label2.setFont(new Font("Arial", 20));
        label2.setTranslateY(100);
        label2.setTranslateX((Sprite.SCALED_SIZE * WIDTH-2) / 2 - 65);

        Group layout1 = new Group();
        layout1.getChildren().addAll(imgv2, label2);

        scene2 = new Scene(layout1, Sprite.SCALED_SIZE * WIDTH-2, Sprite.SCALED_SIZE * HEIGHT-2);

//        if(bomber == "died"){
//            stage.setScene(scene2);
//        }




//        myBomber = new Bomber(myMap.bomberX, myMap.bomberY, Sprite.player_right.getFxImage());
//        myMap.movingentities.add(myBomber);

        scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case SPACE:
                        keyrender++;
                        try {
                            new Map("res/levels/Level1.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // Tao Canvas
                        canvas = new Canvas(Sprite.SCALED_SIZE * Map.ngang-2, Sprite.SCALED_SIZE * Map.doc-2);
                        gc = canvas.getGraphicsContext2D();

                        // Tao root container
                        Group root = new Group();
                        root.getChildren().add(canvas);

                        // Tao scene
                        Scene scene = new Scene(root, Color.GREEN);
                        window.setScene(scene);
                        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(javafx.scene.input.KeyEvent event) {
                                switch (event.getCode()) {
                                    case UP:
                                        Map.myBomber.setHold(true);
                                        Map.myBomber.setKeymove("Up");
                                        break;
                                    case DOWN:
                                        Map.myBomber.setHold(true);
                                        Map.myBomber.setKeymove("Down");
                                        break;
                                    case LEFT:
                                        Map.myBomber.setHold(true);
                                        Map.myBomber.setKeymove("Left");
                                        break;
                                    case RIGHT:
                                        Map.myBomber.setHold(true);
                                        Map.myBomber.setKeymove("Right");
                                        break;
                                    case SPACE:
                                        Map.startbomb();
                                        break;
                                }
                            }
                        });

                        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent event) {
                                switch (event.getCode()) {
                                    case UP:
                                        Map.myBomber.setHold(false);
                                    case DOWN:
                                        Map.myBomber.setHold(false);
                                    case LEFT:
                                        Map.myBomber.setHold(false);
                                    case RIGHT:
                                        Map.myBomber.setHold(false);
                                }
                            }
                        });
                        mediaMenuPlayer.stop();
                        Map.mediaBackgroundPlayer.play();
                        break;
                }
            }
        });

        scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case Y:
//                        window.close();
                        window.setScene(scene1);
                        bomber = "live";
                        break;
                    case N:
                        System.exit(0);
                        break;
                }
            }
        });


        AnimationTimerExt timer = new AnimationTimerExt(100) {
            @Override
            public void handle() {
                removerender();
                update();
                render();
                Map.deleteEntities();
                if(Map.Bomberlife == 0){
                    window.setScene(scene2);
                    Map.mediaBackgroundPlayer.stop();
                }
                if (Map.Bomberlife == 3){
                    window.setTitle("live: ♥ ♥ ♥");
                } else if(Map.Bomberlife == 2){
                    window.setTitle("live: ♥ ♥");
                } else if (Map.Bomberlife == 1){
                    window.setTitle("live: ♥");
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
        if(item!=null){
            for(int i=0;i<item.size();i++){
                if (item.get(i).isIschange()== true){
                    gc.clearRect(32 * item.get(i).getX(), 32 * item.get(i).getY(), 32, 32);
                }
            }
        }
    }
    public void update() {
        nonmovingentities = Map.nonmovingentities;
        nonmovingrerenderentities=Map.nonmovingrerenderentities;
        movingentities = Map.movingentities;
        item=Map.item;
        nonmovingentities.forEach(Entity::update);
        nonmovingrerenderentities.forEach(Entity::update);
        movingentities.forEach(Entity::update);
        item.forEach(Entity::update);
    }

    public void render() {
        System.out.println(keyrender);
        if(keyrender == 1) {
            System.out.println("ok");
            nonmovingentities.forEach(g -> g.render(gc));
            keyrender=keyrender+1;
        }
        if (nonmovingrerenderentities != null) nonmovingrerenderentities.forEach(g -> g.render(gc));
        if(item != null) item.forEach(g -> g.render(gc));
        movingentities.forEach(g -> g.render(gc));
    }
}


