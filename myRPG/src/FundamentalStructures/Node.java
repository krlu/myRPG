package FundamentalStructures;
import java.util.HashSet;

public class Node<E> {
	public E data;
	public HashSet<Node<E>> children;
	public Node(E data){
		this.data = data;
		this.children = new HashSet<Node<E>>();
	}
}
