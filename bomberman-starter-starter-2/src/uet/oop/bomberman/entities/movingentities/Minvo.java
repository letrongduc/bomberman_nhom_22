package uet.oop.bomberman.entities.movingentities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Map.Map;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import java.util.*;

public class Minvo extends movingEntity {
    private List<Image> imgMoveLeft = new ArrayList<>();
    private List<Image> imgMoveRight = new ArrayList<>();
    private List<Image> imgMoveUp = new ArrayList<>();
    private List<Image> imgMoveDown = new ArrayList<>();
    private List<Image> imgDead = new ArrayList<>();

    private class toado {
        int x_td;
        int y_td;

        public toado(int x, int y) {
            x_td = x;
            y_td = y;
        }

        public toado() {
            x_td = 0;
            y_td = 0;
        }

        public boolean equalToado(toado other) {
            if (x_td == other.x_td && y_td == other.y_td) {
                return true;
            }
            return false;
        }
    }

    private int ngang = 31;
    private int doc = 13;

    public Minvo(int x, int y, Image img) {
        super(x, y, img);

        imgMoveLeft.add(Sprite.minvo_left1.getFxImage());
        imgMoveLeft.add(Sprite.minvo_left2.getFxImage());
        imgMoveLeft.add(Sprite.minvo_left3.getFxImage());

        imgMoveRight.add(Sprite.minvo_right1.getFxImage());
        imgMoveRight.add(Sprite.minvo_right2.getFxImage());
        imgMoveRight.add(Sprite.minvo_right3.getFxImage());

        imgMoveUp.add(Sprite.minvo_left1.getFxImage());
        imgMoveUp.add(Sprite.minvo_left2.getFxImage());
        imgMoveUp.add(Sprite.minvo_left3.getFxImage());

        imgMoveDown.add(Sprite.minvo_right1.getFxImage());
        imgMoveDown.add(Sprite.minvo_right2.getFxImage());
        imgMoveDown.add(Sprite.minvo_right3.getFxImage());

        imgDead.add(Sprite.minvo_dead.getFxImage());
        keymove = "";
        MovingLoopindex = 0;
    }

    private Image minvoDead() {
        return imgDead.get(0);
    }

    private String getkeymoving() {
        List<String> d = Map.directionposible(x, y);
        Random generator = new Random();
        int key = generator.nextInt(d.size());
        return d.get(key);
    }

    private Image minvoMoveLeft() {
        if (x > 0 && Map.checkcollisionmoving(x, y, "Left", 1, 0.1))
            x = (double) Math.round((x - 0.1) * 10) / 10;
        if (img == imgMoveLeft.get(0)) return imgMoveLeft.get(1);
        else if (img == imgMoveLeft.get(1)) return imgMoveLeft.get(2);
        else if (img == imgMoveLeft.get(2)) return imgMoveLeft.get(0);
        else return imgMoveLeft.get(0);
    }

    private Image minvoMoveRight() {
        if (x < BombermanGame.WIDTH - 1 && Map.checkcollisionmoving(x, y, "Right", 1, 0.1))
            x = (double) Math.round((x + 0.1) * 10) / 10;
        if (img == imgMoveRight.get(0)) return imgMoveRight.get(1);
        else if (img == imgMoveRight.get(1)) return imgMoveRight.get(2);
        else if (img == imgMoveRight.get(2)) return imgMoveRight.get(0);
        else return imgMoveRight.get(0);
    }

    private Image minvoMoveUp() {
        if (y > 0 && Map.checkcollisionmoving(x, y, "Up", 1, 0.1))
            y = (double) Math.round((y - 0.1) * 10) / 10;
        if (img == imgMoveUp.get(0)) return imgMoveUp.get(1);
        else if (img == imgMoveUp.get(1)) return imgMoveUp.get(2);
        else if (img == imgMoveUp.get(2)) return imgMoveUp.get(0);
        else return imgMoveUp.get(0);
    }

