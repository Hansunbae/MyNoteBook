package dragonF;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("images/background3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			// 기준 왼쪽
			Color leftColor = new Color(image.getRGB(player.getX() + 10, player.getY()));
			// 기준 오른쪽
			Color rightColor = new Color(image.getRGB(player.getX() + 60, player.getY()));
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				player.setLeft(false);
				player.setLeftWallCrash(true);
				//System.out.println("왼쪽 빨간벽이에요");// 확인용
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				player.setRight(false);
				player.setRightWallCrash(true);
				System.out.println("오른쪽 빨간벽이에요");// 확인용 지울거임
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			try {
				Thread.sleep(8);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
