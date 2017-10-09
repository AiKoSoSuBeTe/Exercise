public class StackTest{
	public static void main(String[] args) {
		MyStack ms = new MyStack();
		String hw = "Hello, World!";
		for (int i = 0; i < hw.length(); i++) {
			ms.push(hw.charAt(i));
		}
		ms.showStack();
		System.out.println("------");
		ms.popByVal('l');
		ms.showStack();
	}
}