package uet.oop.bomberman.Map;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bombEffect.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public static List<Entity> nonmovingentities = new ArrayList<>();
    public static List<Entity> nonmovingrerenderentities = new ArrayList<>();
    public static List<Entity> movingentities = new ArrayList<>();
    public static ArrayList<String> area = new ArrayList<>();
    public static Bomb bomb;

    public static Explosion_horizontal explosion_horizontal1left;
    public static Explosion_horizontal explosion_horizontal1right;
    public static Explosion_horizontal explosion_horizontal2left;
    public static Explosion_horizontal explosion_horizontal2right;
    public static Explosion_horizontal_left explosion_horizontalleft;
    public static Explosion_horizontal_right explosion_horizontalright;
    public static Explosion_vertical explosion_vertical1top;
    public static Explosion_vertical explosion_vertical1down;
    public static Explosion_vertical explosion_vertical2top;
    public static Explosion_vertical explosion_vertical2down;
    public static Explosion_vertical_top explosion_verticaltop;
    public static Explosion_vertical_down explosion_verticaldown;

    public static List<Entity> explosion1=new ArrayList<>();
    public static List<Entity> explosion2 =new ArrayList<>();
    public static List<Entity> explosionlast =new ArrayList<>();

    public static boolean isokleft = false;
    public static boolean isokright = false;
    public static boolean isokup = false;
    public static boolean isokdown = false;

    public static boolean[] isokBombEffect =new boolean[4];
    public static boolean isokadd = true;

    public static int counttime = 0;
    //    public static int bomberX;
