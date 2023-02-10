import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class PrintPositionTest {

	  @Test
	    public void testGetCommandPrintPosition() {
	        // Set up input stream with "C" as the user input
	        String input = "C\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

			final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	        outContent.reset();

			final PrintStream originalOut = System.out;
	        
	        System.setOut(new PrintStream(outContent));


	        // Set direction to 'E'
	        Tracebot.direction = 'E';

	        // Call getCommand()
	        Tracebot.getCommand();
	        
	        outContent.reset();

	        Tracebot.printPosition();


	        // Check that the position has been printed correctly
	        String expectedOutput = "Position: 0, 0 - Pen: up - Facing: E\n";
	        assertEquals(expectedOutput, outContent.toString());
	                System.setOut(originalOut);

	    }
	    
}
