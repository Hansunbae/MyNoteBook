package dragonF;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Frame extends JFrame {

	private Frame mContext = this;
	private JLabel backgroundMap;
	private Player player;
	private Enemy enemy;

	List<Enemy> enemyList = new ArrayList<>();

	int j = 0;

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public Frame() {
		initDate();
		setInitLayout();
		addEventListener();
		enemyCreate();
		new Thread(new BackgroundPlayerService(player)).start();

	}

	private void initDate() {
		backgroundMap = new JLabel(new ImageIcon("images/background3.png"));
		setTitle("Galaga");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(600, 900);
		player = new Player(mContext);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(player);
	}

	// enemyList();

	private void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (player.isLeft() == false && player.isLeftWallCrash() == false) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (player.isRight() == false && player.isRightWallCrash() == false) {
						player.right();
					}
					break;

				case KeyEvent.VK_SPACE: // 스페이스 누르면 총알 객체 생성
					Bullet bullet = new Bullet(mContext);
					add(bullet); // frame에서 붙여야 된다.
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					// 위버튼을떄면 멈춰야해
					player.setLeft(false);
					break;

				case KeyEvent.VK_RIGHT:
					// 아래 버튼을 때면 멈춰야해
					player.setRight(false);
					break;
				}
			}
		});
	}

	public void enemyCreate() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < 5; i++) {
						enemyList.add(new Enemy(50 + (i * 100), 50));
						add(enemyList.get(j));
						j++;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new Frame();
	}
}
