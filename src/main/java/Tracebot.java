	
	import java.util.ArrayList;
import java.util.Scanner;
	
	public class Tracebot {
		static int x;
		static int y;
		static int floorlimit;
		static boolean penDown;
		static int[][] floor;
		static char direction;
	    static ArrayList<String> commandHistory = new ArrayList<String>();

	
		public static void main(String[] args) {
			getCommand();
		}
		public void setPenDown(boolean penDown) {
			Tracebot.penDown = penDown;
		}
	
		public static void getCommand(){
			Scanner in = new Scanner(System.in);
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
	                    + "greater than zero\n"
	                    + "[H|h] Replay all the commands entered by the user as a history\n");
	            System.out.print("Enter command: ");
				String command = in.nextLine();
				  char justCode = command.charAt(0);
				while (!((justCode =='q')|| (justCode =='Q'))) {
					try {
					commandHistory.add(command);
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
					case 'H':
	                case 'h':
	                    replayHistory();
	                    break;
					default:
						System.out.println("Invalid command. Please try again.");
						break;
					}
				}catch (Exception e){
						System.out.println("Wrong input, please give valid command!"+e.getMessage());
						
					}
					System.out.print(" \nEnter command: ");
					command = in.nextLine();
					justCode = command.charAt(0);
	
				 
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
			case 'W':
				Tracebot.direction = 'S';
				break;
			case 'S':
				Tracebot.direction = 'E';
				break;
			case 'E':
				Tracebot.direction = 'N';
				break;
			default:
			case 'N':
				Tracebot.direction = 'W';
				break;
			}
		}
		
		public static void floorY(int space){
	        int xx = Tracebot.x;
	        int yy = Tracebot.y;
	        char direction = Tracebot.direction;
	        if (direction =='N'){
	            for (int i = 0; i <= space; ++i){
	            	floor[xx][yy+i] = 1;
	            }
	        }
	        else {
	            for(int i = 0; i <= space; ++i){
	            	floor[xx][yy-i] = 1;
	            }
	        }
	    }
		
		public static void floorX(int space){
	        int xx = Tracebot.x;
	        int yy = Tracebot.y;
	        char direction = Tracebot.direction;
	        if (direction =='E'){
	            for (int i = 0; i <= space; ++i){       
	            	floor[xx+i][yy] = 1;
	            }
	        }
	        else {
	            for(int i = 0; i <= space; ++i){
	            	floor[xx-i][yy] = 1;
	            }
	        }
	    }
		
		//Move robot based on direction and input steps
	    public static void move(int space){
	    	int xx = Tracebot.x;
	        int yy = Tracebot.y;
	        if (checkFloorEdge(space))
	        {
	            switch (Tracebot.direction){
	                case 'N':
	                    yy += space;
	                    if (Tracebot.penDown)
	                        floorY(space);
	                    Tracebot.y = yy;
	                    break;
	                case 'S':
	                    yy -= space;
	                    if (Tracebot.penDown)
	                        floorY(space);
	                    Tracebot.y = yy;
	                    break;
	                case 'E':
	                    xx += space;
	                    if (Tracebot.penDown)
	                        floorX(space);
	                    Tracebot.x = xx;
	                    break;
	                case 'W':
	                    xx -= space;
	                    if (Tracebot.penDown)
	                        floorX(space);
	                    Tracebot.x = xx;
	                    break;
	                default:
	                    System.out.println("Invalid Direction!");
	                    break;
	            }
	            
	        }
	        else {
                System.out.println("Out of Floor boundary!");
	        	
	        }
	    }

	    //Check if input steps are within the matrix
	    public static boolean checkFloorEdge(int space){
	        char dir = Tracebot.direction;
	        int xx = Tracebot.x;
	        int yy = Tracebot.y;
	        int size = Tracebot.floorlimit;
	        switch (dir){
	            case 'S':
	                return yy - space >= 0;
	            case 'E':
	                return xx + space < size;
	            case 'W':
	                return xx - space >= 0;
	            default:
	            case 'N':
	                return yy + space < size;
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
		
		public static void replayHistory() {
	        System.out.println("Command History:");
	        for (String command : commandHistory) {
	            System.out.println(command);
	        }
	    }
	
	}
	
	
