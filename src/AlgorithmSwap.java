import java.util.*;
import java.util.stream.Collectors;

public class AlgorithmSwap {

    public static int numberOfSwaps(List<Integer> numbers) {
        List<Integer> bst = new ArrayList<>();
        int count = 0;

        for(int i = numbers.size() - 1; i >= 0; i--){
            int begin = 0, end = bst.size();
            // while i dont find my place
            while(begin < end){
                int mid = (begin + end) / 2;

                if(numbers.get(i) > bst.get(mid))
                    begin = mid + 1;
                else
                    end = mid;
            }
            bst.add(begin, numbers.get(i));
            count += begin;
        }
        return count;
    }

    public static void main(String args[]){
        System.out.println(numberOfSwaps(Arrays.asList(1, 2)));
    }
}
