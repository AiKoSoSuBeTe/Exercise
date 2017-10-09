import java.util.*;
public class CheckBalance{
	static Collection balanceSymbols = new HashSet();
	
	public static boolean isBalanceSymbol(char c){
		balanceSymbols.add('('); balanceSymbols.add(')'); 
		balanceSymbols.add('['); balanceSymbols.add(']');
		balanceSymbols.add('{'); balanceSymbols.add('}');
		return balanceSymbols.contains(c);
	}

	public static boolean isBalance(String target){
		MyStack ms = new MyStack();
		for (int i = 0; i < target.length(); i++) {
			char charAtIdxN = target.charAt(i);
			
			if (isBalanceSymbol(charAtIdxN)){
				ms.push(charAtIdxN);
			}
			// Otherwise throws NullPorinterException
			if (ms.top() != null) {
				switch ((char)ms.top()) {
					case ')':
						if (ms.isContains('(')) {
							ms.popByVal('(');
							ms.pop(1);
						}
						break;
					case ']':
						if (ms.isContains('[')) {
							ms.popByVal('[');
							ms.pop(1);
						}
						break;
					case '}':
						if (ms.isContains('{')) {
							ms.popByVal('{');
							ms.pop(1);
						}
						break;
					default:
						break;		
				}
			}
		}
		ms.showStack();
		//System.out.println(ms.isEmpty());
		return ms.isEmpty();
	}

	public static void main(String[] args) {
		String target = "({[{}[(])([)])]}";
		System.out.println(isBalance(target));
	}
}