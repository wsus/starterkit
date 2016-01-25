package gameOfLife;

public class GameOfLifeTest {
	public static void main(String []args) {
		GameOfLife game = new GameOfLife();
		game.createBoard();
		game.getCellByXY(2,1).setInitialState(true);
		game.getCellByXY(2,2).setInitialState(true);
		game.getCellByXY(2,3).setInitialState(true);
		game.getCellByXY(1,2).setInitialState(true);
		game.drawBoard();
		game.nextGeneration();
		game.drawBoard();
		game.nextGeneration();
		game.drawBoard();
		game.nextGeneration();
		game.drawBoard();
		game.nextGeneration();
		game.drawBoard();
		game.nextGeneration();
		game.drawBoard();
	}
}
