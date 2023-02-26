package Background;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// 첫 화면 

public class StartWindow extends JFrame {

	ImageIcon imageicon;
	Image image;
	MyPanel panel;
	JButton button;

	public StartWindow() {
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {

		setTitle("Start");
		setSize(600, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		button = new JButton(new ImageIcon("images/GS_button.png"));

		// 버튼 테두리 없애기
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		button.setFocusPainted(false);

		imageicon = new ImageIcon("images/GameBackground1.png");
		image = imageicon.getImage();
		panel = new MyPanel();

	}

	public void setInitLayout() {

		setResizable(false);
		setVisible(true);
		panel.setLayout(null);
		add(panel);
		panel.add(button);
		button.setSize(300, 180);
		button.setLocation(130, 600);

	}

	public void addEventListener() {

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 두번째 창
				new SelectCharacter();
				setVisible(false);

			}
		});

	}

	// JPanel 내부 클래스 (배경 이미지)
	class MyPanel extends JPanel {

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			g.setFont(new Font("SB 어그로 BOLD", Font.PLAIN, 100));
			g.setColor(Color.yellow);
			g.drawString("DRAGON", 30, 150);
			g.drawString("FLIGHT", 80, 300);
		}
	}

	public static void main(String[] args) {
		new StartWindow();
	}

}
