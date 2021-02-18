import java.util.*;

public class JobDifficulty {

    private static Comparator<List<Integer>> getComparator(){
        return Comparator.comparing(l -> l.get(0), Comparator.reverseOrder());
    }

    private static boolean isSameGroup(int start, int end, int total, int accumulated, int remainingDays){
        return total - (accumulated + end - start + 1) >= remainingDays;
    }

    public static int minDifficulty(int[] complexity, int days){
        if(days > complexity.length) return -1;

        List<List<Integer>> sorted = new LinkedList<>();

        // O(n)
        for(int i = 0; i < complexity.length; i++)
            sorted.add(Arrays.asList(complexity[i], i));

        sorted.sort(getComparator());   // O(n^2)

        int total = complexity.length,
                accumulated = 0, ans = 0;

        for(int i = 1; i <= days; i++){
            List<Integer> max = sorted.remove(0);

            ans += max.get(0);

            int start = max.get(1);
            int end = max.get(1);

            while(i < days && isSameGroup(
                    Math.min(start, sorted.get(0).get(1)),
                    Math.max(end, sorted.get(0).get(1)),
                    total,
                    accumulated,
                    days - i)){

                // update the range of the subgroup
                List<Integer> next = sorted.get(0);
                start = Math.min(start, next.get(1));
                end = Math.max(end, next.get(1));

                // remove other elements in the middle of the subgroup so they wont get counted as candidates in the next rounds
                int finalStart = start;
                int finalEnd = end;
                sorted.removeIf(l -> l.get(1) >= finalStart && l.get(1) <= finalEnd);
            }
            accumulated += end - start + 1;
        }
        return ans;
    }

    public static void main(String args[]){
        int size;
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();

        int[] difficulty = new int[size];
        for(int i = 0; i < size; i++)
            difficulty[i] = scanner.nextInt();

        int days = scanner.nextInt();

        System.out.println(minDifficulty(difficulty, days));

    }
}
