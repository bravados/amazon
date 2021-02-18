import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

public class BetaTesting {

    public static int minDifficulty(int[] jobDifficulty, int d) {

        if(d > jobDifficulty.length)
            return -1;

        if(d == 1)
            return Arrays.stream(jobDifficulty).max().getAsInt();

        int[][] res = new int [jobDifficulty.length + 1][d + 1];
        int max = 0;
        for(int i = 1; i <= jobDifficulty.length; i++){
            max = Math.max(max, jobDifficulty[i - 1]);
            res[i][1] = max;
        }

        for(int j = 2; j <= d; j++)
            for(int i = j; i <= jobDifficulty.length; i++){
                int maxTask = 0;
                int minValue = Integer.MAX_VALUE;

                for(int row = i; row > j - 1; row --){
                    maxTask = Math.max(maxTask, jobDifficulty[row - 1]);
                    minValue = Math.min(minValue, maxTask + res[row - 1][j - 1]);
                }
                res[i][j] = minValue;
            }
        return res[jobDifficulty.length][d];
    }

    public static void main(String args[]){
        int size;
        Scanner scanner = new Scanner(System.in);
        size = scanner.nextInt();

        int[] complexity = new int[size];
        for(int i = 0; i < size; i++)
            complexity[i] = scanner.nextInt();

        int days = scanner.nextInt();

        System.out.println(minDifficulty(complexity, days));
    }
}
