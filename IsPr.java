public class IsPr{
	public static boolean isPr(int num){
		if (num<1) {
			return  false;
		}else if (num == 2) {
			return true;
		}else if (num > 2) {
			// i is a test num;
			for (int i=2; i<num; i++) {
				if (num%i == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		for (int j = 2; j<=100; j++) {
			System.out.println(j +""+isPr(j));
		}
	}
}