package uet.oop.bomberman.Map;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Item.item;
import uet.oop.bomberman.entities.Item.itemBigbomb;
import uet.oop.bomberman.entities.Item.itemDetonator;
import uet.oop.bomberman.entities.Item.itemSpeedup;
import uet.oop.bomberman.entities.bombEffect.*;
import uet.oop.bomberman.entities.movingentities.*;
import uet.oop.bomberman.entities.nonmovingentities.Brick;
import uet.oop.bomberman.entities.nonmovingentities.Wall;
import uet.oop.bomberman.entities.nonmovingentities.nonMovingEntity;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.media.mediaPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    public static Map map;
    public static List<nonMovingEntity> nonmovingentities;
    public static List<nonMovingEntity> nonmovingrerenderentities;
    public static List<movingEntity> movingentities;
    public static List<bombEffect> bombeffects;
    public static List<item> item;
    private static ArrayList<String> area;
    public static Bomb bomb;
    //    private static GraphicsContext gc;
    public static Canvas canvas;
    public static int level;
    public static int doc;
    public static int ngang;


    public static List<bombEffect> explosion1;
    public static List<bombEffect> explosion2;
    public static List<bombEffect> explosionlast;

    public static int[] isokBombEffect;
    public static boolean isokadd;

    private static boolean isSpeedupok;
    private static boolean isBigBombok;

    //   public static int counttime = 0;
    public static Bomber myBomber;
    public static int Bomberlife;

    public ArrayList readMap(String path) throws IOException {
        Scanner scanner = new Scanner(Paths.get(path), "UTF-8");
        level = scanner.nextInt();
        doc = scanner.nextInt();
        ngang = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> area = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            String lineData = scanner.nextLine();
            area.add(lineData);
        }
        scanner.close();
        return area;
    }

    public Map(int Level) throws IOException {
        mediaPlayer.mediaBackgroundPlayer.play();

        String path = "res/levels/Level" + Level + ".txt";
        nonmovingentities = new ArrayList<>();
        nonmovingrerenderentities = new ArrayList<>();
        movingentities = new ArrayList<>();
        bombeffects=new ArrayList<>();
        item = new ArrayList<>();
        area = new ArrayList<>();

        explosion1 = new ArrayList<>();
        explosion2 = new ArrayList<>();
        explosionlast = new ArrayList<>();

        isokBombEffect = new int[4];
        isokadd = true;

        boolean isSpeedupok = false;
        boolean isBigBombok = false;
        Bomberlife = 1;
        Bomb.idbomb = 1;

        area = readMap(path);


//        Map.mediaBackgroundPlayer.play();

        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * Map.ngang - 2, Sprite.SCALED_SIZE * Map.doc - 2);
        BombermanGame.gc = canvas.getGraphicsContext2D();
        System.out.println("gc da chay");
        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, Color.GREEN);
        BombermanGame.window.setScene(scene);
        System.out.println("window da chay");
        System.out.println("keyrender map = " + BombermanGame.keyrender);
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

        for (int i = 0; i < area.size(); i++) {
            for (int j = 0; j < area.get(1).length(); j++) {
                if (area.get(i).charAt(j) == '#') {
                    nonMovingEntity object = new Wall(j, i, Sprite.wall.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == '*') {
                    nonMovingEntity object = new Brick(j, i, Sprite.brick.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == 'p') {
                    myBomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                    movingentities.add(myBomber);
                } else if (area.get(i).charAt(j) == '1') {
                    movingEntity object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '2') {
                    movingEntity object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '3') {
                    movingEntity object = new Minvo(j, i, Sprite.minvo_left1.getFxImage());
                    movingentities.add(object);
                }
            }
        }
    }

    public void levelUp(){
        try {
            level++;
            map = new Map(level);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkcollisionmoving(double dx, double dy, String keymove, double widthentity, double distance) {
        double x = (double) Math.round(dx * 100) / 100;
        double y = (double) Math.round(dy * 100) / 100;
        for (int i = 0; i < nonmovingentities.size(); i++) {
            if (keymove == "Right") {
                double Distance = (double) Math.round((x + 1 + distance) * 100) / 100;
                if ((nonmovingentities.get(i).getY() > y - 1 && nonmovingentities.get(i).getY() < y && nonmovingentities.get(i).getX() > x)
                        || (nonmovingentities.get(i).getY() > y && nonmovingentities.get(i).getY() < y + 1 && nonmovingentities.get(i).getX() > x)
                        || (nonmovingentities.get(i).getY() == y && nonmovingentities.get(i).getX() > x)) {
                    if (Distance > nonmovingentities.get(i).getX()) {
                        return false;
                    }
                }
            }
            if (keymove == "Left") {
                double Distance = (double) Math.round((x - 1 - distance) * 100) / 100;
                if ((nonmovingentities.get(i).getY() > y - 1 && nonmovingentities.get(i).getY() < y && nonmovingentities.get(i).getX() < x)
                        || (nonmovingentities.get(i).getY() > y && nonmovingentities.get(i).getY() < y + 1 && nonmovingentities.get(i).getX() < x)
                        || (nonmovingentities.get(i).getY() == y && nonmovingentities.get(i).getX() < x)) {
                    if (Distance < nonmovingentities.get(i).getX()) {
                        return false;
                    }
                }
            }
            if (keymove == "Up") {
                double Distance = (double) Math.round((y - 1 - distance) * 100) / 100;
                if ((nonmovingentities.get(i).getX() > x - 1 && nonmovingentities.get(i).getX() < x && nonmovingentities.get(i).getY() < y) || (nonmovingentities.get(i).getX() < x + 1 && nonmovingentities.get(i).getX() > x && nonmovingentities.get(i).getY() < y) || (nonmovingentities.get(i).getX() == x && nonmovingentities.get(i).getY() < y)) {
                    if (y - 1 - distance < nonmovingentities.get(i).getY()) return false;
                }
            }
            if (keymove == "Down") {
                double Distance = (double) Math.round((y + 1 + distance) * 100) / 100;
                if ((nonmovingentities.get(i).getX() > x - 1 && nonmovingentities.get(i).getX() < x && nonmovingentities.get(i).getY() > y) || (nonmovingentities.get(i).getX() < x + 1 && nonmovingentities.get(i).getX() > x && nonmovingentities.get(i).getY() > y) || (nonmovingentities.get(i).getX() == x && nonmovingentities.get(i).getY() > y)) {
                    if (y + 1 + distance > nonmovingentities.get(i).getY()) return false;
                }

            }
        }
        return true;
    }

    public static List directionposible(double dx, double dy) {
        double x = (double) Math.round(dx * 100) / 100;
        double y = (double) Math.round(dy * 100) / 100;
        List<String> d = new ArrayList<>();
        d.add("Left");
        d.add("Right");
        d.add("Up");
        d.add("Down");
        for (int i = 0; i < nonmovingentities.size() - 1; i++) {
            if (nonmovingentities.get(i).getY() == y - 1 && nonmovingentities.get(i).getX() == x) d.remove("Up");
            if (nonmovingentities.get(i).getY() == y + 1 && nonmovingentities.get(i).getX() == x) d.remove("Down");
            if (nonmovingentities.get(i).getY() == y && nonmovingentities.get(i).getX() == x - 1) d.remove("Left");
            if (nonmovingentities.get(i).getY() == y && nonmovingentities.get(i).getX() == x + 1) d.remove("Right");
        }
        return d;
    }

    public static Boolean checkSpace(double l1, double l2, double index) {
        if ((l1 < l2 && l1 < index && index < l2) || (l1 > l2 && l1 > index && index > l2)) {
            return true;
        }
        return false;
    }

    public static Boolean checkLocalVer(double x, double y) {
        for (int i = 0; i < nonmovingentities.size(); i++) {
            double noMoveX = nonmovingentities.get(i).getX();
            double noMoveY = nonmovingentities.get(i).getY();
            double bomberY = Map.myBomber.getY();
            if (noMoveX == x && checkSpace(bomberY, y, noMoveY) == true) {
//                System.out.println("chan doc");
//                System.out.println(nonmovingentities.get(i).getX() + " " + nonmovingentities.get(i).getY());
                return false;
            }
        }
//        System.out.println("thoang doc");
        return true;
    }

    public static Boolean checkLocalHor(double x, double y) {
        for (int i = 0; i < nonmovingentities.size(); i++) {
            double noMoveX = nonmovingentities.get(i).getX();
            double noMoveY = nonmovingentities.get(i).getY();
            double bomberX = Map.myBomber.getX();
            if (noMoveY == y && checkSpace(bomberX, x, noMoveX) == true) {
//                System.out.println("chan ngang");
//                System.out.println(nonmovingentities.get(i).getX() + " " + nonmovingentities.get(i).getY());
                return false;
            }
        }
//        System.out.println("thoang ngang");
        return true;
    }

    public static int[][] duyetMap() {
        int[][] check = new int[area.size()][area.get(1).length()];
        for (int i = 0; i < nonmovingentities.size(); i++) {
            check[(int) nonmovingentities.get(i).getY()][(int) nonmovingentities.get(i).getX()] = 1;
        }
        return check;
    }

    public static void startbomb() {
        if (Bomb.isexploded == false) {
            nonmovingrerenderentities = new ArrayList<>();
            bombeffects.remove(bomb);
            if (explosion1 != null)
                for (int i = 0; i < explosion1.size(); i++) {
                    bombeffects.remove(explosion1.get(i));
                }
            if (explosionlast != null)
                for (int i = 0; i < explosionlast.size(); i++) {
                    bombeffects.remove(explosionlast.get(i));
                }
            if (Bomb.idbomb == 2 && explosion2 != null) {
                for (int i = 0; i < explosion2.size(); i++) {
                    bombeffects.remove(explosion2.get(i));
                }
            }
            explosion1 = new ArrayList<>();
            explosionlast = new ArrayList<>();
            if (Bomb.idbomb == 2) explosion2 = new ArrayList<>();

            bomb = new Bomb(myBomber.getX(), myBomber.getY(), Sprite.bomb.getFxImage());
            //1left
            explosion1.add(new Explosion_horizontal(bomb.getX() + 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1right
            explosion1.add(new Explosion_horizontal(bomb.getX() - 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1top
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() - 1, Sprite.explosion_vertical.getFxImage()));
            //1down
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() + 1, Sprite.explosion_vertical.getFxImage()));

            if (Bomb.idbomb == 1) {
                //left
                explosionlast.add(new Explosion_horizontal_left(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
                //right
                explosionlast.add(new Explosion_horizontal_right(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
                //top
                explosionlast.add(new Explosion_vertical_top(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical_top_last.getFxImage()));
                //down
                explosionlast.add(new Explosion_vertical_down(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical_down_last.getFxImage()));
            }
            if (Bomb.idbomb == 2) {
                //1left
                explosion2.add(new Explosion_horizontal(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
                //1right
                explosion2.add(new Explosion_horizontal(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
                //1top
                explosion2.add(new Explosion_vertical(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical.getFxImage()));
                //1down
                explosion2.add(new Explosion_vertical(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical.getFxImage()));

                //left
                explosionlast.add(new Explosion_horizontal_left(bomb.getX() + 3, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
                //right
                explosionlast.add(new Explosion_horizontal_right(bomb.getX() - 3, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
                //top
                explosionlast.add(new Explosion_vertical_top(bomb.getX(), bomb.getY() - 3, Sprite.explosion_vertical_top_last.getFxImage()));
                //down
                explosionlast.add(new Explosion_vertical_down(bomb.getX(), bomb.getY() + 3, Sprite.explosion_vertical_down_last.getFxImage()));

            }

             bombeffects.add(bomb);

            for (int i = 0; i < explosion1.size(); i++) {
                bombeffects.add(explosion1.get(i));
            }
            for (int i = 0; i < explosionlast.size(); i++) {
                bombeffects.add(explosionlast.get(i));
            }
            if (Bomb.idbomb == 2) {
                for (int i = 0; i < explosion1.size(); i++) {
                    bombeffects.add(explosion2.get(i));
                }
            }

            for (int i = 0; i < 4; i++) {
                isokBombEffect[i] = 0;
            }


            for (int i = 0; i < nonmovingentities.size(); i++) {
                for (int j = 0; j < explosion1.size(); j++) {
                    if (checkcolision(explosion1.get(j).getX(), explosion1.get(j).getY(), nonmovingentities.get(i).getX(), nonmovingentities.get(i).getY()) == true) {
                        nonmovingrerenderentities.add(nonmovingentities.get(i));
                        if (isokBombEffect[j] == 0) {
                            nonmovingentities.get(i).setIschange(true);
                            isokBombEffect[j] = 1;
                        }

                    }
                }
            }

            if (Bomb.idbomb == 2) {
                for (int i = 0; i < nonmovingentities.size(); i++) {
                    {
                        for (int j = 0; j < explosion2.size(); j++) {
                            if (checkcolision(explosion2.get(j).getX(), explosion2.get(j).getY(), nonmovingentities.get(i).getX(), nonmovingentities.get(i).getY()) == true) {
                                for (int h = 0; h < nonmovingrerenderentities.size(); h++) {
                                    if (nonmovingentities.get(i) == nonmovingrerenderentities.get(h))//xem co bi xac dinh trung doi tuong da co
                                        isokadd = false;
                                }
                                if (isokadd == true) {
                                    nonmovingrerenderentities.add(nonmovingentities.get(i));
                                    if (isokBombEffect[j] != 0) nonmovingentities.get(i).setIschange(false);
                                    if (isokBombEffect[j] == 0) {
                                        nonmovingentities.get(i).setIschange(true);
                                        isokBombEffect[j] = 2;
                                    }
                                }
                            }
                            isokadd = true;
                        }
                    }
                }
            }
            isokadd = true;
            for (int i = 0; i < nonmovingentities.size(); i++) {
                {
                    for (int j = 0; j < explosionlast.size(); j++) {
                        if (checkcolision(explosionlast.get(j).getX(), explosionlast.get(j).getY(), nonmovingentities.get(i).getX(), nonmovingentities.get(i).getY()) == true) {
                            for (int h = 0; h < nonmovingrerenderentities.size(); h++) {
                                if (nonmovingentities.get(i) == nonmovingrerenderentities.get(h))//xem co bi xac dinh trung doi tuong da co
                                    isokadd = false;
                            }
                            if (isokadd == true) {
                                nonmovingrerenderentities.add(nonmovingentities.get(i));
                                if (isokBombEffect[j] != 0) nonmovingentities.get(i).setIschange(false);
                                if (isokBombEffect[j] == 0) {
                                    isokBombEffect[j] = 3;
                                    nonmovingentities.get(i).setIschange(true);
                                }
                            }
                        }
                        isokadd = true;
                    }
                }
            }


        }
    }

    public static <bombEffect> void checkdeadbybomb(double dx, double dy) {
        for (int i = 0; i < movingentities.size(); i++) {
                if (checkcolision(dx, dy, movingentities.get(i).getX(), movingentities.get(i).getY()) == true) {
                    movingentities.get(i).setIsdead(true);
                }
        }

    }

    public static boolean checkdeadbyenermy(double dx,double dy){
        for (int i = 0; i < movingentities.size(); i++) {
            if (checkcolision(dx, dy, movingentities.get(i).getX(), movingentities.get(i).getY()) == true && movingentities.get(i) instanceof Bomber ==false){
                return true;
            }
        }
        return false;
    }

    public static void checkcolisonitem() {
        {
            for (int i = 0; i < item.size(); i++) {
                if (checkcolision(myBomber.getX(), myBomber.getY(), item.get(i).getX(), item.get(i).getY()) == true) {
                    if (item.get(i) instanceof itemSpeedup) {
                        mediaPlayer.mediaNewLifePlayer.play();
                        myBomber.setMovedistance(0.5);
                        item.get(i).setIschange(false);
                    }
                    if (item.get(i) instanceof itemBigbomb) {
                        mediaPlayer.mediaNewLifePlayer.play();
                        Bomb.idbomb = 2;
                        item.get(i).setIschange(false);
                    }
                    if (item.get(i) instanceof itemDetonator) {
                        mediaPlayer.mediaNewLifePlayer.play();
                        Bomberlife = Bomberlife + 1;
                        item.get(i).setIschange(false);
                    }
                }
            }
        }

    }

    public static boolean checkcolision(double x11, double y12, double x21, double y22) {
        double x1 = (double) Math.round(x11 * 100) / 100;
        double y1 = (double) Math.round(y12 * 100) / 100;
        double x2 = (double) Math.round(x21 * 100) / 100;
        double y2 = (double) Math.round(y22 * 100) / 100;
        if (x2 - 1 < x1 && x2 >= x1 && y2 + 1 > y1 && y2 <= y1) return true;
        else if (x2 - 1 < x1 && x2 >= x1 && y2 - 1 < y1 && y2 >= y1) return true;
        else if (x2 + 1 > x1 && x2 <= x1 && y2 + 1 > y1 && y2 <= y1) return true;
        else if (x2 + 1 > x1 && x2 <= x1 && y2 - 1 < y1 && y2 >= y1) return true;
        else return false;
    }

    public static void createItem(double x, double y) {
        Random generator = new Random();
        int key = generator.nextInt((12));
        if (key == 11) {
            if (isSpeedupok == false) {
                item.add(new itemSpeedup(x, y, Sprite.itemSpeedup.getFxImage()));
                isSpeedupok = true;
            }
        }
        if (key == 8) {
            if (isBigBombok == false) {
                item.add(new itemBigbomb(x, y, Sprite.itemBigbomb.getFxImage()));
                isBigBombok = true;
            }
        }
        if (key == 4) {
                item.add(new itemDetonator(x, y, Sprite.itemDetonator.getFxImage()));
        }

    }

    public static void deleteEntities() throws IOException {
        if (nonmovingrerenderentities != null) {
            for (int i = 0; i < nonmovingrerenderentities.size(); i++) {
                if (nonmovingrerenderentities.get(i).getImg() == null) {
                    createItem(nonmovingrerenderentities.get(i).getX(), nonmovingrerenderentities.get(i).getY());
                    nonmovingentities.remove(nonmovingrerenderentities.get(i));
                    nonmovingrerenderentities.remove(nonmovingrerenderentities.get(i));
                }

            }
        }
        for (int i = 0; i < movingentities.size(); i++) {
                if (movingentities.get(i).getImg() == null) {
                    if (movingentities.get(i) == myBomber) {

                        movingentities.remove(i);
                        Bomberlife = Bomberlife - 1;
                        if (Bomberlife > 0) {
                            {
                                myBomber = new Bomber(1, 1, Sprite.player_right.getFxImage());
                                Bomb.idbomb = 1;
                                isSpeedupok=false;
                                isBigBombok=false;
                                movingentities.add(myBomber);
                            }
                        }
                    } else {
                        createItem(movingentities.get(i).getX(), movingentities.get(i).getY());
                        movingentities.remove(i);
                        System.out.println("size = " + movingentities.size());
                        if(movingentities.size() == 1 && Bomberlife != 0){
                            if(level < 2){
                                BombermanGame.keyrender--;
                                mediaPlayer.mediaBackgroundPlayer.stop();
                                mediaPlayer.mediaNextLevelPlayer.play();
                                map.levelUp();
                            } else {

                            }
                        }
                    }
                }

        }
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i).getImg() == null) {
                item.remove(i);
            }
        }
    }
}
