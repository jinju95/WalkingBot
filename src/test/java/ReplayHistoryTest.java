import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.Test;

public class ReplayHistoryTest {
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Test
    public void testReplayHistory() {
       
        String input = "U\nD\nR\nh\nq\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        System.setOut(new PrintStream(outContent));
        Tracebot.commandHistory.clear();


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
                + "greater than zero\n"
                + "[H|h] Replay all the commands entered by the user as a history" + "\nEnter command:  \nEnter command:  \nEnter command:  \nEnter command: Command History:\nU\nD\nR\nh\n \nEnter command:";
        
        assertEquals(expectedOutput, outContent.toString().trim());

 }

}
