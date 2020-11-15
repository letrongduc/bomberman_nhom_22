package uet.oop.bomberman.Map;

import com.sun.jmx.snmp.agent.SnmpGenericObjectServer;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Map {
    public static List<Entity> nonmovingentities = new ArrayList<>();
    public static List<Entity> movingentities = new ArrayList<>();
    public static ArrayList<String> area = new ArrayList<>();

//    public static int bomberX;
//    public static int bomberY;
    public static Bomber myBomber;

    public Map() {
        area.add("p    2** * *1 *   *  * * *   ");
        area.add(" # # #*# # #*#*# # # #*#*#*# ");
        area.add(" # # # # #*# # #*#*# # # # #*");
        area.add("  x*     ***  *  1   *   * * ");
        area.add("          x **  *  *   1     ");
        area.add(" # # # # # # # # #*# #*# # # ");
        area.add("*  *      *  *      *        ");
        area.add(" # # # #*# # # #*#*# # # # # ");
        area.add("*    **  *       *           ");
        area.add(" #*# # # # # # #*# # # # # # ");
        area.add("           *   *  *          ");

        for (int i = 0; i < area.size() ; i++) {
            for (int j = 0; j < area.get(1).length() ; j++) {
                if (area.get(i).charAt(j) == '#') {
                    Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == '*') {
                    Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == 'p') {
//                    bomberX = i;
//                    bomberY = j;
                    myBomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                    movingentities.add(myBomber);
                } else if (area.get(i).charAt(j) == '1') {
                    Entity object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '2') {
                    Entity object = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                    movingentities.add(object);
                }
            }
        }
    }
    public static boolean checkcollision(double dx,double dy ,String keymove,double widthentity,double distance){
            double x=(double) Math.round(dx * 100) / 100;
            double y=(double) Math.round(dy * 100) / 100;
            for(int i =0;i<nonmovingentities.size();i++){
                if(keymove=="Right"){
                    if((nonmovingentities.get(i).getY()>y-1 && nonmovingentities.get(i).getY()<y&& nonmovingentities.get(i).getX()>x) || (nonmovingentities.get(i).getY()>y && nonmovingentities.get(i).getY()<y+1&& nonmovingentities.get(i).getX()>x)||(nonmovingentities.get(i).getY()==y && nonmovingentities.get(i).getX()>x)) {
                        if (x+1 +distance > nonmovingentities.get(i).getX()) {
                            return false;
                        }
                    }
                }
                if(keymove=="Left"){
                    if((nonmovingentities.get(i).getY()>y-1 && nonmovingentities.get(i).getY()<y&& nonmovingentities.get(i).getX()<x) || (nonmovingentities.get(i).getY()>y && nonmovingentities.get(i).getY()<y+1&& nonmovingentities.get(i).getX()<x)||(nonmovingentities.get(i).getY()==y && nonmovingentities.get(i).getX()<x)) {
                        if (x-1- distance < nonmovingentities.get(i).getX()) {
                            return false;
                        }
                    }
                }
                if(keymove=="Up"){
                    if ((nonmovingentities.get(i).getX()>x-1&&nonmovingentities.get(i).getX()<x&&nonmovingentities.get(i).getY()<y)|| (nonmovingentities.get(i).getX()<x+1 && nonmovingentities.get(i).getX()>x&&nonmovingentities.get(i).getY()<y)||(nonmovingentities.get(i).getX()==x &&nonmovingentities.get(i).getY()<y)) {
                        if (y - 1 - distance < nonmovingentities.get(i).getY()) return false;
                    }
                }
                if(keymove=="Down"){
                    if ((nonmovingentities.get(i).getX()>x-1&&nonmovingentities.get(i).getX()<x&& nonmovingentities.get(i).getY()>y)|| (nonmovingentities.get(i).getX()<x+1 && nonmovingentities.get(i).getX()>x&& nonmovingentities.get(i).getY()>y)||(nonmovingentities.get(i).getX()==x && nonmovingentities.get(i).getY()>y)) {
                        if (y+1 + distance > nonmovingentities.get(i).getY()) return false;
                    }

                }
            }
        System.out.println();
        return true;
    }

    public static List directionposible(double dx, double dy) {
        double x=(double) Math.round(dx * 100) / 100;
        double y=(double) Math.round(dy * 100) / 100;
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

    public static Boolean checkSpace(double l1, double l2, double index){
        if((l1 < l2 && l1 < index && index < l2) || (l1 > l2 && l1 > index && index > l2)){
            return true;
        }
        return false;
    }

    public static Boolean checkLocalVer(double x, double y){
        for (int i = 0; i < nonmovingentities.size(); i++) {
            double noMoveX = nonmovingentities.get(i).getX();
            double noMoveY = nonmovingentities.get(i).getY();
            double bomberY = Map.myBomber.getY();
            if(noMoveX == x && checkSpace(bomberY, y, noMoveY) == true){
//                System.out.println("chan doc");
//                System.out.println(nonmovingentities.get(i).getX() + " " + nonmovingentities.get(i).getY());
                return false;
            }
        }
//        System.out.println("thoang doc");
        return true;
    }

    public static Boolean checkLocalHor(double x, double y){
        for (int i = 0; i < nonmovingentities.size(); i++) {
            double noMoveX = nonmovingentities.get(i).getX();
            double noMoveY = nonmovingentities.get(i).getY();
            double bomberX = Map.myBomber.getX();
            if(noMoveY == y && checkSpace(bomberX, x, noMoveX) == true){
//                System.out.println("chan ngang");
//                System.out.println(nonmovingentities.get(i).getX() + " " + nonmovingentities.get(i).getY());
                return false;
            }
        }
//        System.out.println("thoang ngang");
        return true;
    }
}
