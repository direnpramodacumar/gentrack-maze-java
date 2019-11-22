import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Main {

	/**
	 * Main method
	 * 
	 */
	public static void main(String[] args) {
	
		Maze maze = new Maze();
		//open the dialog to select the file
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Creates the maze according to the file selected
		    maze.createMaze(chooser.getSelectedFile().getPath());
		             
		    //Finds the path
		    maze.findPath();
		}
		else {
		    System.out.println("No file Selected");	
		}
	}
}
