interface MultiPly{
	void multiply(int a, int b);
}
public class NineNine{
	public void nineNine(MultiPly mp){
		int COUNT_TO_CREAT = 0;
		mp.multiply(COUNT_TO_CREAT, COUNT_TO_CREAT);
	}
	public static void main(String[] args) {
		NineNine nn = new NineNine();
		nn.nineNine((a, b)->{
			for (a = 1; a <= 9; a++) {
				for (b = 1; b <= a; b++) {
					System.out.print(a+"x"+b+"="+(a*b)+" ");
				}
				System.out.println("");
			}
		});
	}
}