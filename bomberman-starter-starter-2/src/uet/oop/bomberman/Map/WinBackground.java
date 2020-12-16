package uet.oop.bomberman.Map;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.media.mediaPlayer;

public class WinBackground {
    public WinBackground(){
        mediaPlayer.mediaWinPlayer.play();
        int HEIGHT = BombermanGame.HEIGHT;
        int WIDTH = BombermanGame.WIDTH;
        // tao scene menu
        Image img1 = new Image("file:winBackground.jpg");
        ImageView imgv1 = new ImageView(img1);
        imgv1.setFitHeight(Sprite.SCALED_SIZE * HEIGHT-2);
        imgv1.setFitWidth(Sprite.SCALED_SIZE * WIDTH-2);

        Group layout = new Group();
        layout.getChildren().addAll(imgv1);

        Scene scene1 = new Scene(layout, Sprite.SCALED_SIZE * WIDTH-2, Sprite.SCALED_SIZE * HEIGHT-2);
        BombermanGame.window.setScene(scene1);
    }
}
