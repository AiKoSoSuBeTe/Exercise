public class Benz extends Car{
	public Benz(String color, int hoursePower, int price){
		super(color, hoursePower, price);
	}
	@Override
	public String buyyer(){
		return "Rich man";
	}
	@Override
	public String getBrand(){
		return "this is a Benz car. Costs $"+this.getPrice();
	}
	public static void main(String[] args) {
		Benz myCar = new Benz("Black", 1080, 1000000);
		System.out.println("Only the man who is a "+myCar.buyyer()+" can buy this. And "+myCar.getBrand());
	}
}