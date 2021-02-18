import java.util.*;

public class MostCompetitiveSubsequence {

    private boolean isSorted(int [] nums){
        for(int i = 1; i < nums.length; i++)
            if(nums[i - 1] > nums[i])
                return false;
        return true;
    }

    public int[] mostCompetitive(int[] nums, int k){
        if(k == nums.length) return nums;
        if(nums.length > 1 && isSorted(nums)) return Arrays.copyOfRange(nums, 0, k);

        int min;
        int pos = -1;
        int result [] = new int [k];
        int resultIdx = 0;

        for(int l = k - 1; l >= 0; l--) {
            min = Integer.MAX_VALUE;

            for (int i = pos + 1; i < nums.length && nums.length - i - 1 >= l; i++)
                if (nums[i] < min) {
                    min = nums[i];
                    pos = i;
                }
            result[resultIdx++] = min;
        }
        return result;
    }
    public static void main(String args[]){
        int k = 3;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] split = input.split(",");
        int nums [] = new int [split.length];
        for(int i = 0; i < split.length; i++)
            nums[i] = Integer.valueOf(split[i]).intValue();

        MostCompetitiveSubsequence mostCompetitiveSubsequence = new MostCompetitiveSubsequence();

        int[] result = mostCompetitiveSubsequence.mostCompetitive(nums, k);

        for(int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
    }

}
