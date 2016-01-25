package gameOfLife;

public class Cell {
	private int x;
	private int y;
	private boolean isAlive;
	private boolean nextGenStatus;
	private GameOfLife parentGame;
	
	public Cell (GameOfLife game, int x, int y) { //constructor
		this.x = x;
		this.y = y;
		this.parentGame = game;
	}
	
	/**
	 * Returns the life status of the cell.
	 * 
	 * @return	true if the cell is alive, false if otherwise
	 */
	public boolean getIsAlive () {
		return isAlive;
	}
	
	/**
	 * Sets the life status of the cell to the given value.
	 * 
	 * @param	status boolean	the status the cell is to be set to (true = alive, false = dead)
	 */
	public void setLifeStatus (boolean status) {
		isAlive = status;
	}
	
	/**
	 * Sets the life status, which the cell is to have in the next generation, to the given
	 * value.
	 * 
	 * @param	status boolean	the status the cell is to have in the next generation
	 * 			(true = alive, false = dead)
	 */
	public void setNextGenStatus (boolean status) {
		nextGenStatus = status;
	}
	
	/**
	 * Sets both the current life status of the cell and the one the cell is to have in the next
	 * generation to a given value. To be used only to manually switch some cells to be alive
	 * in the beginning.
	 * 
	 * @param state	boolean	the new status of the cell (true = alive, false = dead)
	 */
	public void setInitialState (boolean state) {
		isAlive = state;
		nextGenStatus = state;
	}
	
	/**
	 * Updates the cell's life status with the passing of a generation.
	 */
	public void updateLifeStatus () {
		isAlive = nextGenStatus;
	}
	
	/**
	 * Checks if the given set of coordinates (x,y) is within the game board.
	 * 
	 * @param x	number	the x coordinate to be checked
	 * @param y	number	the y coordinate to be checked
	 * @return			true if the cell of such coordinates is within the game board, false
	 * 					otherwise
	 */
	public boolean isWithinBounds(int x, int y) {
		if (x >= 0 && x < 5) {
			if (y >= 0 && y < 5) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the cell of given (x,y) coordinates is this cell.
	 * 
	 * @param x	number	the x coordinate to be checked
	 * @param y number	the y coordinate to be checked
	 * @return			true if given coordinates are the coordinates of this cell, false
	 * 					otherwise
	 */
	public boolean isThisCell(int x, int y) {
		if (x == this.x && y == this.y) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the number of neighbouring cells that are alive. Checks all cells within one
	 * coordinate unit.
	 * 
	 * @return	the number of the cell's alive neighbours
	 */
	public int getAliveNeighbourCount() {
		int count = 0;
		for (int i = x-1; i < x+2; i++) {
			for (int j = y-1; j < y+2; j++) {
				count += addToNeighbourCount(i,j);
			}
		}
		//System.out.println("This cell has " + count + " alive neighbours.");
		return count;
	}
	
	/**
	 * Checks if the cell with coordinates (x,y) exists and isn't this cell.
	 * To be called only by the {@link #getAliveNeighbourCount()} method during the calculation
	 * of the amount of alive neighbours.
	 * @param x	number	the x coordinate of the checked cell
	 * @param y	number	the y coordinate of the checked cell
	 * @return			1 if the cell exists and isn't the cell which called the method,
	 * 					0 otherwise.
	 */
	private int addToNeighbourCount(int x, int y) {
		//System.out.println("Checking cell " + x + ", " + y + " .");
		if (isWithinBounds(x,y) && !isThisCell(x,y)) {
			if (parentGame.getCellByXY(x,y).getIsAlive()) {
				//System.out.println("Cell alive.");
				return 1;
			}
		}
		//System.out.println("Cell dead.");
		return 0;
	}
}
