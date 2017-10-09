package lee;
interface Product{
	int getProduceTime();
}
public class Printer implements Output, Product{
	private String[] printData = new String[MAX_CACHE_LINE];
	private int dataNum = 0;
	public void out(){
		while(dataNum > 0){
			System.out.println("Now printing: "+ printData[0]);
			System.arraycopy(printData, 1, printData, 0, --dataNum);
		}
	}
	public void getData(String msg){
		if (dataNum >= MAX_CACHE_LINE) {
			System.out.println("Erro: Queue is full.");
		}
		else {
			printData[dataNum++] = msg;
		}
	}
	public int getProduceTime(){
		return 45;
	}
	public static void main(String[] args) {
		Output o = new Printer();
		o.getData("Data111");
		o.getData("Data222");
		o.out();
		o.getData("Data333");
		o.getData("Data444");
		o.out();
		o.print("SWK","ZBJ","BGJ");
		o.test();
		Product p = new Printer();
		System.out.println(p.getProduceTime());
		Object obj = p;
	}
}