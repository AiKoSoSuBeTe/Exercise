public class Array2LinkedList{
	public static class XLinkedList{
		public int theSize;
		private Node beginMarker;
		private Node endMarker;
		public static class Node{
			public Integer data;
			public Node prev;
			public Node next;
			public Node(Integer d, Node p, Node n){
				data = d;
				prev = p;
				next = n;
			}
		}

		public XLinkedList(){
			beginMarker = new Node(null, null, null);
			endMarker = new Node(null, beginMarker, null);
			beginMarker.next = endMarker;
			theSize = 0;
		}

		public void addAtEnd(Integer x){
			addBefore(endMarker, x);
		}

		public void addBefore(Node p, Integer x){
			Node newNode = new Node(x, p.prev, p);
			p.prev.next = newNode;
			// newNode.prev.next = newNode;
			p.prev = newNode;
			theSize++;
		}

		private Node getNode(int idx, int lower, int upper){
			Node p;
			if (idx < lower || idx > upper) {
				throw new IndexOutOfBoundsException();
			}
			if (idx < size()/2) {
				p = beginMarker.next;
				for (int i = 0; i < idx; i++) {
					p = p.next;
				}
			}else {
				p = endMarker;
				for (int i = size(); i > idx; i--) {
					p = p.prev;
				}
			}

			return p;
		}

		public int size(){
			return theSize;
		}
	}

	public static void main(String[] args) {
		int[] arr = new int[]{3,6,7,1,2};
		XLinkedList xll = new XLinkedList();
		for (int n : arr) {
			xll.addAtEnd(n);
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println(xll.getNode(i, 0, xll.size()).data);
		}
	}
}