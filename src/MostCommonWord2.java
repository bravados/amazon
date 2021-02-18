import java.util.*;

public class MostCommonWord2 {
    public static String mostCommonWord(String paragraph, String[] banned) {

        Map<String, Integer> count = new HashMap<>();

        String[] words = paragraph.toLowerCase().split("[^a-z]+");
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));

        String mostCommon = "";
        int repetitions = 0, newSubtotal;


        for(String word: words)
            if(!bannedSet.contains(word)) {
                newSubtotal = count.getOrDefault(word.toLowerCase(), 0) + 1;
                count.put(word.toLowerCase(), newSubtotal);

                if(newSubtotal > repetitions){
                    repetitions = newSubtotal;
                    mostCommon = word.toLowerCase();
                }
            }

        return mostCommon;
    }

    public static void main(String[] args){
        String paragraph = "Team, team's teams are my team choice. Bob hit a ball, the hit BALL flew far after it was hit. the mm was re hit by Alfred, alfred, alfred, por ejemplo";
        String[] banned = new String[]{"hit", "alfred"};

        System.out.println(mostCommonWord(paragraph, banned));
    }
}
