public class Nums{
	public static int fun(int n){
		String str = n + "";
		return str.length();
	}
	public static int sum(int n){
		String str = n +"";
		int result = 0;
		for (int i = 1; i <= str.length(); i++) {
			result += Integer.parseInt(str.substring(i-1,i));
		}
		return result;	
	}
	public static void main(String[] args) {
		System.out.println(fun(123456)+" "+sum(123456));
	}
}