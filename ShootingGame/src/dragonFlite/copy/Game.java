package dragonFlite.copy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	private int delay = 20;
	private long pretime;
	private int count;
	private int score;

	private Image player = new ImageIcon("src/images/player1.png").getImage();

	private int playerX, playerY;
	private int playerWidth = player.getWidth(null);
	private int playerHeight = player.getHeight(null);
	private int playerSpeed = 10;

	private boolean up, down, left, right, shooting;
	private boolean isOver;

	private ArrayList<PlayerAttack> playerAttackList = new ArrayList<PlayerAttack>();
	private ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

	private PlayerAttack playerAttack;
	private Enemy enemy;
	private Enemy enemy1;
	private Enemy enemy2;
	private Enemy enemy3;
	private Enemy enemy4;
	private Enemy enemy5;

	@Override
	public void run() {

		reset();
		while (true) {
			while (!isOver) {
				pretime = System.currentTimeMillis();
				if (System.currentTimeMillis() - pretime < delay) {
					try {
						Thread.sleep(delay - System.currentTimeMillis() + pretime);
						keyProcess();
						playerAttackProcess();
						enemyAppearProcess(); // 적 생성 메서드 호출
						enemyMoveProcess(); // 적 움직이는 메서드 호출

						count++;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void reset() {
		isOver = false;
		count = 0;
		score = 0;
		playerX = 10;
		playerY = (Main.SCREEN_HEIGHT - playerHeight) / 2;

		playerAttackList.clear();
		enemyList.clear();

	}
	
	
	// 대략 총알 발사위치 - 잘 모르겠음 
	private void keyProcess() {
		if (up && playerY - playerSpeed > 0)
			playerY -= playerSpeed;
		if (down && playerY + playerHeight + playerSpeed < Main.SCREEN_HEIGHT)
			playerY += playerSpeed;
		if (left && playerX - playerSpeed > 0)
			playerX -= playerSpeed;
		if (right && playerX + playerWidth + playerSpeed < Main.SCREEN_WIDTH)
			playerX += playerSpeed;
		if (shooting && count % 15 == 0) {
			playerAttack = new PlayerAttack(playerX + 50, playerY);
			playerAttackList.add(playerAttack);
		}
	}

	// 총알 피격 판정 메서드
	private void playerAttackProcess() {
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			playerAttack.fire();

			for (int j = 0; j < enemyList.size(); j++) {
				enemy = enemyList.get(j);
				if (playerAttack.x > enemy.x && playerAttack.x < enemy.x + enemy.width && playerAttack.y > enemy.y
						&& playerAttack.y < enemy.y + enemy.height) {
					enemy.hp -= playerAttack.attack;
					playerAttackList.remove(playerAttack);
				}
				if (enemy.hp <= 0) {

					enemyList.remove(enemy);
					score += 1000;
				}
			}
		}
	}

	private void enemyAppearProcess() {
		if (count % 80 == 0) {
			// enemy = new Enemy(1120, (int)(Math.random()*621));
			
			enemy1 = new Enemy(20, 100);
			enemy2 = new Enemy(135, 100);
			enemy3 = new Enemy(250, 100);
			enemy4 = new Enemy(365, 100);
			enemy5 = new Enemy(480, 100);
			
			enemyList.add(enemy1);
			enemyList.add(enemy2);
			enemyList.add(enemy3);
			enemyList.add(enemy4);
			enemyList.add(enemy5);
			for (int i = 0; i < enemyList.size(); i++) {
			}
		}
	}

	private void enemyMoveProcess() {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
		
			enemy.move();
		}
	}

	public void gameDraw(Graphics g) {
		playerDraw(g);
		enemyDraw(g);
		infoDraw(g);
	}

	public void infoDraw(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString("SCORE : " + score, 40, 80);
		if (isOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 80));
			g.drawString("Press R to restart", 295, 380);
		}
	}

	public void playerDraw(Graphics g) {
		g.drawImage(player, playerX, playerY, null);
		g.setColor(Color.GREEN);
		for (int i = 0; i < playerAttackList.size(); i++) {
			playerAttack = playerAttackList.get(i);
			g.drawImage(playerAttack.image, playerAttack.x, playerAttack.y, null);
		}
	}

	public void enemyDraw(Graphics g) {
		for (int i = 0; i < enemyList.size(); i++) {
			enemy = enemyList.get(i);
			g.drawImage(enemy.image, enemy.x, enemy.y, null);
			g.setColor(Color.yellow);
			g.fillRect(enemy.x + 1, enemy.y - 40, enemy.hp * 15, 20);
		}

	}

	public boolean isOver() {
		return isOver;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setShooting(boolean shooting) {
		this.shooting = shooting;
	}
}
