import java.util.HashMap;
import java.util.Map;

public class AmazonPairMusics2 {

    // returns the number of pairs that can be formed that, for each pair, sums a multiple of 60
    public static int getSongPairCount(int[] songs){
        Map<Integer, Integer> map = new HashMap<>();
        int total = 0;

        for(int song: songs){
            int n = song % 60;
            int complement = n == 0 ? 0 : 60 - n;

            total += map.getOrDefault(complement, 0);
            map.put(n, map.getOrDefault(map.get(n), 0) + 1);
        }
        return total;
    }

    public static void main(String args[]){
        int [] songs = {60, 60, 60};
        System.out.println(getSongPairCount(songs));
    }
}
