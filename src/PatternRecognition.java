import java.util.ArrayList;
import java.util.List;

public class PatternRecognition {

    public int countMatches(String pattern, String input){
        if(pattern.isEmpty()) return 0;

        boolean m[][] = new boolean[pattern.length()][input.length()];

        for(int j = 0; j < input.length(); j++){
            m[0][j] = input.charAt(j) == pattern.charAt(0);
        }

        for(int i = 1; i < pattern.length(); i++)
            for(int j = i; j < input.length(); j++){
                m[i][j] = m[i - 1][j - 1] && input.charAt(j) == pattern.charAt(i);
            }

        int total = 0;
        for(int j = 0; j < input.length(); j++)
            total += m[pattern.length() - 1][j] ? 1 : 0;

        return total;
    }

    public String patternRecognition(String input) {
        String[] patternAndBlobs = input.split(";");

        if(patternAndBlobs.length == 0) return null;

        String pattern = patternAndBlobs[0];
        String blobs = patternAndBlobs[1];
        String[] splitBlobs = blobs.split("\\|");

        StringBuilder sb = new StringBuilder();
        int total = 0;

        for(int i = 0; i < splitBlobs.length; i++){
            int count = countMatches(pattern, splitBlobs[i]);
            sb.append(count);
            sb.append("|");
            total += count;
        }
        sb.append(total);

        return sb.toString();
    }

    public static void main(String args[]){
        PatternRecognition patternRecognition = new PatternRecognition();
        System.out.println(patternRecognition.patternRecognition("bc;bcdefbcbebc|abcdebcfgsdf|cbdbesfbcy|1bcdef23423bc32"));
    }
}
