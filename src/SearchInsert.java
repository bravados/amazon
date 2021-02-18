public class SearchInsert {

        private static int wrapper(int[] nums, int start, int end, int target){
            if(end == start) return nums[end] < target ? start + 1 : start;

            int med = start + (end - start) / 2;

            if(nums[med] == target) return med;
            return nums[med] < target ? wrapper(nums, Math.min(end, med + 1), end, target) : wrapper(nums, start, Math.max(start, med - 1), target);
        }

        public static int searchInsert(int[] nums, int target) {
            return wrapper(nums, 0, nums.length - 1, target);
        }

    public static void main(String args[]){
        int n[] = new int []{1, 3};

        System.out.println(searchInsert(n, 3));
    }
}
