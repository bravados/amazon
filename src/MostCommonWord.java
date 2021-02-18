import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MostCommonWord {

    private static String [] getWords(String paragraph){
        return paragraph.split("( |!|\\?|'|,|;|\\.)+");
    }

    private static List<String> sortWords(String[] words){
        return Arrays.stream(words).map(String::toLowerCase).sorted().collect(Collectors.toList());
    }

    public static String mostCommonWord(String paragraph, String[] banned) {

        String[] words = getWords(paragraph);
        List<String> sortedWords = sortWords(words);
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));

        int localGreatest = 0, globalGreatest = 0;
        String mostCommon = null;

        for(int i = 0; i <sortedWords.size(); i ++){
            localGreatest++;

            if(i == sortedWords.size() - 1 || !sortedWords.get(i).equals(sortedWords.get(i + 1))){

                if(localGreatest > globalGreatest && !bannedWords.contains(sortedWords.get(i))){
                    globalGreatest = localGreatest;
                    mostCommon = sortedWords.get(i);
                }

                localGreatest = 0;
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
