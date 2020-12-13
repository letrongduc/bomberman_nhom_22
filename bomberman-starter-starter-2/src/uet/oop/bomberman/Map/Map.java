package uet.oop.bomberman.Map;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.Item.itemBigbomb;
import uet.oop.bomberman.entities.Item.itemDetonator;
import uet.oop.bomberman.entities.Item.itemSpeedup;
import uet.oop.bomberman.entities.bombEffect.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    public static List<Entity> nonmovingentities = new ArrayList<>();
    public static List<Entity> nonmovingrerenderentities = new ArrayList<>();
    public static List<Entity> movingentities = new ArrayList<>();
    public static List<Entity> item= new ArrayList<>();
    public static ArrayList<String> area = new ArrayList<>();
    public static Bomb bomb;

    public static List<Entity> explosion1 = new ArrayList<>();
    public static List<Entity> explosion2 = new ArrayList<>();
    public static List<Entity> explosionlast = new ArrayList<>();

    public static int[] isokBombEffect = new int[4];
    public static boolean isokadd = true;

 //   public static int counttime = 0;
    public static Bomber myBomber;
    public static int Bomberlife=1;

    public static MediaPlayer mediaBackgroundPlayer =
            new MediaPlayer(new Media(new File("sounds/background.wav").toURI().toString()));

    public static MediaPlayer mediaLosePlayer =
            new MediaPlayer(new Media(new File("sounds/lose.wav").toURI().toString()));

    public ArrayList readMap(String path) throws IOException {
        Scanner scanner = new Scanner(Paths.get(path), "UTF-8");
        int level = scanner.nextInt();
        int doc = scanner.nextInt();
        int ngang = scanner.nextInt();
        scanner.nextLine();
        ArrayList<String> area = new ArrayList<>();
        for (int i = 0; i < 13; i++) {
            String lineData = scanner.nextLine();
            area.add(lineData);
        }
        scanner.close();
        return area;
    }

    public Map(String path) throws IOException {
//        area.add("###############################");
//        area.add("#      ** *    * 2 *  * * *  3#");
//        area.add("# # # #*# # #*#*# # # #*#*#*# #");
//        area.add("#  **     ***  *      * 2 * * #");
//        area.add("# # # # # #*# # #*#*# # # # #*#");
//        area.add("#*           **  *  *        1#");
//        area.add("# # # # # # # # # #*# #*# # # #");
//        area.add("#*  * 1    *  *               #");
//        area.add("# # # # #*# # # #*#*# # # # # #");
//        area.add("#*    **  *       *           #");
//        area.add("# #*# # # # # # #*# # # # # # #");
//        area.add("#    p      *   *  *          #");
//        area.add("###############################");
        area = readMap(path);

        mediaBackgroundPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaBackgroundPlayer.seek(Duration.ZERO);
            }
        });

