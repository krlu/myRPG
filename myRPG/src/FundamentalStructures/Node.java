package FundamentalStructures;
import java.util.ArrayList;

public class Node<E> {
	public E data;
	public ArrayList<Node<E>> children;
	public Node(E data){
		this.data = data;
	}
}
