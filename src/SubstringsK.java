import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class SubstringsK {

    public static Set<String> findKSizedKDistinct(String s, int k){
        Set<String> substrings = new HashSet<>();
        boolean[] visited = new boolean[26];
        StringBuilder candidate = new StringBuilder();

        for(int i = 0; i < s.length() - (k - 1); i++){
            for(int j = 0; j < k && !visited[s.charAt(i + j) - 'a']; j++){
                visited[s.charAt(i + j) - 'a'] = true;
                candidate.append(s.charAt(i + j));

                if(j == k - 1){
                    substrings.add(candidate.toString());
                }
            }
            // reset
            for(int j = 0; j < k; j++)
                visited[s.charAt(i + j) - 'a'] = false;
            candidate.delete(0, candidate.length());
        }

        return substrings;
    }

    public static void main(String args[]){
        String s = new String("awaglknagawunagwkwagl");

        System.out.println(findKSizedKDistinct(s, 4));
    }
}
