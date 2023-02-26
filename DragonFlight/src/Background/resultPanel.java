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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Background.StartWindow.MyPanel;

public class resultPanel extends JFrame {

	ImageIcon imageicon;
	Image image;
	JPanel panel;
	JButton startbutton;
	JButton closebutton;

	public resultPanel() {
		initData();
		setInitLayout();
		addEventListener();
	}

	public void initData() {

		setTitle("GameOver");
		setSize(600, 900);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		startbutton = new JButton(new ImageIcon("images/Re_button.png"));
		closebutton = new JButton(new ImageIcon("images/Close_button.png"));

		// 버튼 테두리 없애기
		startbutton.setBorderPainted(false);
		startbutton.setContentAreaFilled(false);
		startbutton.setFocusPainted(false);

		closebutton.setBorderPainted(false);
		closebutton.setContentAreaFilled(false);
		closebutton.setFocusPainted(false);
		// 배경이미지
		imageicon = new ImageIcon("images/GameBackground1.png");
		image = imageicon.getImage();
		panel = new MyPanel();

	}

	public void setInitLayout() {

		setResizable(false);
		setVisible(true);
		panel.setLayout(null);
		add(panel);
		panel.add(startbutton);
		panel.add(closebutton);
		startbutton.setSize(200, 70);
		closebutton.setSize(200, 70);
		startbutton.setLocation(50, 700);
		closebutton.setLocation(300, 700);

	}

	public void addEventListener() {

		startbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 캐릭터 선택창으로
				new SelectCharacter();
				setVisible(false);

			}
		});

		closebutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 게임 종료
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
			g.setColor(Color.white);
			g.drawString("Game", 100, 120);
			g.drawString("Over", 120, 220);
		}
	}

	public static void main(String[] args) {
		new resultPanel();
	}

}
