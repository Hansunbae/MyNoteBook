package dragonR;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ShootingGame extends JFrame {

	private Image bufferImage;
	private Graphics screenGraphic;

	// private Image mainScreen = new
	// ImageIcon("src/images/main_screen.png").getImage();
	// private Image loadingScreen = new
	// ImageIcon("src/images/loading_screen.png").getImage();
	private Image gameScreen = new ImageIcon("images/GameBackground1_1.png").getImage(); // 게임화면 이미지 넣는 곳

	private boolean isMainScreen, isLoadingScreen, isGameScreen; // 화면 관련 불리언 플래그 3개

	private Game game = new Game();

	public ShootingGame() {
		initData();
		setInitLayout();
		addEventListener();
		gameStart();

	}

	private void initData() {
		setTitle("Shooting Game");
		// setUndecorated(true);
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void setInitLayout() {
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(null);
	}

	private void addEventListener() {
		// isMainScreen = true;
		// isLoadingScreen = false;
		// isGameScreen = false;

		// 실험 중 지워주길
		isGameScreen = true;

		addKeyListener(new KeyListener());
	}

	
	// 아래 주석 부분 게임 딜레이 있다가 시작하는 부분
//	// 엔터 눌러서 게임 시작
//	private void gameStart() {
//		// isMainScreen = false;
//		// isLoadingScreen = true;
//
//		// 밑에 두개 모른다
//		Timer loadingTimer = new Timer();
//		TimerTask loadingTask = new TimerTask() {
//			@Override
//			public void run() {
//
//				// isLoadingScreen = false;
//				// 여기서 게임 스크린 true되면서 게임 시작
//				isGameScreen = true;
//				game.start();
//			}
//		};
//		loadingTimer.schedule(loadingTask, 3000);
//	}

	

	// 엔터 눌러서 게임 시작
	private void gameStart() {
				isGameScreen = true;
				game.start();
	}
	
	// 이부분 모름
	public void paint(Graphics g) {
		bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		screenGraphic = bufferImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(bufferImage, 0, 0, null);
	}
	
	

	// 실행되면 화면 그려주는 부분
	public void screenDraw(Graphics g) {
		// true일 때 실행
//        if (isMainScreen) {
//            g.drawImage(mainScreen, 0, 0, null);
//        }
		// true일 때 실행
//        if (isLoadingScreen) {
//            g.drawImage(loadingScreen, 0, 0, null);
//        }
		// true일 때 실행
		if (isGameScreen) {
			g.drawImage(gameScreen, 0, 0, null);
			game.gameDraw(g);
		}

		this.repaint();
	}

	// 키 입력 받아 처리하는 내부 클래스
	class KeyListener extends KeyAdapter {

		// 키 누를 때 true로 만들어 실행
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				game.setUp(true);
				break;
			case KeyEvent.VK_DOWN:
				game.setDown(true);
				break;
			case KeyEvent.VK_LEFT:
				game.setLeft(true);
				break;
			case KeyEvent.VK_RIGHT:
				game.setRight(true);
				break;
			// 리셋 키
			case KeyEvent.VK_R:
				if (game.isOver())
					game.reset();
				break;
			case KeyEvent.VK_SPACE:
				game.setShooting(true);
				break;

			// 메인 스크린이 true를 통해 작동하고 있고 이 때 엔터 누르면 게임스타트 메서드 호출
//                case KeyEvent.VK_ENTER:
//                    if (isMainScreen) gameStart();
//                    break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			}
		}

		// 키 뗄 떄 false로 만듬
		public void keyReleased(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				game.setUp(false);
				break;
			case KeyEvent.VK_DOWN:
				game.setDown(false);
				break;
			case KeyEvent.VK_LEFT:
				game.setLeft(false);
				break;
			case KeyEvent.VK_RIGHT:
				game.setRight(false);
				break;
			case KeyEvent.VK_SPACE:
				game.setShooting(false);
				break;
			}
		}
	}
}
