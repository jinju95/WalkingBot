import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class RegresstionTest {
	
	
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @Test
    public void testCheckFloorEdgeNorth() {
        Tracebot.x = 2;
        Tracebot.y = 2;
        Tracebot.floorlimit = 5;
        Tracebot.direction = 'N';
        assertTrue(Tracebot.checkFloorEdge(2));
        assertFalse(Tracebot.checkFloorEdge(4));
    }

    @Test
    public void testCheckFloorEdgeSouth() {
        Tracebot.x = 2;
        Tracebot.y = 2;
        Tracebot.floorlimit = 5;
        Tracebot.direction = 'S';
        assertTrue(Tracebot.checkFloorEdge(2));
        assertFalse(Tracebot.checkFloorEdge(4));
    }

    @Test
    public void testCheckFloorEdgeEast() {
        Tracebot.x = 2;
        Tracebot.y = 2;
        Tracebot.floorlimit = 5;
        Tracebot.direction = 'E';
        assertTrue(Tracebot.checkFloorEdge(2));
        assertFalse(Tracebot.checkFloorEdge(4));
    }

    @Test
    public void testCheckFloorEdgeWest() {
        Tracebot.x = 2;
        Tracebot.y = 2;
        Tracebot.floorlimit = 5;
        Tracebot.direction = 'W';
        assertTrue(Tracebot.checkFloorEdge(2));
        assertFalse(Tracebot.checkFloorEdge(4));
    }
    
    @Test
    public void testMove() {
    	Tracebot.initializeFloor(Tracebot.floorlimit);        // Test moving North with pen down
        Tracebot.direction = 'N';
        Tracebot.penDown = true;
        Tracebot.floorlimit = 10;
        Tracebot.x = 0;
        Tracebot.y = 0;
        Tracebot.move(3);
        assertEquals(3, Tracebot.y);
        
        Tracebot.direction = 'N';
        Tracebot.penDown = false;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(3);
        assertEquals(8, Tracebot.y);

        // Test moving South with pen up
        Tracebot.direction = 'S';
        Tracebot.penDown = false;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(2);
        assertEquals(3, Tracebot.y);
        
        Tracebot.direction = 'S';
        Tracebot.penDown = true;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(2);
        assertEquals(3, Tracebot.y);

        // Test moving East with pen down
        Tracebot.x = 0;
        Tracebot.y = 0;
        Tracebot.direction = 'E';
        Tracebot.penDown = true;
        Tracebot.floorlimit = 10;
        Tracebot.move(3);
        assertEquals(Tracebot.x, 3);
        assertEquals(Tracebot.y, 0);
        
        Tracebot.direction = 'E';
        Tracebot.penDown = false;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(4);
        assertEquals(9, Tracebot.x);

        // Test moving West with pen up
        Tracebot.direction = 'W';
        Tracebot.penDown = false;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(6);
        assertEquals(Tracebot.x, 5);
        
        Tracebot.direction = 'W';
        Tracebot.penDown = true;
        Tracebot.floorlimit = 10;
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.move(6);
        assertEquals(Tracebot.x, 5);
        
    }
        

    @Test
    public void testMoveOutsideBoundary() {
        Tracebot.x = 8;
        Tracebot.y = 9;
        Tracebot.direction = 'E';
        Tracebot.penDown = false;
        Tracebot.floorlimit = 10;
        Tracebot.move(3);
        assertEquals(Tracebot.x, 8);
        assertEquals(Tracebot.y, 9);
    }

    @Test
    public void testInvalidDirection() {
        Tracebot.x = 5;
        Tracebot.y = 5;
        Tracebot.direction = 'Z';
        Tracebot.penDown = true;
        Tracebot.floorlimit = 10;
        Tracebot.move(1);
        assertEquals(Tracebot.x, 5);
        assertEquals(Tracebot.y, 5);
    }

	
	@Test
    public void testRegression() {

	String input = "i 4\nD \nl \nm e\nu\nq";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    
    System.setOut(new PrintStream(outContent));
    Tracebot.commandHistory.clear();


    // Call getCommand()
    Tracebot.main(null);
    
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
            + "[H|h] Replay all the commands entered by the user as a history" + "\nEnter command:  \nEnter command:  \nEnter command:  \nEnter command: Wrong input, please give valid command!For input string: \"e\"\n \nEnter command:  \nEnter command:";
    
    assertEquals(expectedOutput, outContent.toString().trim());
    
	}
	
	@Test
    public void testPrintPosition() {
        // Redirect standard output to a stream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Set up test data
        Tracebot.x = 2;
        Tracebot.y = 3;
        Tracebot.penDown = false;
        Tracebot.direction = 'E';
        
        // Call method to be tested
        Tracebot.printPosition();
        
        // Check the output
        String expectedOutput = "Position: 2, 3 - Pen: up - Facing: E\n";
        assertEquals(expectedOutput, outContent.toString());
        
        // Reset standard output stream
        System.setOut(System.out);
    }

}
