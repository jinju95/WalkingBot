	
	import java.util.Scanner;
	
	public class Tracebot {
		static int x;
		static int y;
		static int floorlimit;
		static boolean penDown;
		static int[][] floor;
		static char direction;
	
		public static void main(String[] args) {
			getCommand();
		}
		public void setPenDown(boolean penDown) {
			Tracebot.penDown = penDown;
		}
	
		public static void getCommand(){
			try (Scanner in = new Scanner(System.in)) {
				System.out.print("Welcome to RoboticPen!!!\n"
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
						+ "greater than zero\n");
				System.out.print("Enter command: ");
				String command = in.nextLine();
				  char justCode = command.charAt(0);
				while (!((justCode =='q')|| (justCode =='Q'))) {			    
					switch (justCode) {
					case 'I':
					case 'i':
						int floorSize = Integer.parseInt(command.substring(2).trim());
						if(floorSize>0) {
						initializeFloor(floorSize);
						} 
						else
							System.out.print(" give the command and a positive value again!!!");
						break;
					case 'C':
					case 'c':
						printPosition();
						break;
					case 'D':
					case 'd':
						penDown = true;
						break;
					case 'U':
					case 'u':
						penDown = false;
						break;
					case 'M':
					case 'm':
						int steps = Integer.parseInt(command.substring(2).trim());
						if(steps>0)
							move(steps);
						else
							System.out.print(" give the command and a positive value again!!!");
						break;
					case 'R':
					case 'r':
						turnRight(Tracebot.direction);
						break;
					case 'L':
					case 'l':
						turnLeft(Tracebot.direction);
						break;
					case 'P':
					case 'p':
						System.out.println(printFloor());
						break;
					default:
						System.out.println("Invalid command. Please try again.");
						break;
					}
	
					System.out.print(" \nEnter command: ");
					command = in.nextLine();
					justCode = command.charAt(0);
	
				}
			}
			catch (Exception e){
				System.out.println("Wrong input, please startover!"+e.getMessage());
				
			}
		}
	
		public static void initializeFloor(int size) {
			floor = new int[size][size];
			floorlimit = size;
			x = 0;
			y = 0;
			direction = 'N';
			penDown = false;
		}
		public static void turnRight(char direction) {
			switch(direction) {
			case 'N':
				Tracebot.direction = 'E';
				break;
			case 'E':
				Tracebot.direction = 'S';
				break;
			case 'S':
				Tracebot.direction = 'W';
				break;
			case 'W':
				Tracebot.direction = 'N';
				break;
			}
		}
	
		public static void turnLeft(char direction) {
			switch(direction) {
			case 'N':
				Tracebot.direction = 'W';
				break;
			case 'W':
				Tracebot.direction = 'S';
				break;
			case 'S':
				Tracebot.direction = 'E';
				break;
			case 'E':
				Tracebot.direction = 'N';
				break;
			}
		}
	
		public static void move(int spaces) {
			switch(direction) {
			case 'N':
				y = y + spaces;
				break;
			case 'E':
				x = x + spaces;
				break;
			case 'S':
				y = y - spaces;
				break;
			case 'W':
				x = x - spaces;
				break;
			}
			if (penDown) {
				switch(direction) {
				case 'S':
					for (int i = y + spaces; i > y; i--) {
						floor[x][i] = 1;
					}
					break;
				case 'E':
					for (int i = x - spaces; i < x; i++) {
						floor[i][y] = 1;
					}
					break;
				case 'N':
					for (int i = y - spaces; i < y; i++) {
						floor[x][i] = 1;
					}
					break;
				case 'W':
					for (int i = x + spaces; i > x; i--) {
						floor[i][y] = 1;
					}
					break;
				}
			}
	}
	
		public static void printPosition() {
			System.out.println("Position: " + x + ", " + y + " - Pen: " + (penDown ? "down" : "up") + " - Facing: " + direction);
		}
	
		public static String printFloor() {
	
			StringBuilder sb = new StringBuilder();
			for (int i = floor.length - 1; i >= 0; i--) {
	
				for (int j = 0; j < floor[0].length; j++) {
					sb.append(floor[j][i] == 1 ? "* " : "  ");
				}
				sb.append("\n");
			}
			return sb.toString();
		}
	
	}
	
	