    private Image minvoMoveDown() {
        if (y < BombermanGame.HEIGHT - 1 && Map.checkcollisionmoving(x, y, "Down", 1, 0.1))
            y = (double) Math.round((y + 0.1) * 10) / 10;
        if (img == imgMoveDown.get(0)) return imgMoveDown.get(1);
        else if (img == imgMoveDown.get(1)) return imgMoveDown.get(2);
        else if (img == imgMoveDown.get(2)) return imgMoveDown.get(0);
        else return imgMoveDown.get(0);
    }

    private ArrayList<toado> truyhoi(ArrayList<toado> road, HashMap<toado, ArrayList<toado>> map, toado E, toado S) {
        toado temp = E;
        for (toado key : map.keySet()) {
            ArrayList<toado> ar = map.get(key);
            for (int i = 0; i < ar.size(); i++) {
                if (ar.get(i).equalToado(E)) {
                    temp = key;
                    road.add(temp);
                    break;
                }
            }
        }
        E = temp;
        if (S.equalToado(E) == false) {
            road = truyhoi(road, map, E, S);
        }
        return road;
    }

    private void MovingLoop() {
        if (MovingLoopindex == 10) MovingLoopindex = 0;
        if (MovingLoopindex == 0) {
            boolean flag = false;
            List<toado> queue = new ArrayList<>();
            List<toado> pre = new ArrayList<>();
            ArrayList<toado> road = new ArrayList<toado>();
            HashMap<toado, ArrayList<toado>> map = new HashMap<toado, ArrayList<toado>>();
            toado S = new toado((int) x, (int) y);
            toado St = S;
            toado E = new toado((int) Map.myBomber.getX(), (int) Map.myBomber.getY());
            int[][] a = Map.duyetMap();
            int[][] check = new int[doc][ngang];

            int[] hx = {0, 1, 0, -1};
            int[] hy = {1, 0, -1, 0};

            //BFS
            queue.add(S);
            check[S.y_td][S.x_td] = 1;

            while (!queue.isEmpty()) {
                S = queue.get(0);
                pre.add(S);
                queue.remove(S);
                ArrayList<toado> v = new ArrayList<toado>();
                for (int i = 0; i < 4; i++) {
                    if ((a[(S.y_td + hy[i])][(S.x_td + hx[i])] == 0)
                            && (S.x_td + hx[i] >= 0)
                            && (S.x_td + hx[i] <= ngang - 1)
                            && (S.y_td + hy[i] >= 0)
                            && (S.y_td + hy[i] <= doc - 1)
                            && (check[(S.y_td + hy[i])][(S.x_td + hx[i])] == 0)) {
                        toado td = new toado(S.x_td + hx[i], S.y_td + hy[i]);
                        check[(S.y_td + hy[i])][(S.x_td + hx[i])] = 1;
                        pre.add(td);
                        queue.add(td);
                        v.add(td);
                    }
                }
                map.put(S, v);
            }
            for (int i = 0; i < pre.size(); i++) {
                if (pre.get(i).equalToado(E) == true) {
                    flag = true;
                    break;
                }
            }
            if (flag == false || E.equalToado(St) == true) {
                keymove = getkeymoving();
            } else {
                road.add(E);
                road = truyhoi(road, map, E, St);
                if (road.get(road.size() - 2).y_td - y == -1) {
                    keymove = "Up";
                }
                if (road.get(road.size() - 2).y_td - y == 1) {
                    keymove = "Down";
                }
                if (road.get(road.size() - 2).x_td - x == 1) {
                    keymove = "Right";
                }
                if (road.get(road.size() - 2).x_td - x == -1) {
                    keymove = "Left";
                }
            }
        }

        //loop
        MovingLoopindex = MovingLoopindex + 1;

        if (keymove == "Left") img = minvoMoveLeft();
        if (keymove == "Right") img = minvoMoveRight();
        if (keymove == "Up") img = minvoMoveUp();
        if (keymove == "Down") img = minvoMoveDown();

    }

    @Override
    public void update() {
        if (isdead == false) {
            MovingLoop();
        } else {
            countisdead = countisdead + 1;
            if (countisdead <= 12) img = minvoDead();
            else img = null;
        }
    }
}
