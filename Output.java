package lee;
public interface Output{
	int MAX_CACHE_LINE = 50;
	void out();
	void getData(String msg);
	default void print(String... msgs){
		for (String msg : msgs){
			System.out.println(msg);
		}
	}
	default void test(){
		System.out.println("The default test method...");
	}
	static String staticTest(){
		return "The static method in interface...";
	}
}