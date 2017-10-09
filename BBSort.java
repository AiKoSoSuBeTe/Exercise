public class BBSort{
	public static int[] bBSort(int[] nums){
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0;j < nums.length-1 ;j++) {
				// Big 2 Small
				if (nums[j] > nums[j+1]) {
					int temp;
					temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
		return nums;
	}
}