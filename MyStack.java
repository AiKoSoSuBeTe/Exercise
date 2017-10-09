public class MyStack<AnyType>{
	private static final int DEFAULT_CAPACITY = 10;

	private int theSize;
	private AnyType[] stack;

	public MyStack(){
		doClear();
	} 

	public void clear(){
		doClear();
	}

	private void doClear(){
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

	public void ensureCapacity(int capacity){
		if (capacity < theSize) {
			return;
		}

		AnyType[] old = stack;
		stack = (AnyType[])new Object[capacity];
		for (int i = 0; i < size(); i++) {
			stack[i] = old[i];
		}
	}

	public AnyType get(int idx){
		return stack[idx];
	}

	public void popByVal(AnyType x){
		if (!isContains(x)) {
			return;
		}
		for (int i = size() - 1; i >= 0; i--) {
			if (stack[i] == x) {
				for (int j = i; j < size(); j++) {
					stack[j] = stack[j+1];
					stack[size() - 1] = null;
				}
				theSize--;
				// Break and only delect first one.
				break;
			}
		}
	}

	public AnyType set(int idx, AnyType newVal){
		AnyType oldVal = stack[idx];
		stack[idx] = newVal;
		return oldVal;
	}

	public AnyType top(){
		if (!isEmpty()) {
			return stack[size() - 1];
		}
		return null;
	}

	public boolean push(AnyType x){
		if (size() == stack.length) {
			ensureCapacity(size()*2+1);
		}
		stack[size()] = x;
		theSize++;
		return true;
	}

	public AnyType pop(){
		if (isEmpty()) {
			System.out.println("No element here");
			return null;
		}else{
			AnyType old = stack[size() - 1];
			stack[size() - 1] = null;
			theSize--;
			return old;
		}

	}

	public void pop(int times){
		if (isEmpty()) {
			System.out.println("No element here");
		}else {
			for (int i = 0; i < times; i++) {
				pop();
			}
		}
	}

	public void showStack(){
		for (AnyType at : stack) {
			System.out.println(at);
		}
	}

	public boolean isContains(AnyType x){
		for (AnyType at : stack) {
			if (at == x) {
				return true;
			}
		}
		return false;
	}









}