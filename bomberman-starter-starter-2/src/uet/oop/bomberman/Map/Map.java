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

    public static int bomberX;
    public static int bomberY;

    public Map() {
        area.add("###############################");
        area.add("#p     ** * *1 * 2 *  * * *   #");
        area.add("# # # #*# # #*#*# # # #*#*#*# #");
        area.add("# # # # # #*# # #*#*# # # # #*#");
        area.add("#  x*     ***  *  1   * 2 * * #");
        area.add("#f         x **  *  *   1     #");
        area.add("# # # # # # # # # #*# #*# # # #");
        area.add("#*  *      *  *      *        #");
        area.add("# # # # #*# # # #*#*# # # # # #");
        area.add("#*    **  *       *           #");
        area.add("# #*# # # # # # #*# # # # # # #");
        area.add("#           *   *  *          #");
        area.add("###############################");

        for (int i = 1; i < area.size() - 1; i++) {
            for (int j = 1; j < area.get(1).length() - 1; j++) {
                if (area.get(i).charAt(j) == '#') {
                    Entity object = new Wall(j, i, Sprite.wall.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == '*') {
                    Entity object = new Brick(j, i, Sprite.brick.getFxImage());
                    nonmovingentities.add(object);
                } else if (area.get(i).charAt(j) == 'p') {
//                    Entity object = new Bomber(j, i, Sprite.player_right.getFxImage());
//                    movingentities.add(object);
                    bomberX = i;
                    bomberY = j;
                } else if (area.get(i).charAt(j) == '1') {
                    Entity object = new Balloom(j, i, Sprite.balloom_left1.getFxImage());
                    movingentities.add(object);
                } else if (area.get(i).charAt(j) == '2') {
                    Entity object = new Minvo(j, i, Sprite.minvo_left1.getFxImage());
                    movingentities.add(object);
                }
            }
        }
    }

    public static boolean checkcollision(double x, double y, String keymove) {
        System.out.println("ok2");
        for (int i = 0; i < nonmovingentities.size(); i++) {
            if (keymove == "Right") {
                System.out.println("ok1 " + i + "," + nonmovingentities.size());
                if ((nonmovingentities.get(i).getY() > y - 1 && nonmovingentities.get(i).getY() < y) || (nonmovingentities.get(i).getY() > y && nonmovingentities.get(i).getY() < y + 1) || (nonmovingentities.get(i).getY() == y && nonmovingentities.get(i).getX() > x)) {
                    if (x + 0.5 + 0.3 >= nonmovingentities.get(i).getX()) {
                        System.out.println(nonmovingentities.get(i).getX() + "," + nonmovingentities.get(i).getY());
                        return false;
                    }
                }
            }
            if (keymove == "Left") {
                if ((nonmovingentities.get(i).getY() > y - 1 && nonmovingentities.get(i).getY() < y) || (nonmovingentities.get(i).getY() > y && nonmovingentities.get(i).getY() < y + 1) || nonmovingentities.get(i).getY() == y) {
                    if (x - 0.3 < nonmovingentities.get(i).getX()) return false;
                }
            }
            if (keymove == "Up") {
                if ((nonmovingentities.get(i).getX() > x - 1 && nonmovingentities.get(i).getX() < x) || (nonmovingentities.get(i).getX() < x + 1 && nonmovingentities.get(i).getX() > x) || nonmovingentities.get(i).getX() == x) {
                    if (y - 0.3 < nonmovingentities.get(i).getY()) return false;
                }
            }
            if (keymove == "Down") {
                if ((nonmovingentities.get(i).getX() > x - 1 && nonmovingentities.get(i).getX() < x) || (nonmovingentities.get(i).getX() < x + 1 && nonmovingentities.get(i).getX() > x) || nonmovingentities.get(i).getX() == x) {
                    if (y + 1 + 0.3 > nonmovingentities.get(i).getY()) return false;

                }
            }
        }
        System.out.println();
        return true;
    }

    public static List directionposible(double x, double y) {
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

}
