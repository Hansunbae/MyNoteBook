package Background;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FallingBack extends JPanel implements ActionListener {

	private BufferedImage backgroundImage;
	private int backgroundY = 0;
	private int backgroundSpeed = 5;

	public FallingBack() {
		try {
			backgroundImage = ImageIO.read(new File("images/GameBackground1.png"));
		} catch (IOException e) {
			System.out.println("이미지경로확인");
		}
		javax.swing.Timer timer = new javax.swing.Timer(30, this);
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, -backgroundY, null);
		g.drawImage(backgroundImage, 0, -backgroundY - backgroundImage.getHeight(), null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		backgroundY -= backgroundSpeed;
		if (backgroundY <= -backgroundImage.getHeight()) {
			backgroundY = 0;
		}
		repaint();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new FallingBack());
		frame.setSize(600, 900);
		frame.setVisible(true);
	}
}