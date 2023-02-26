package dragonFlite;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
    Image image = new ImageIcon("src/images/pinkE.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int hp = 10;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
         this.y += 7;
    }
}
