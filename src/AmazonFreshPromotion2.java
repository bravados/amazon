import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AmazonFreshPromotion2 {

    private boolean equals(String s1, String s2) {
        return s1.equals(s2) || s1.equals("anything") || s2.equals("anything");
    }

    public int isWinner(List<List<String>> combination, List<String> list) {
        if (combination.isEmpty()) return 1;
        int rows = combination.stream().mapToInt(List::size).sum();

        boolean m[][] = new boolean[rows + combination.size() - 1][list.size()];

        List<String> translatedCombination = new ArrayList<>();
        for (int i = 0; i < combination.size(); i++) {
            for (int j = 0; j < combination.get(i).size(); j++)
                translatedCombination.add(combination.get(i).get(j));
            if (i + 1 < combination.size())
                translatedCombination.add("*");
        }

        for (int j = 0; j < list.size(); j++)
            m[0][j] = equals(combination.get(0).get(0), list.get(j));

        for (int i = 1; i < translatedCombination.size(); i++)
            if (translatedCombination.get(i).equals("*"))
                for (int j = 0; j < list.size(); j++)
                    m[i][j] = m[i - 1][j] || j > 0 && m[i][j - 1];
            else
                for (int j = i; j < list.size(); j++)
                    m[i][j] = m[i - 1][j - 1] && equals(translatedCombination.get(i), list.get(j));

        int winner = 0;

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < list.size() && winner == 0; j++)
                System.out.print(m[i][j] + " ");
            System.out.println();
        }

        for (int j = 0; j < list.size() && winner == 0; j++)
            if (m[m.length - 1][j])
                winner = 1;
        return winner;
    }


    public static void main(String args[]) {
        List<List<String>> combination = Arrays.asList(
                Arrays.asList("apple", "apple"),
                Arrays.asList("banana", "anything", "banana")
        );

        AmazonFreshPromotion2 amazonFreshPromotion2 = new AmazonFreshPromotion2();
        System.out.println(amazonFreshPromotion2.isWinner(combination, Arrays.asList("banana", "orange", "banana", "apple", "apple")));

    }
}
