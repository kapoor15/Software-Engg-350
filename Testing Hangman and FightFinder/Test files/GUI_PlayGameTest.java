import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GUI_PlayGameTest {
	
	private GUI_PlayGame screen;
	
    @Before
    public void setUp() throws Exception {
        screen = new GUI_PlayGame(4,4);
        screen.show();
    }

	@Test
	public void testGUIWithValidInput() {
		screen.inputLetter = 'A';
		screen.controller();
		assertEquals("Nope!", screen.result.getText());
        assertEquals("Guesses Remaining: 3", screen.label3.getText());
	}
	
	@Test
	public void testGUIWithInvalidInput() {
		screen.inputLetter = '$';
		screen.controller();
		assertEquals("Nope!", screen.result.getText());
        assertEquals("Guesses Remaining: 4", screen.label3.getText());
	}
	
	@Test
	public void testPlayAsNormalAndGuessRight() {
		screen.game = new NormalHangMan("RED", 2, "");
		//screen.game.makeGuess('R');
		screen.inputLetter = 'R';
		screen.isEvil = false;
		screen.controller();
		assertEquals("Yes!", screen.result.getText());
		assertEquals("Guesses Remaining: 2", screen.label3.getText());
		assertEquals("Secret Word: R _ _ " , screen.label2.getText());
	}
	
	@Test
	public void testPlayAsEvilAndGuessRight() {
		screen.game = new NormalHangMan("RED", 2, "");
		//screen.game.makeGuess('R');
		screen.inputLetter = 'R';
		screen.isEvil = true;
		screen.controller();
		assertEquals("Yes!", screen.result.getText());
		assertEquals("Guesses Remaining: 2", screen.label3.getText());
		assertEquals("Secret Word: R _ _ " , screen.label2.getText());
	}
	
	@Test
	public void testPlayUntilWin() {
		screen.game = new NormalHangMan("RED", 2, "");
		screen.isEvil = false;
		screen.inputLetter = 'D';
		screen.controller();
		screen.inputLetter = 'E';
		screen.controller();
		screen.inputLetter = 'R';
		screen.controller();
		assertEquals("Yes!", screen.result.getText());
		assertEquals("Guesses Remaining: 2", screen.label3.getText());
		assertEquals("Secret Word: R E D " , screen.label2.getText());
		assertTrue(screen.game.isWin());
		assertTrue(screen.game.gameOver());
	}
	
	@Test
	public void testPlayUntilLose() {
		screen.game = new NormalHangMan("RED", 2, "");
		screen.isEvil = true;
		screen.inputLetter = 'X';
		screen.controller();
		screen.inputLetter = 'Y';
		screen.controller();
		assertEquals("Nope!", screen.result.getText());
		assertEquals("Guesses Remaining: 0", screen.label3.getText());
		assertEquals("Secret Word: _ _ _ " , screen.label2.getText());
		assertFalse(screen.game.isWin());
		assertTrue(screen.game.gameOver());
	}
	
	

}
