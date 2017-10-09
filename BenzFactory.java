public class BenzFactory{
	public CarInterface getMotor(){
		return new BenzMotor();
	}
	public static void main(String[] args) {
		BenzFactory bf = new BenzFactory();
		CarInterface bm = bf.getMotor();
		BenzInterface bif = new BenzInterface(bm);
		System.out.println("Only the man who is a "+bif.useBuyyer()+" can buy this.");
		System.out.println(bif.useGetBrand());

	}
}