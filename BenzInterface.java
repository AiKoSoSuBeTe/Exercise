public class BenzInterface{
	CarInterface cif;
	public BenzInterface(CarInterface cif){
		this.cif = cif;
	}
	public String useBuyyer(){
		return this.cif.buyyer();
	}
	public String useGetBrand(){
		return this.cif.getBrand();
	}
}