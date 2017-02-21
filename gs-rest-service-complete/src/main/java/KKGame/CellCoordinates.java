package KKGame;

public class CellCoordinates {

	protected Integer x;
	protected Integer y;
	/**
	 * @return the x
	 */
	public Integer getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(Integer x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public Integer getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(Integer y) {
		this.y = y;
	}
	public CellCoordinates(Integer x, Integer y) {
		super();
		this.setX(x);
		this.setY(y);
	}
	
}
