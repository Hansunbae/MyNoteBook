package dragonFlite.FrameOff;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PlayerAttack {
    Image image = new ImageIcon("src/images/Gun.png").getImage();
    int x, y;
    int width = image.getWidth(null);
    int height = image.getHeight(null);
    int attack = 4; // 원래 5였는데 많이 때리는 걸로 바꾼다고 4으로 함

    public PlayerAttack(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void fire() {
        this.y -= 15;
    }
}
