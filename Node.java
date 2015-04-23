//package trees;

public class Node {

String name;
	
	Node left;
	Node right;
	int [] values=new int[2];
	
	public Node(){
		name="-1";
	}
	
	public Node(String name){
		this.name=name;
		
	   }
	
	
	public Node(String index, Node left,Node right){
		
		name=index;
		this.left=left;
		this.right=right;
	}
}

