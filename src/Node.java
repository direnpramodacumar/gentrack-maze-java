
public class Node {
	private int rowNumber;
	private int columnNumber;
	private Node parent;
	/**
	 * Initialize a node with specified row, column and parent 
	 * @param x is the row number of the node
	 * @param y is the column number of the node
	 * @param p is the parent node
	 */
	public Node(int x, int y, Node p ){
		rowNumber = x;
		columnNumber = y;
		parent = p;
	}
	
	/**
	 * method to get the row number from this node.   
	 * @return the row number of this node
	 */
	public int getRowNumber(){
		return rowNumber;
	}
	
	/**
	 * method to get the column number from this node.
	 * @return the column number of this node
	 */
	public int getColumnNumber(){
		return columnNumber;
	}
	
	/**
	 * method to get the parent from this node.
	 * @return the parent node
	 */
	public Node getParent(){
		return parent;
	}

	

}

