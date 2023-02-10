import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TracebotTest {


	@Test
	public void testSetPenDown() {
		Tracebot robot = new Tracebot();

		robot.setPenDown(true);
		assertTrue(Tracebot.penDown);

	}

	@Test
	public void testSetPenUP() {
		Tracebot robot = new Tracebot();

		robot.setPenDown(false);
		assertFalse(Tracebot.penDown);
	}


	@Test
	public void testTurnRightFromNorth() {
		Tracebot.direction = 'N';
		Tracebot.turnRight(Tracebot.direction);
		assertEquals('E', Tracebot.direction);
	}

	@Test
	public void testTurnRightFromEast() {
		Tracebot.direction = 'E';
		Tracebot.turnRight(Tracebot.direction);
		assertEquals('S', Tracebot.direction);
	}

	@Test
	public void testTurnRightFromSouth() {
		Tracebot.direction = 'S';
		Tracebot.turnRight(Tracebot.direction);
		assertEquals('W', Tracebot.direction);
	}

	@Test
	public void testTurnRightFromWest() {
		Tracebot.direction = 'W';
		Tracebot.turnRight(Tracebot.direction);
		assertEquals('N', Tracebot.direction);
	}

	@Test
	public void testTurnLeftFromNorth() {
		Tracebot.direction = 'N';
		Tracebot.turnLeft(Tracebot.direction);
		assertEquals('W', Tracebot.direction);
	}

	@Test
	public void testTurnLeftFromWest() {
		Tracebot.direction = 'W';
		Tracebot.turnLeft(Tracebot.direction);
		assertEquals('S', Tracebot.direction);
	}

	@Test
	public void testTurnLeftFromSouth() {
		Tracebot.direction = 'S';
		Tracebot.turnLeft(Tracebot.direction);
		assertEquals('E', Tracebot.direction);
	}

	@Test
	public void testTurnLeftFromEast() {
		Tracebot.direction = 'E';
		Tracebot.turnLeft(Tracebot.direction);
		assertEquals('N', Tracebot.direction);
	}

	@Test
	public void testMoveNorthPenDown() {
		Tracebot.x = 0;
		Tracebot.y = 0;
		Tracebot.direction = 'N';
		Tracebot.penDown = true;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(5, Tracebot.y);
		for (int i = 0; i < 5; i++) {
			assertEquals(1, Tracebot.floor[Tracebot.x][i]);
		}
	}

	@Test
	public void testMoveEastPenDown() {
		Tracebot.x = 0;
		Tracebot.y = 0;
		Tracebot.direction = 'E';
		Tracebot.penDown = true;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(5, Tracebot.x);
		for (int i = 0; i < 5; i++) {
			assertEquals(1, Tracebot.floor[i][Tracebot.y]);
		}
	}

	@Test
	public void testMoveSouthPenDown() {
		Tracebot.x = 0;
		Tracebot.y = 5;
		Tracebot.direction = 'S';
		Tracebot.penDown = true;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(0, Tracebot.y);
		for (int i = 5; i > 0; i--) {
			assertEquals(1, Tracebot.floor[Tracebot.x][i]);
		}
	}

	@Test
	public void testMoveWestPenDown() {
		Tracebot.x = 5;
		Tracebot.y = 0;
		Tracebot.direction = 'W';
		Tracebot.penDown = true;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(0, Tracebot.x);
		for (int i = 5; i > 0; i--) {
			assertEquals(1, Tracebot.floor[i][Tracebot.y]);
		}
	}

	@Test
	public void testMoveNorthPenUp() {
		Tracebot.x = 0;
		Tracebot.y = 0;
		Tracebot.direction = 'N';
		Tracebot.penDown = false;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(5, Tracebot.y);
		for (int i = 0; i < 5; i++) {
			assertEquals(0, Tracebot.floor[Tracebot.x][i]);
		}
	}

	@Test
	public void testMoveEastPenUp() {
		Tracebot.x = 0;
		Tracebot.y = 0;
		Tracebot.direction = 'E';
		Tracebot.penDown = false;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(5, Tracebot.x);
		for (int i = 0; i < 5; i++) {
			assertEquals(0, Tracebot.floor[i][Tracebot.y]);
		}
	}

	@Test
	public void testMoveSouthPenUp() {
		Tracebot.x = 0;
		Tracebot.y = 5;
		Tracebot.direction = 'S';
		Tracebot.penDown = false;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(0, Tracebot.y);
		for (int i = 5; i > 0; i--) {
			assertEquals(0, Tracebot.floor[Tracebot.x][i]);
		}
	}

	@Test
	public void testMoveWestPenUp() {
		Tracebot.x = 5;
		Tracebot.y = 0;
		Tracebot.direction = 'W';
		Tracebot.penDown = false;
		Tracebot.floor = new int[10][10];
		Tracebot.move(5);
		assertEquals(0, Tracebot.x);
		for (int i = 5; i > 0; i--) {
			assertEquals(0, Tracebot.floor[i][Tracebot.y]);
		}
	}

	@Test
	public void testprintFloor() {
		// set up the floor
		Tracebot.floor = new int[][] {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};

		// expected result
		String expected = "*   * \n  *   \n*   * \n";

		// call the method and compare with the expected result
		assertEquals(expected, Tracebot.printFloor());
	}

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void restoreStreams() {
		System.setOut(originalOut);
	}

	@Test
	public void testPrintPosition() {
		Tracebot.x = 2;
		Tracebot.y = 3;
		Tracebot.penDown = true;
		Tracebot.direction = 'N';
		Tracebot.printPosition();
		String expected = "Position: 2, 3 - Pen: down - Facing: N\n";
		assertEquals(expected, outContent.toString());
	}

	@Test
	public void testInitializeFloor() {
		Tracebot.initializeFloor(3);
		int[][] expectedFloor = new int[3][3];
		assertArrayEquals(expectedFloor, Tracebot.floor);
		assertEquals(0, Tracebot.x);
		assertEquals(0, Tracebot.y);
		assertEquals('N', Tracebot.direction);
		assertEquals(false, Tracebot.penDown);
	}


}
