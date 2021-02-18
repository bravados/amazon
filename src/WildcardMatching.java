import java.util.*;
import java.util.stream.Stream;

public class WildcardMatching {

    private static boolean equals(char c1, char c2){
        return c1 == c2 || c1 == '?' || c2 == '?';
    }

    public static boolean isMatch(String s, String p) {
        int sl = s.length(),
                pl = p.length();

        boolean match[][] = new boolean [sl + 1][pl + 1];
        match[0][0] = true;

        // first row
        boolean charVisited = false;
        for(int j = 1; j <= pl; j++)
            if(p.charAt(j - 1) == '*' && !charVisited)
                match[0][j] = true;
            else {
                charVisited = true;
                match[0][j] = false;
            }

        // first col
        for(int i = 1; i <= sl; i++)
            match[i][0] = false;

        for(int j = 1; j <= pl; j++)
            for(int i = 1; i <= sl; i++)
                if(p.charAt(j - 1) == '*')
                    match[i][j] = match[i - 1][j] || match[i][j - 1];
                else
                    match[i][j] = match[i - 1][j - 1] && equals(p.charAt(j - 1), s.charAt(i - 1));

        return match[sl][pl];
    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String p = scanner.next();

        System.out.println(isMatch(s, p));
    }
}
