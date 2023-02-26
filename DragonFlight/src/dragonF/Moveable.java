package dragonF;

public interface Moveable {
	public abstract void left();
	public abstract void right();
	default  public void up() {}; 
}
