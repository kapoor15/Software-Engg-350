import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NormalHangManTest {
	
	private HangmanGame game;
	
	@Before
    public void testSetUp() throws Exception {
         game = new NormalHangMan("SEXY", 8, "");
    }

	@Test
	public void testGuessIsNotLetter() {
		assertFalse(game.makeGuess('$'));
		assertEquals(8, game.numGuessesRemaining());
		assertEquals(4, game.numLettersRemaining());
		assertEquals("", game.lettersGuessed());
	}
	
	@Test
	public void testGuessIsInSecret() {
		assertTrue(game.makeGuess('S'));
		assertEquals(8, game.numGuessesRemaining());
		assertEquals(3, game.numLettersRemaining());
		assertEquals("S", game.lettersGuessed());
		assertEquals("S _ _ _ ", game.displayGameState());
		assertFalse(game.gameOver());
	}
	
	@Test
	public void testGuessIsNotInSecret() {
		assertFalse(game.makeGuess('P'));
		assertEquals(7, game.numGuessesRemaining());
		assertEquals(4, game.numLettersRemaining());
		assertEquals("P", game.lettersGuessed());
		assertEquals("_ _ _ _ ", game.displayGameState());
		assertFalse(game.gameOver());
	}
	
	@Test
	public void testWinningGuessesMade() {
		assertFalse(game.makeGuess('P'));
		assertEquals(7, game.numGuessesRemaining());
		assertEquals(4, game.numLettersRemaining());
		assertEquals("P", game.lettersGuessed());
		assertEquals("_ _ _ _ ", game.displayGameState());
		assertFalse(game.gameOver());
		assertTrue(game.makeGuess('S'));
		assertTrue(game.makeGuess('E'));
		assertTrue(game.makeGuess('X'));
		assertTrue(game.makeGuess('Y'));
		assertEquals(0, game.numLettersRemaining());
		assertEquals("PSEXY", game.lettersGuessed());
		assertEquals("S E X Y ", game.displayGameState());
		assertEquals(7, game.numGuessesRemaining());
		assertTrue(game.gameOver());	
	}
	
	@Test
	public void testNoGuessesLeft() {
		assertFalse(game.makeGuess('P'));
		assertFalse(game.makeGuess('Q'));
		assertFalse(game.makeGuess('R'));
		assertTrue(game.isWin());
		assertFalse(game.makeGuess('T'));
		assertFalse(game.makeGuess('Z'));
		assertFalse(game.makeGuess('J'));
		assertFalse(game.makeGuess('A'));
		assertFalse(game.makeGuess('L'));
		assertEquals(0, game.numGuessesRemaining());
		assertEquals(4, game.numLettersRemaining());
		assertEquals("PQRTZJAL", game.lettersGuessed());
		assertEquals("_ _ _ _ ", game.displayGameState());
		assertTrue(game.gameOver());
		assertFalse(game.isWin());
	}
	
	@Test
	public void testRepeatCharacters() {
		game = new NormalHangMan("BULB", 3, "");
		assertEquals("BULB", game.getSecretWord());
		assertFalse(game.makeGuess('P'));
		assertFalse(game.makeGuess('P'));
		assertEquals(2, game.numGuessesRemaining());
		assertTrue(game.isWin());
		assertTrue(game.makeGuess('B'));
		assertEquals(2, game.numGuessesRemaining());
		assertEquals(2, game.numLettersRemaining());
		assertEquals("PB", game.lettersGuessed());
		assertEquals("B _ _ B ", game.displayGameState());
		assertFalse(game.gameOver());
		assertTrue(game.isWin());
	}

}
