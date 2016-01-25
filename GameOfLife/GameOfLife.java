package gameOfLife;

import java.util.*;

public class GameOfLife {
	private ArrayList<ArrayList> rows = new ArrayList<ArrayList>(5);
	
	/**
	 * Builds the game board by filling the rows list with lists of cells (5 elements each),
	 * and then fills the latter with cell objects. Each created cell has a set of coordinates
	 * (x,y), that correspond to its indexes in the list of rows and cells. The created cells
	 * are dead by default.
	 */
	public void createBoard() {
		for (int y = 0; y < 5; y++) {
			rows.add(new ArrayList<Cell>(5));
			for (int x = 0; x < 5; x++) {
				rows.get(y).add(new Cell(this,x,y));
			}
		}
	}
	
	/**
	 * Returns the cell with the specified coordinates. The origin point (0,0) is the top left
	 * corner.
	 * 
	 * @param x	number	the x coordinate of the cell (from 0 to 4)
	 * @param y	number	the y coordinate of the cell (from 0 to 4)
	 * @return			the cell with the coordinates x,y.
	 */
	public Cell getCellByXY (int x, int y) {
		ArrayList<Cell> row = rows.get(y);
		return row.get(x);
	}
	
	/**
	 * Moves the system one generation forward. First it calculates the future status of all the
	 * cells, in accordance to the Game of Life's original rules, then updates their current
	 * status to it.
	 */
	public void nextGeneration() {
		for (ArrayList<Cell> row : rows) {
			for (Cell cell : row) {
				if (cell.getIsAlive() && cell.getAliveNeighbourCount() < 2 ) {
					cell.setNextGenStatus(false);
				}
				if (cell.getIsAlive() && cell.getAliveNeighbourCount() > 3 ) {
					cell.setNextGenStatus(false);
				}
				if (!cell.getIsAlive() && cell.getAliveNeighbourCount() == 3 ) {
					cell.setNextGenStatus(true);
				}
			}
		}
		for (ArrayList<Cell> row : rows) {
			for (Cell cell : row) {
				cell.updateLifeStatus();
			}
		}
		//System.out.println("Next generation.");
	}
	
	/**
	 * Draws the current state of the board in the console. The dead cells are denoted by X's,
	 * the alive ones - by O's.
	 */
	public void drawBoard() {
		System.out.println("");
		for (ArrayList<Cell> row : rows) {
			for (Cell cell : row) {
				if (cell.getIsAlive()) {
					System.out.print("O ");
				}
				else {
					System.out.print("X ");
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}
}
