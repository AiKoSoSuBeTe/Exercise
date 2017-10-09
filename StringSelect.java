import java.util.*;
public class StringSelect{
	public static void main(String[] args) {
		String target = "aaabccddqqudhadsadasdj";
		// reading by index
		for (int i = 0; i < target.length(); i++) {
			// first appear index equals last appear index
			if(target.indexOf(target.charAt(i)) == target.lastIndexOf(target.charAt(i))){
				System.out.println(target.charAt(i));
				break;
			}
		}
	}	
}