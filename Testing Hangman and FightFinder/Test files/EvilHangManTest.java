import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class EvilHangManTest {

private HangmanGame game;
	
	@Before
    public void testSetUp() throws Exception {
         game = new EvilHangMan(4, 5);
    }
	
	@Test
	public void testInputIsNotValid() {
		assertFalse(game.makeGuess('%'));
		assertFalse(game.makeGuess('$'));
		assertEquals(26, game.numLettersRemaining());
		assertEquals("", game.lettersGuessed());
		assertEquals(5, game.numGuessesRemaining());
	}
	
	@Test
	public void testLoseRunningOutOfGuesses() {
		game = new EvilHangMan(4, 3);
		assertFalse(game.makeGuess('Z'));
		assertFalse(game.makeGuess('Y'));
		assertFalse(game.isWin());
		assertFalse(game.gameOver());
		assertEquals(26, game.numLettersRemaining());
		assertEquals("ZY", game.lettersGuessed());
		assertEquals(1, game.numGuessesRemaining());
		assertFalse(game.makeGuess('B'));
		assertTrue(game.gameOver());
		assertEquals("ZYB", game.lettersGuessed());
		assertEquals("_ _ _ _ ", game.displayGameState());
		
	}
	
	@Test
	public void testModificationOfSecretWord() {
		String first = game.getSecretWord();
		assertEquals(5, game.numGuessesRemaining());
		assertFalse(game.makeGuess('O'));
		assertFalse(game.makeGuess('E'));
		assertEquals(3, game.numGuessesRemaining());
		String second = game.getSecretWord();
		assertFalse(game.makeGuess('A'));
		assertFalse(game.makeGuess('I'));
		String third = game.getSecretWord();
		assertNotEquals(first, second);
		assertNotEquals(third, second);
		assertNotEquals(first, third);
		assertEquals("OEAI", game.lettersGuessed());
	}
	
	@Test
	public void testRepeatChars() {
		game = new EvilHangMan(4, 3);
		assertFalse(game.makeGuess('Z'));
		assertFalse(game.makeGuess('Z'));
		assertFalse(game.isWin());
		assertFalse(game.gameOver());
		assertEquals(26, game.numLettersRemaining());
		assertEquals("Z", game.lettersGuessed());
		assertEquals(2, game.numGuessesRemaining());
	}
	
	@Test
	public void testUnableToGetSecretWordLetter() {
		game = new EvilHangMan(4, 3);
		assertFalse(game.makeGuess('X'));
		String first = game.getSecretWord();
		assertFalse(game.isWin());
		assertFalse(game.gameOver());
		assertEquals(26, game.numLettersRemaining());
		assertEquals("X", game.lettersGuessed());
		assertFalse(game.makeGuess('Z'));
		String second = game.getSecretWord();
		assertEquals(1, game.numGuessesRemaining());
		assertEquals(first, second);
	}
	
	@Test
	public void testWinGame() {
		game = new EvilHangMan(4, 26);
		assertFalse(game.makeGuess('A'));
		assertFalse(game.makeGuess('E'));
		assertFalse(game.makeGuess('I'));
		assertFalse(game.makeGuess('O'));
		assertFalse(game.makeGuess('U'));
		assertFalse(game.makeGuess('Y'));
		assertFalse(game.makeGuess('B'));
		assertFalse(game.makeGuess('C'));
		assertFalse(game.makeGuess('D'));
		assertFalse(game.makeGuess('F'));
		assertFalse(game.makeGuess('G'));
		assertFalse(game.makeGuess('H'));
		assertFalse(game.makeGuess('J'));
		assertFalse(game.makeGuess('K'));
		assertFalse(game.makeGuess('L'));
		assertFalse(game.makeGuess('M'));
		assertFalse(game.makeGuess('N'));
		assertTrue(game.makeGuess('P'));
		assertFalse(game.gameOver());
		assertFalse(game.isWin());
		assertFalse(game.makeGuess('Q'));
		assertFalse(game.makeGuess('R'));
		assertTrue(game.makeGuess('S'));
		assertTrue(game.makeGuess('T'));
		assertFalse(game.makeGuess('V'));
		assertFalse(game.makeGuess('W'));
		assertFalse(game.makeGuess('X'));
		assertFalse(game.makeGuess('Z'));
		assertEquals("_ _ _ _ ", game.displayGameState());
	}
	
	@Test
	public void testSingleChar() {
		game = new EvilHangMan(1, 26);
		assertTrue(game.makeGuess('A'));
		assertEquals("_ ", game.displayGameState());
	}
	
	

}
