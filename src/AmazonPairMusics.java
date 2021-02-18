import java.util.*;

public class AmazonPairMusics {

    private static List<Integer> getCandidates(int n){
        List<Integer> cand = new ArrayList<>();
        int missing = 60 - (n % 60);

        for(int i = 0; i <= 1000; i += 60)
            cand.add(missing + i);
        return cand;
    }

    public static int getSongPairCount(int[] songs){
        Map<Integer, List<Integer>> pos = new HashMap<>();
        int ans = 0;

        for(int i = 0; i < songs.length; i++) {
            List<Integer> positions = pos.getOrDefault(songs[i], new ArrayList<>());
            positions.add(i);
            pos.putIfAbsent(songs[i], positions);
        }

        for(int i = 0; i < songs.length; i++){
            List<Integer> cand = getCandidates(songs[i]);

            for(int j = 0; j < cand.size(); j++)
                if(pos.containsKey(cand.get(j))){
                    List<Integer> positions = pos.get(cand.get(j));
                    for(Integer position: positions){
                        if(position > i){
                            ans++;
                            System.out.println(String.format("one pair is: (%d, %d) (%d + %d)",
                                    i, position, songs[i], cand.get(j)));
                        }
                    }
                }
        }
        return ans;
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] songs = new int[size];

        for(int i = 0; i < size; i++)
            songs[i] = scanner.nextInt();

        System.out.println(getSongPairCount(songs));
    }
}
