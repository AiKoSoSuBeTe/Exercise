import java.util.Arrays;
public class MyQueue<AnyType>{
	private static final int DEFAULT_CAPACITY = 5;

	private int theSize;
	private AnyType[] queue;

	public MyQueue(){
		doClear();
	}

	public void clear(){
		doClear();
	}

	public void doClear(){
		theSize = 0;
		ensureCapacity(DEFAULT_CAPACITY);
	}

	public int size(){
		return theSize;
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public void trimToSize(){
		ensureCapacity(size());
	}
	public void ensureCapacity(int newCapacity){
		if (newCapacity < size()) {
			return;
		}

		AnyType[] old = queue;
		queue = (AnyType[])new Object[newCapacity];
		for (int i = 0; i < size(); i++) {
			queue[i] = old[i];
		}
	}

	public AnyType get(int idx){
		return queue[idx];
	}
	public AnyType set(int idx, AnyType x){
		AnyType old = queue[idx];
		queue[idx] = x;
		return old;
	}

	public void enqueue(AnyType x){
		if (size() == queue.length) {
			ensureCapacity(size()*2+1);
		}
		queue[size()] = x;
		theSize++;
	}

	public AnyType dequeue(){
		AnyType front = queue[0];
		AnyType[] old = queue;
		queue = (AnyType[])new Object[old.length];
		System.arraycopy(old, 1, queue, 0, size()-1);
		theSize--;
		return front;
	}

	public void showQueue(){
		for (AnyType at : queue) {
			System.out.println(at);
		}
	}


}