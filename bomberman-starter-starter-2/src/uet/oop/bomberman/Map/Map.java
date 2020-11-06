package uet.oop.bomberman.Map;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class Map {
    public static List<Entity> entities = new ArrayList<>();
    public static ArrayList<String> area = new ArrayList<>();

    public Map(){
        area.add("###############################");
        area.add("#p     ** *  1 * 2 *  * * *   #");
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

        for (int i=1;i<area.size()-1;i++)
        {
            for (int j=1;j< area.get(1).length()-1;j++)
            {
                if (area.get(i).charAt(j)=='#')
                {
                    Entity object = new Wall(i,j,Sprite.wall.getFxImage());
                    entities.add(object);
                }
                else if (area.get(i).charAt(j)=='*')
                {
                    Entity object = new Brick(i,j,Sprite.brick.getFxImage());
                    entities.add(object);
                }
                else if (area.get(i).charAt(j)=='p')
                {
                    Entity object = new Bomber(i,j,Sprite.player_right.getFxImage());
                    entities.add(object);
                }
                else if (area.get(i).charAt(j)=='1')
                {
                    Entity object = new Balloom(i,j,Sprite.balloom_left1.getFxImage());
                    entities.add(object);
                }
                else if (area.get(i).charAt(j)=='2')
                {
                    Entity object = new Minvo(i,j,Sprite.minvo_left1.getFxImage());
                    entities.add(object);
                }
//                else {
//                    Entity object = new Grass(i,j,Sprite.grass.getFxImage());
//                    entities.add(object);
//                }

            }
        }
    }
}
