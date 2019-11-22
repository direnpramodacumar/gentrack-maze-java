import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {

	private char [][] mazeLayout;
	private boolean [][] visitedMaze;
	private int totalOfColumns;
	private int totalOfRows;
	private int startRow = 0;
    private int startColumn = 0;
    private int endRow = 0;
    private int endColumn = 0;
    private Node endNode;
    private Queue<Node> queue = new LinkedList<>();
    
    /**
	 * Creates the Maze from the file selected 
	 * @param path is the path of the file selected 
	 */
	public void createMaze(String path) {
		 BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(path));
				String line;
				int lineCount=0;
				String[] values;
				while ((line = br.readLine()) != null) {	
					//if is the first line then get and save the row and column values of the maze
					if(lineCount==0) {
						values = line.split("\\s");		
						totalOfColumns=Integer.valueOf(values[0]);
						totalOfRows=Integer.valueOf(values[1]);
						mazeLayout = new char [totalOfRows][totalOfColumns];	
					}
					//if is the second line then get and save the row and column values of the start
					else if(lineCount==1) {
						values = line.split("\\s");
						startColumn = Integer.valueOf(values[0]);
						startRow = Integer.valueOf(values[1]);
						
					}
					//if is the third line then get and save the row and column values of the end
					else if(lineCount==2) {
						values = line.split("\\s");
						endColumn = Integer.valueOf(values[0]);
						endRow = Integer.valueOf(values[1]);	
					}
					else {
						//loop to replace 1 with '#' and 0 with ' '
						for(int i=0,l=0;i<line.length();i++) {

							if(line.charAt(i)=='1') {
								mazeLayout [lineCount-3][l++] ='#';
							}
							else if(line.charAt(i)=='0') {
								mazeLayout [lineCount-3][l++] =' ';
							}	
						}
					}
					lineCount++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					br.close();	
					mazeLayout [startRow][startColumn] ='S';
					mazeLayout [endRow][endColumn] ='E';
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
	
	/**
	 * Finds the path of the maze using the Breadth-first search algorithm
	 */
	public void findPath() {
		
		boolean reachedEnd=false;	
		// construct a bidimensional array to keep track of visited cells
		visitedMaze = new boolean[totalOfRows][totalOfColumns];
		
		//add the start node to the queue
		queue.add(new Node(startRow,startColumn,null));
		
		// mark the start as visited
		visitedMaze[startRow][startColumn] = true;
		
		// loop the queue until is empty
		while(!queue.isEmpty()) {
			
			// pop the first node from queue
			Node node = queue.poll();
			
			//get the row and column values of the node 
			int nodeRow=node.getRowNumber();
			int nodeColumn=node.getColumnNumber();
			
			//if the end is found  then mark variable reachedEnd as true, save the node and stop 
			if(mazeLayout[nodeRow][nodeColumn]=='E') {
				reachedEnd=true;
				endNode=node;
				break;
			}
			//explore the Neighbours
			exploreNeighbours(nodeRow,nodeColumn,node);
		}
		//if reachedEnd is true then print "Path found" and call the method retrievePath and the printMaze
		if(reachedEnd) {
			System.out.println("Path Found");
			
			//retrieves the path and mark with 'X' in the mazeLayout
			retrivePath();
			
			//prints the solution of the maze
			printMaze();
			
		}
		//if reachedEnd is false then print "Path not found" 
		else
			System.out.println("Path Not Found");
	}
	
	/**
	 * Explores the neighbors of the current node and then add to the queue
	 * @param rr is the row of the current node
	 * @param cc is the column of the current node
	 * @param p is the current node
	 */
	public void exploreNeighbours(int rr, int cc, Node p) {
		// arrays of all 4 possible movements 
		int dr[] = { -1, 0, 0, 1 };
		int dc[] = { 0, -1, 1, 0 };
		
		//checks if there is a wrapping movement in a row
		if(rr==0 && mazeLayout[rr][cc]==' ' && mazeLayout[totalOfRows-1][cc]==' ') {
			queue.add(new Node(totalOfRows-1,cc,p));
		}
		//checks if there is a wrapping movement in a row
		else if(rr==totalOfRows-1 && mazeLayout[rr][cc]==' ' && mazeLayout[0][cc]==' ') {
			queue.add(new Node(0,cc,p));
		}
		//checks if  there is a wrapping movement in a column
		else if(cc==totalOfColumns-1 && mazeLayout[rr][cc]==' ' && mazeLayout[rr][0]==' ') {
			queue.add(new Node(rr,0,p));
		}
		//checks if  there is a wrapping movement in a column
		else if(cc==0 && mazeLayout[rr][cc]==' ' && mazeLayout[rr][totalOfColumns-1]==' ') {
			queue.add(new Node(rr,totalOfColumns-1,p));
		}
		//loop to check for all 4 possible movements
		for(int i=0; i<4;i++) {
			int br= rr + dr[i]; 
			int bc= cc + dc[i];
			
			//check if is valid position in array, or if was visited or if it is a wall
			if(br<0 || bc<0 ||br>=totalOfRows || bc>=totalOfColumns|| visitedMaze[br][bc]|| mazeLayout[br][bc]=='#') {
				continue;
			}
			//add to the queue the next movement	
			queue.add(new Node(br,bc,p));
			
			//mark as visited
			visitedMaze[br][bc] = true;
		}	
	}
	
	/**
	 * Retrieves the path and mark with 'X' in the mazeLayout.  
	 */
	public void retrivePath() {
		
		//gets the parent of the end node
		Node next = endNode.getParent();
		
		//loop to get the path and  mark with 'X' in the maeLayout
		while(next.getParent()!=null) {
			mazeLayout[next.getRowNumber()][next.getColumnNumber()]='X';
			next=next.getParent();
		}
	}
	
	/**
	 * Prints the Maze
	 */
	public void printMaze() {
		for (int i = 0; i <mazeLayout.length; i++) {
			System.out.println(mazeLayout[i]);
	    }
	}
}
