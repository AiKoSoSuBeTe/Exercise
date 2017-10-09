public abstract class Car{
	private String color;
	private int hoursePower;
	private int price;
	public Car(String color, int hoursePower, int price){
		this.setColor(color);
		this.setHoursePower(hoursePower);
		this.setPrice(price);
	}
	public abstract String buyyer();
	public abstract String getBrand();
	public void setColor(String color){
		this.color = color;
	} 
	public void setHoursePower(int hoursePower){
		this.hoursePower = hoursePower;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public String getColor(){
		return this.color;
	}
	public int getHoursePower(){
		return this.hoursePower;
	}
	public int getPrice(){
		return this.price;
	}

}