//        Map.mediaBackgroundPlayer.play();

        for (int i = 0; i < area.size(); i++) {
            for (int j = 0; j < area.get(1).length(); j++) {
                if (area.get(i).charAt(j) == '#') {
                    Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == '*') {
                    Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == 'p') {
                    myBomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                    movingentities.add(myBomber);
                } else if (area.get(i).charAt(j) == '1') {
                    Entity object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '2') {
                    Entity object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '3') {
                    Entity object = new Minvo(j, i, Sprite.minvo_left1.getFxImage());
                    movingentities.add(object);
                }
            }
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
                        System.out.println(Distance);
                        System.out.println(nonmovingentities.get(i).getX());
                        System.out.println();
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
        for (int i = 0; i < area.size(); i++) {
            for (int j = 0; j < area.get(1).length(); j++) {
                if (area.get(i).charAt(j) == '*' || area.get(i).charAt(j) == '#') {
                    check[i][j] = 1;
                } else {
                    check[i][j] = 0;
                }
            }
        }
        return check;
    }

    public static void startbomb() {
        if (Bomb.isexploded == false) {
            nonmovingrerenderentities = new ArrayList<>();
            movingentities.remove(bomb);
            if (explosion1 != null)
                for (int i = 0; i < explosion1.size(); i++) {
                    movingentities.remove(explosion1.get(i));
                }
            if (explosionlast != null)
                for (int i = 0; i < explosionlast.size(); i++) {
                    movingentities.remove(explosionlast.get(i));
                }
            if(Bomb.idbomb == 2 && explosion2 != null){
                for (int i = 0; i < explosion2.size(); i++) {
                    movingentities.remove(explosion2.get(i));
                }
            }
            explosion1 = new ArrayList<>();
            explosionlast = new ArrayList<>();
            if(Bomb.idbomb==2) explosion2 =new ArrayList<>();

            bomb = new Bomb(myBomber.getX(), myBomber.getY(), Sprite.bomb.getFxImage());
            //1left
            explosion1.add(new Explosion_horizontal(bomb.getX() + 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1right
            explosion1.add(new Explosion_horizontal(bomb.getX() - 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1top
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() - 1, Sprite.explosion_vertical.getFxImage()));
            //1down
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() + 1, Sprite.explosion_vertical.getFxImage()));

            if(Bomb.idbomb==1)
            {
                //left
                explosionlast.add(new Explosion_horizontal_left(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
                //right
                explosionlast.add(new Explosion_horizontal_right(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
                //top
                explosionlast.add(new Explosion_vertical_top(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical_top_last.getFxImage()));
                //down
                explosionlast.add(new Explosion_vertical_down(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical_down_last.getFxImage()));
            }
            if(Bomb.idbomb==2)
            {
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

            movingentities.add(bomb);

            for (int i = 0; i < explosion1.size(); i++) {
                movingentities.add(explosion1.get(i));
            }
            for (int i = 0; i < explosionlast.size(); i++) {
                movingentities.add(explosionlast.get(i));
            }
            if(Bomb.idbomb==2){
                for (int i = 0; i < explosion1.size(); i++) {
                    movingentities.add(explosion2.get(i));
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

            if(Bomb.idbomb==2)
            {
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
                                    if (isokBombEffect[j] !=0) nonmovingentities.get(i).setIschange(false);
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
            isokadd=true;
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
                                if (isokBombEffect[j] !=0) nonmovingentities.get(i).setIschange(false);
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
            if (movingentities.get(i) instanceof Explosion_horizontal == false
                    && movingentities.get(i) instanceof Explosion_horizontal_left == false
                    && movingentities.get(i) instanceof Explosion_horizontal_right == false
                    && movingentities.get(i) instanceof Explosion_vertical == false
                    && movingentities.get(i) instanceof Explosion_vertical_down == false
                    && movingentities.get(i) instanceof Explosion_vertical_top == false
                    && movingentities.get(i) instanceof Bomb == false) {
                if (checkcolision(dx, dy, movingentities.get(i).getX(), movingentities.get(i).getY()) == true) {
                    movingentities.get(i).setIsdead(true);
                }
            }
        }

    }
    public static void checkcolisonitem(){
        {
            for (int i = 0; i < item.size(); i++){
                if(checkcolision(myBomber.getX(),myBomber.getY(), item.get(i).getX(), item.get(i).getY())==true){
                    if(item.get(i) instanceof itemSpeedup ){
                        myBomber.setMovedistance(0.5);
                        item.get(i).setIschange(false);
                    }
                    if(item.get(i) instanceof itemBigbomb){
                        Bomb.idbomb=2;
                        item.get(i).setIschange(false);
                    }
                    if(item.get(i) instanceof itemDetonator){
                        Bomberlife=Bomberlife+1;
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

    public static void createItem(double x,double y){
        Random generator = new Random();
        int key =generator.nextInt((3));
        if(key==1) {
            item.add(new itemSpeedup(x,y,Sprite.itemSpeedup.getFxImage()));
        }
        if(key==2){
            item.add(new itemBigbomb(x,y,Sprite.itemBigbomb.getFxImage()));
        }
        if(key==3){
            item.add(new itemDetonator(x,y,Sprite.itemDetonator.getFxImage()));
        }

    }

    public static void deleteEntities() {
        if (nonmovingrerenderentities != null) {
            for (int i = 0; i < nonmovingrerenderentities.size(); i++) {
                if (nonmovingrerenderentities.get(i).getImg() == null) {
                    createItem(nonmovingrerenderentities.get(i).getX(),nonmovingrerenderentities.get(i).getY());
                    nonmovingentities.remove(nonmovingrerenderentities.get(i));
                    nonmovingrerenderentities.remove(nonmovingrerenderentities.get(i));
                }

            }
        }
        for (int i = 0; i < movingentities.size(); i++) {
            if (movingentities.get(i) instanceof Explosion_horizontal == false
                    && movingentities.get(i) instanceof Explosion_horizontal_left == false
                    && movingentities.get(i) instanceof Explosion_horizontal_right == false
                    && movingentities.get(i) instanceof Explosion_vertical == false
                    && movingentities.get(i) instanceof Explosion_vertical_down == false
                    && movingentities.get(i) instanceof Explosion_vertical_top == false
                    && movingentities.get(i) instanceof Bomb == false) {
                if (movingentities.get(i).getImg() == null) {
                    if(movingentities.get(i)==myBomber){
                        movingentities.remove(i);
                        Bomberlife=Bomberlife-1;
                        if(Bomberlife>0) myBomber= new Bomber(0,0, Sprite.player_right.getFxImage());
                    }
                    else {
                        createItem(movingentities.get(i).getX(), movingentities.get(i).getY());
                        movingentities.remove(i);
                    }
                }
            }
        }
        for(int i=0;i<item.size();i++){
            if(item.get(i).getImg()==null)
            {
                item.remove(i);
            }
        }
    }
}
