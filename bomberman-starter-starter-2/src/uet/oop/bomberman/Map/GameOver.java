package uet.oop.bomberman.Map;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.io.IOException;

public class GameOver {
    int HEIGHT = BombermanGame.HEIGHT;
    int WIDTH = BombermanGame.WIDTH;
    private Scene scene2;

    public GameOver(Stage window){
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
        window.setScene(scene2);
        scene2.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                switch (event.getCode()) {
                    case Y:
                        BombermanGame.keyrender--;
                        try {
                            System.out.println("tao map 1 gameover");
                            Map.map = new Map("res/levels/Level1.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
//                        window.setTitle("hi");
                        break;

                    case N:
                        System.exit(0);
                        break;
                }
            }
        });
    }
}
