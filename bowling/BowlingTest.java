package bowling;

public class BowlingTest {
	public static void main(String []args) {
		
		BowlingApp game = new BowlingApp();
		game.roll(10);
		game.roll(9);
		game.roll(1);
		game.roll(5);
		game.roll(5);
		game.roll(7);
		game.roll(2);
		game.roll(10);
		game.roll(10);
		game.roll(10);
		game.roll(9);
		game.roll(0);
		game.roll(8);
		game.roll(2);
		game.roll(9);
		game.roll(1);
		game.roll(10);
		System.out.println ("Current score: " + game.getScore() + ".");
		if (game.isGameOver()) {
			System.out.println ("The game is over!");
		}
	
	}
}
