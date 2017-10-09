import java.util.Random;
public class AutoBalance{
	/*
		createSeeds()
		@input: none
		@return: A array of random integers.

	 */
	public static int[] createSeeds(){
		int[] seeds = new int[3];
		Random rd = new Random();
		for (int i = 0; i < seeds.length; i++) {
			seeds[i] = rd.nextInt(10);
		}
		return seeds;
	}
	/*
		getRelative(int[])
		@input: an integer array of numbers to sort
		@return: 2D String array of relative of numbers

	 */
	public static String[][] getRelative(int[] arr){
		arr = BBSort.bBSort(arr);
		int a = arr[0];
		int b = arr[1];
		int c = arr[2];
		if (a == c) {
			return new String[][]{new String[]{"a", "=", "b"}, new String[]{"b", "=", "c"}, new String[]{"a", "=","c"}};
		}else if (a == b) {
			return new String[][]{new String[]{"a", "=", "b"}, new String[]{"b", "<", "c"}, new String[]{"a", "<","c"}};
		}else if (b == c) {
			return new String[][]{new String[]{"a", "<", "b"}, new String[]{"b", "=", "c"}, new String[]{"a", "<","c"}};
		}else {
			return new String[][]{new String[]{"a", "<", "b"}, new String[]{"b", "<", "c"}, new String[]{"a", "<","c"}};
		}
	}

	/*
		isSovable(String[][])
		@input: relative array
		@return: a boolean showing if this is solvable.
	 */
	public static boolean isSolvable(String[][] relative){
		Random rd = new Random();
		int rdInt = rd.nextInt(2); // Random from 0,1,2  //chose conditions
		int rdIntPlusOne = rdInt + 1;
			if (rdIntPlusOne == 3) {
				rdIntPlusOne = 0;
			}
		int[] seeds = createSeeds();
		//String[][] relative = getRelative(seeds);
		String[] cond1 = relative[rdInt];
		String[] cond2 = relative[rdIntPlusOne];
		// AI dis-BBSort
		/*
			         Using Steps


						          __________
				     __standard__|
			 _______|
			|

			Only For Three-Number Comparation.
		 */
		{
			// Seaching Standard and its index.
			String standard;
			int indexOfStandard1 = 0;
			int indexOfStandard2 = 0;
			for (int i = 0; i < cond1.length; i+=2) {
				for (int j = 0; j < cond2.length; j+=2) {
					if (cond1[i] == cond2[j]) {
						indexOfStandard1 = i;
						indexOfStandard2 = j;
					}
				}
			}
			standard = cond1[indexOfStandard1];
			String[] steps = new String[3];
			steps[1] += standard;
			String symbol1 = cond1[1];
			// Sorting by condition1
			if (symbol1 == "=") {
				steps[1] += "0";
			}else if (indexOfStandard1 == 0 && symbol1 == "<") {
				steps[2] += "0";
			}else if (indexOfStandard1 == 2 && symbol1 == "<") {
				steps[0] += "0";
			}
			// Sorting by Condition2
			String symbol2 = cond2[1];
			if (symbol2 == "=") {
				steps[1] += "0";
			}else if (indexOfStandard2 == 0 && symbol2 == "<") {
				steps[2] += "0";
			}else if (indexOfStandard2 == 2 && symbol2 == "<") {
				steps[0] += "0";
			}
			// Giving result
			boolean isStepEmpty = (steps[0] == null || steps[2] == null);
			// Showing conditions
			for (String cdt : cond1) {
				System.out.print(cdt + " ");
			}
			System.out.println("");
			for (String cdt : cond2 ) {
				System.out.print(cdt + " ");
			}
			// if step 2 is put only one thing, and some steps is empty.
			if (!(steps[1].length() == 5 && isStepEmpty)) {
				return true;
			}
		}
		return false;
	}

	public static String[] coditionOutput(String[] arr){
		// To output conditions
		return new String[1];
	}

	public static String transformer(String str){
		// To Show more kind of balance
		//  for example
		//  a > b --> b < a
		return new String();
	}

	public static void main(String[] args) {
		int[] seeds = createSeeds();
		String[][] relative = getRelative(seeds);
		System.out.println(AutoBalance.isSolvable(relative));
	}

}