//    public static int bomberY;
    public static Bomber myBomber;

    public Map() {
        area.add("###############################");
        area.add("#      ** *    * 2 *  * * *  3#");
        area.add("# # # #*# # #*#*# # # #*#*#*# #");
        area.add("#  **     ***  *      * 2 * * #");
        area.add("# # # # # #*# # #*#*# # # # #*#");
        area.add("#*           **  *  *        1#");
        area.add("# # # # # # # # # #*# #*# # # #");
        area.add("#*  * 1    *  *               #");
        area.add("# # # # #*# # # #*#*# # # # # #");
        area.add("#*    **  *       *           #");
        area.add("# #*# # # # # # #*# # # # # # #");
        area.add("#    p      *   *  *          #");
        area.add("###############################");

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
//            movingentities.remove(explosion_horizontal1left);
//            movingentities.remove(explosion_horizontal1right);
//            movingentities.remove(explosion_horizontalleft);
//            movingentities.remove(explosion_horizontalright);
//            movingentities.remove(explosion_vertical1top);
//            movingentities.remove(explosion_vertical1down);
//            movingentities.remove(explosion_verticaltop);
//            movingentities.remove(explosion_verticaldown);
            if(explosion1 != null)
            for(int i=0;i<explosion1.size();i++)
            {
                movingentities.remove(explosion1.get(i));
            }
            if(explosionlast != null)
            for(int i=0;i<explosionlast.size();i++)
            {
                movingentities.remove(explosionlast.get(i));
            }
            explosion1=new ArrayList<>();
            explosionlast=new ArrayList<>();
            bomb = new Bomb(myBomber.getX(), myBomber.getY(), Sprite.bomb.getFxImage());
            System.out.println(Bomb.isexploded);
//            explosion_horizontal1left = new Explosion_horizontal(bomb.getX() + 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage());
//            explosion_horizontal1right = new Explosion_horizontal(bomb.getX() - 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage());
//            explosion_horizontalleft = new Explosion_horizontal_left(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage());
//            explosion_horizontalright = new Explosion_horizontal_right(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage());
//
//            explosion_vertical1top = new Explosion_vertical(bomb.getX(), bomb.getY() - 1, Sprite.explosion_vertical.getFxImage());
//            explosion_vertical1down = new Explosion_vertical(bomb.getX(), bomb.getY() + 1, Sprite.explosion_vertical.getFxImage());
//            explosion_verticaltop = new Explosion_vertical_top(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical_top_last.getFxImage());
//            explosion_verticaldown = new Explosion_vertical_down(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical_down_last.getFxImage());

            //1left
            explosion1.add(new Explosion_horizontal(bomb.getX() + 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1right
            explosion1.add(new Explosion_horizontal(bomb.getX() - 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
            //1top
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() - 1, Sprite.explosion_vertical.getFxImage()));
            //1down
            explosion1.add(new Explosion_vertical(bomb.getX(), bomb.getY() + 1, Sprite.explosion_vertical.getFxImage()));

            //left
            explosionlast.add(new Explosion_horizontal_left(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
            //right
            explosionlast.add(new Explosion_horizontal_right(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
            //top
            explosionlast.add(new Explosion_vertical_top(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical_top_last.getFxImage()));
            //down
            explosionlast.add(new Explosion_vertical_down(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical_down_last.getFxImage()));


            movingentities.add(bomb);

//            movingentities.add(explosion_horizontal1left);
//            movingentities.add(explosion_horizontal1right);
//            movingentities.add(explosion_horizontalleft);
//            movingentities.add(explosion_horizontalright);
//            movingentities.add(explosion_vertical1top);
//            movingentities.add(explosion_vertical1down);
//            movingentities.add(explosion_verticaltop);
//            movingentities.add(explosion_verticaldown);

            for(int i=0;i<explosion1.size();i++)
            {
                movingentities.add(explosion1.get(i));
            }
            for(int i=0;i<explosionlast.size();i++)
            {
                movingentities.add(explosionlast.get(i));
            }

            for (int i=0;i<4;i++){
                isokBombEffect[i] = false;
            }


            for(int i=0;i<nonmovingentities.size();i++){
                for(int j=0;j<explosion1.size();j++){
                    if(checkcolision(explosion1.get(j).getX(),explosion1.get(j).getY(),nonmovingentities.get(i).getX(),nonmovingentities.get(i).getY())==true){
                        nonmovingrerenderentities.add(nonmovingentities.get(i));
                        if (isokBombEffect[j]== false) {
                            nonmovingentities.get(i).setIschange(true);
                        }
                        isokBombEffect[j] = true;

                    }
                }
            }
            System.out.println(nonmovingrerenderentities.size());
            for (int i=0 ; i< nonmovingrerenderentities.size();i++)
            {
                System.out.println(nonmovingrerenderentities.get(i).getX()+" "+nonmovingrerenderentities.get(i).getY());
            }
                for (int i = 0; i < nonmovingentities.size(); i++) {
                    {
                        for (int j = 0; j < explosionlast.size(); j++) {
                            if (checkcolision(explosionlast.get(j).getX(), explosionlast.get(j).getY(), nonmovingentities.get(i).getX(), nonmovingentities.get(i).getY()) == true) {
                                for(int h=0 ;h < nonmovingrerenderentities.size();h++){
                                    if (nonmovingentities.get(i) == nonmovingrerenderentities.get(h))//xem co bi xac dinh trung doi tuong da co
                                            isokadd=false;
                                }
                                if(isokadd== true)
                                {
                                    nonmovingrerenderentities.add(nonmovingentities.get(i));
                                    if (isokBombEffect[j] == true) nonmovingentities.get(i).setIschange(false);
                                    if (isokBombEffect[j] == false) nonmovingentities.get(i).setIschange(true);
                                    isokBombEffect[j] = true;
                                }
                            }
                            isokadd= true;
                        }
                    }
                }



//
//            for (int i = 0; i < nonmovingentities.size() - 1; i++) {
//                if (checkcolison(explosion_horizontal1left.getX(),explosion_horizontal1left.getY(),nonmovingentities.get(i).getX(),nonmovingentities.get(i).getY())== true) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokleft == false) nonmovingentities.get(i).setIschange(true);
//                    isokleft = true;
//                }
//
//                if (nonmovingentities.get(i).getX() - 1 < explosion_horizontal1right.getX() && nonmovingentities.get(i).getX() >= explosion_horizontal1right.getX() && nonmovingentities.get(i).getY() > explosion_horizontal1right.getY() - 1 && nonmovingentities.get(i).getY() < explosion_horizontal1right.getY() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokright == false) nonmovingentities.get(i).setIschange(true);
//                    isokright = true;
//                }
//
//                if (nonmovingentities.get(i).getY() == explosion_vertical1top.getY() && nonmovingentities.get(i).getX() > explosion_vertical1top.getX() - 1 && nonmovingentities.get(i).getX() < explosion_vertical1top.getX() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokup == false) nonmovingentities.get(i).setIschange(true);
//                    isokup = true;
//                }
//
//                if (nonmovingentities.get(i).getY() == explosion_vertical1down.getY() && nonmovingentities.get(i).getX() > explosion_vertical1down.getX() - 1 && nonmovingentities.get(i).getX() < explosion_vertical1down.getX() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokdown == false) nonmovingentities.get(i).setIschange(true);
//                    isokdown = true;
//                }
//            }
//            for (int i = 0; i < nonmovingentities.size() - 1; i++) {
//                if (nonmovingentities.get(i).getX() == explosion_horizontalright.getX() && nonmovingentities.get(i).getY() > explosion_horizontalright.getY() - 1 && nonmovingentities.get(i).getY() < explosion_horizontalright.getY() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokright == true) nonmovingentities.get(i).setIschange(false);
//                    if (isokright == false) nonmovingentities.get(i).setIschange(true);
//                    isokright = true;
//                }
//
//                if (nonmovingentities.get(i).getX() == explosion_horizontalleft.getX() && nonmovingentities.get(i).getY() > explosion_horizontalleft.getY() - 1 && nonmovingentities.get(i).getY() < explosion_horizontalleft.getY() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokleft == true) nonmovingentities.get(i).setIschange(false);
//                    if (isokleft == false) nonmovingentities.get(i).setIschange(true);
//                    isokleft = true;
//                }
//
//                if (nonmovingentities.get(i).getY() == explosion_verticaltop.getY() && nonmovingentities.get(i).getX() > explosion_verticaltop.getX() - 1 && nonmovingentities.get(i).getX() < explosion_verticaltop.getX() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokup == true) nonmovingentities.get(i).setIschange(false);
//                    if (isokup == false) nonmovingentities.get(i).setIschange(true);
//                    isokup = true;
//                }
//
//                if (nonmovingentities.get(i).getY() == explosion_verticaldown.getY() && nonmovingentities.get(i).getX() > explosion_verticaldown.getX() - 1 && nonmovingentities.get(i).getX() < explosion_verticaldown.getX() + 1) {
//                    nonmovingrerenderentities.add(nonmovingentities.get(i));
//                    if (isokdown == true) nonmovingentities.get(i).setIschange(false);
//                    if (isokdown == false) nonmovingentities.get(i).setIschange(true);
//                    isokdown = true;
//                }
//            }
        }
        System.out.println(nonmovingrerenderentities.size());
        for (int i=0 ; i< nonmovingrerenderentities.size();i++)
        {
            System.out.println(nonmovingrerenderentities.get(i).getX()+" "+nonmovingrerenderentities.get(i).getY());
        }
    }

    public static void checkdeadbybomb(double dx, double dy){
        for(int i=0;i<movingentities.size();i++){

        }

    }

    public static boolean checkcolision(double x11,double y12, double x21, double y22){
        double x1 = (double) Math.round(x11 * 100) / 100;
        double y1 = (double) Math.round(y12 * 100) / 100;
        double x2 = (double) Math.round(x21 * 100) / 100;
        double y2 = (double) Math.round(y22 * 100) / 100;
        if(x2-1<x1 && x2>=x1 && y2+1 >y1 &&y2 <= y1) return true;
        else if (x2-1<x1 && x2>=x1 && y2 -1 <y1 && y2 >= y1 ) return  true;
        else if (x2+1 >x1 && x2 <=x1 && y2+1 >y1 &&y2 <= y1)return  true;
        else if(x2+1 >x1 && x2 <=x1 && y2 -1 <y1 && y2 >= y1) return true;
        else return false;
    }

    public static void deleteNonmovingentities() {
        if (nonmovingrerenderentities != null) {
            for (int i = 0; i < nonmovingrerenderentities.size(); i++) {
                if (nonmovingrerenderentities.get(i).getImg() == null) {
                    nonmovingentities.remove(nonmovingrerenderentities.get(i));
                    nonmovingrerenderentities.remove(nonmovingrerenderentities.get(i));
                }

            }
        }

    }
}
