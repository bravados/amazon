import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

public class StorageOptimization {

    private static int greatestConsecutive(int limit, List<Integer> cells){
        boolean occupied [] = new boolean[limit];

        for(Integer cell: cells)
            occupied[cell - 1] = true;

        int max = 0;
        int count = 0;

        for(int i = 0; i < occupied.length; i++){
            if(occupied[i]) count++;
            else{
                max = Math.max(max, count);
                count = 0;
            }
        }

        return Math.max(max, count);
    }

    public static long storage(int n, int m, List<Integer> h, List<Integer> v) {
        int consecutiveH = greatestConsecutive(n, h);
        int consecutiveV = greatestConsecutive(m, v);

        return (long)(consecutiveH + 1) * (consecutiveV + 1);
    }


    public static void main(String args[]){
        System.out.println(storage(3, 2, Arrays.asList(1, 2, 3 ), Arrays.asList(1, 2)));
    }
}
