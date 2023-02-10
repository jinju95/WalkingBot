import static org.junit.Assert.*;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;


public class CommandTest {
		
	    @Test
	    public void testGetCommandInitializeFloor() {
	        // Set up input stream with "I 3" as the user input
	        String input = "I 3\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that floor has been initialized to a 3x3 array
	        int[][] expectedFloor = new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
	        assertArrayEquals(expectedFloor, Tracebot.floor);
	    }
	    
	    @Test
	    public void testGetCommandPenDown() {
	        // Set up input stream with "D" as the user input
	        String input = "D\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that penDown is now true
	        assertTrue(Tracebot.penDown);
	    }

	    @Test
	    public void testGetCommandPenUp() {
	        // Set up input stream with "U" as the user input
	        String input = "U\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that penDown is now false
	        assertFalse(Tracebot.penDown);
	    }
	    
	    @Test
	    public void testGetCommandMove() {
	        // Set up input stream with "M 2" as the user input
	        String input = "M 2\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Set direction to 'N'
	        Tracebot.direction = 'N';

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that x and y have been updated correctly
	        assertEquals(2, Tracebot.y);
	    }
	    
	    @Test
	    public void testGetCommandTurnRight() {
	        // Set up input stream with "R" as the user input
	        String input = "R\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Set direction to 'N'
	        Tracebot.direction = 'N';

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that direction has been updated correctly
	        assertEquals('E', Tracebot.direction);
	    }
	    
	    @Test
	    public void testGetCommandTurnLeft() {
	        // Set up input stream with "L" as the user input
	        String input = "L\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);

	        // Set direction to 'N'
	        Tracebot.direction = 'N';

	        // Call getCommand()
	        Tracebot.getCommand();

	        // Check that direction has been updated correctly
	        assertEquals('W', Tracebot.direction);
	    }
	    
	    @Test
	    public void testPrintFloor() {
	    	
	        String input = "P\nq\n";
	        InputStream in = new ByteArrayInputStream(input.getBytes());
	        System.setIn(in);	
	        // Arrange
			Tracebot.floor = new int[][] {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
			String expected = "*   * \n  *   \n*   * \n";
	        
	        // Call getCommand()
	        Tracebot.getCommand();
	        
	        // Act
	        String actualOutput = Tracebot.printFloor();
	        
	        // Assert
			assertEquals(expected, Tracebot.printFloor());
	    }
	    
}










