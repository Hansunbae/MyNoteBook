package dragonF;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon player;

	// 플레이어의 상태
	private boolean left;
	private boolean right;

	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	// 플레이어의 속도
	private final int SPEED = 2;

	public Player(Frame mContext) {
		initDate();
		setInitLayout();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ImageIcon getAirplane() {
		return player;
	}

	public void setAirplane(ImageIcon player) {
		this.player = player;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}


	private void initDate() {
		player = new ImageIcon("images/air.png");
		left = false;
		right = false;
		leftWallCrash = false;
		rightWallCrash = false;
	}

	private void setInitLayout() {
		x = 275;
		y = 800;		
		setSize(50, 50);
		setLocation(x, y);
		setIcon(player);
	}

	
	@Override
	public void left() {
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(player);
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();
	}

	@Override
	public void right() {
		right = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					setIcon(player);
					x = x + SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();
	}

}
