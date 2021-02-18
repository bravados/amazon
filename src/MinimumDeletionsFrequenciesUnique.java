import java.util.*;

public class MinimumDeletionsFrequenciesUnique {

    public int minDeletions(String s) {
        int letters [] = new int[26];

        for(int i = 0; i < s.length(); i++)
            letters[s.charAt(i) - 'a']++;

        Set<Integer> used = new HashSet<>();
        int deletions = 0;

        for(int i = 0; i < letters.length; i++) {
            int freq = letters[i];

            while (used.contains(freq) && freq > 0) {
                deletions++;
                freq--;
            }
            used.add(freq);
        }
        return deletions;
    }

    public static void main(String args[]){
        MinimumDeletionsFrequenciesUnique minimumDeletionsFrequenciesUnique = new MinimumDeletionsFrequenciesUnique();
        System.out.println(minimumDeletionsFrequenciesUnique.minDeletions("aabb"));
    }

}
