import java.util.Random;
public class Std{
	private static int n;
	private static int num;
	public static int COUNT_K = 1 + (new Random().nextInt())%2;
	public static int COUNT_N = (new Random().nextInt())%101;
	public static int COUNT_M = (new Random().nextInt())%101;
	public static int COUNT_P = (new Random().nextInt())%101;
	public void process(){
		this.num += this.n * COUNT_K;
	}
	public void exProcess(){
		this.num -= this.n * COUNT_K;
	}
	public void stdRandom(){
		this.n = (new Random().nextInt())%6;
		//this.num = (new Random().nextInt())%6;
	}
	public static void fun(Std i, Std j, Std k, Std l, Std m, Std o){
		if ((i.n*COUNT_N + j.n*COUNT_M + k.n*COUNT_P)<(l.n*COUNT_N + m.n*COUNT_M + o.n*COUNT_P)) {
			System.out.println(i.n+"A + "+j.n+"B + "+k.n+"C "+"<"+l.n+" A + "+m.n+" B +"+o.n+"C");
			{
				i.process(); j.process(); k.process(); l.process(); m.process(); o.process();
			}
		}else if ((i.n*COUNT_N + j.n*COUNT_M + k.n*COUNT_P)>(l.n*COUNT_N + m.n*COUNT_M + o.n*COUNT_P)) {
			System.out.println(i.n+"A + "+j.n+"B + "+k.n+"C "+">"+l.n+" A + "+m.n+" B +"+o.n+"C");
			{
				i.exProcess(); j.exProcess(); k.exProcess(); l.exProcess(); m.exProcess(); o.exProcess();
			}
		}else {
			System.out.println(i.n+"A + "+j.n+"B + "+k.n+"C "+">"+(l.n+1)+" A + "+m.n+" B +"+o.n+"C");
			{
			i.process(); j.process(); k.process(); m.process(); o.process();
			l.n += (l.n+1)*COUNT_K;
			}
		}	
	}
	public static void main(String[] args) {
			Std i = new Std(); Std j = new Std(); Std k = new Std(); Std l = new Std(); Std m = new Std(); Std o = new Std();
		for (int e = 0; e < 5; e++) {
			i.stdRandom(); j.stdRandom(); k.stdRandom(); l.stdRandom(); m.stdRandom(); o.stdRandom(); 
			fun(i, j, k, l, m, o);
		}
		System.out.println("Answer1: "+i.num+"A + "+j.num+"B + "+k.num+"C < "+l.num+" A + "+m.num+" B +"+o.num+" C");
	}
}