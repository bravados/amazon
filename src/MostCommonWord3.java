import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MostCommonWord3 {

    private static int findLastOccurrence(int start, int end, String[] words, String target){

        if(start == end) {    // base case
            if (target.equals(words[start]))
                return start;
            return start - 1;
        }

        int half = start + (end - start) / 2; // offset + half the size of the current range

        if(words[half].compareTo(target) > 0)   // seek in left half
            return findLastOccurrence(start, Math.max(start, half - 1), words, target);
        // else if it is equal, because it will never be lower since they are sorted asc
        return findLastOccurrence(half + 1, end, words, target);
    }

    public static String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = Arrays.stream(banned).collect(Collectors.toSet());

        String[] words = paragraph.split("( |!|\\?|'|,|;|\\.)+");
        words = Arrays.stream(words).map(String::toLowerCase).sorted().toArray(size -> new String[size]);   // this should be expensive

        // divide & conquer
        int start = 0, end = words.length - 1;
        int repetitions = 0;
        String mostCommon = null;   // it is guaranteed there is at least one non-banned word

        do {
            String currentWord = words[start];

            end = findLastOccurrence(start, end, words, currentWord);

            int currentRepetitions = end - start + 1;

            if(currentRepetitions > repetitions && !bannedSet.contains(currentWord)){
                repetitions = currentRepetitions;
                mostCommon = currentWord;
            }

            start = end + 1;
            end = words.length - 1;
        } while(start <= end);

        return mostCommon;
    }

    public static void main(String[] args){
        String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = new String[]{"hit"};

        System.out.println(mostCommonWord(paragraph, banned));
    }
}
