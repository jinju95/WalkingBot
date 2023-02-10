import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class InvalidMoveTest {
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	 @Test
	    public void testMoveCommandWithInvalidSteps() {
	        // Mock the command and floor size input
	    	// Set up input stream with "I 3" as the user input
	        String input = "M 0\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);
	        
	        System.setOut(new PrintStream(outContent));


	        // Call getCommand()
	        Tracebot.getCommand();
	        
	     // Check that the position has been printed correctly
	        String expectedOutput = "Welcome to RoboticPen!!!\n"
					+ "Use below commands!!!\n"
					+ "[U|u] Pen up\r\n"
					+ "[D|d] Pen down\r\n"
					+ "[R|r] Turn right\r\n"
					+ "[L|l] Turn left\r\n"
					+ "[M s|m s] Move forward s spaces (s is a non-negative integer)\r\n"
					+ "[P|p] Print the floor mapped\r\n"
					+ "[C|c] Print current position of the pen and whether it is up or down and its\r\n"
					+ "facing direction\r\n"
					+ "[Q|q] Stop the program\r\n"
					+ "[I n|i n] Initialize the system: The values of the array floor are zeros and the robot\r\n"
					+ "is back to [0, 0], pen up and facing north. n size of the array, an integer\r\n"
					+ "greater than zero" + "\n[H|h] Replay all the commands entered by the user as a history" + "\nEnter command:  give the command and a positive value again!!! \nEnter command:";
	        
	        assertEquals(expectedOutput, outContent.toString().trim());


	    }

}
