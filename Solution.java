import java.util.Arrays;
public class Solution{
	private static class Node implements Comparable<Node>{
		int val;
		int idx;
		public Node(){}
		public Node(int val, int idx){
			this.val = val;
			this.idx = idx;
		}
		@Override
		public int compareTo(Node o){
			if (o == null) {
				return -1;
			}
			return this.val - o.val;
		}
	}
	// -------------------
	public int[] twoSum(int[] nums, int target){
		int[] result ={0, 0};
		Node[] tmp = new Node[nums.length];
		for (int i = 0; i < nums.length; i++) {
			tmp[i] = new Node(nums[i], i);
		}
		Arrays.sort(tmp);
		int lo = 0;
		int hi = nums.length - 1;
		while(lo < hi){
			if (tmp[lo].val + tmp[hi].val == target) {
				if (tmp[lo].idx > tmp[hi].idx) {
					result[0] = tmp[hi].idx + 1;
					result[1] = tmp[lo].idx + 1;
				}else {
					result[0] = tmp[lo].idx + 1;
					result[1] = tmp[hi].idx + 1;
				}
				break;
			}else if (tmp[lo].val + tmp[hi].val > target) {
				hi--;
			}else {
				lo++;
			}
		}
		return result;
	}
	public static void main(String[] args) {
		Solution sl = new Solution();
		int[] show = sl.twoSum(new int[]{2,7,9,11}, 11);
		for (int n : show) {
			System.out.println(n);
		}
	}
}