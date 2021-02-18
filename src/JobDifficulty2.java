import java.util.Arrays;

public class JobDifficulty2 {

    public int minDifficulty(int[] jobDifficulty, int d) {

        if(d > jobDifficulty.length) return -1;
        if(d == 1)
            return Arrays.stream(jobDifficulty).max().getAsInt();

        int m [][] = new int[d][jobDifficulty.length];

        int max = Integer.MIN_VALUE;
        for(int j = 0; j < jobDifficulty.length; j++) {
            max = Math.max(max, jobDifficulty[j]);
            m[0][j] = max;
        }

        for(int i = 1; i < d; i++)
            for(int j = i; j < jobDifficulty.length; j++){
                int min = Integer.MAX_VALUE;
                int maxTask = Integer.MIN_VALUE;

                for(int col = j; col >= i; col--){
                    maxTask = Math.max(maxTask, jobDifficulty[col]);
                    min = Math.min(min, maxTask + m[i - 1][col - 1]);
                }
                m[i][j] = min;
            }
        return m[d - 1][jobDifficulty.length - 1];
    }

    public static void main(String args[]){
        int jd [] = {6,5,4,3,2,1};
        int d = 2;

        JobDifficulty2 jobDifficulty2 = new JobDifficulty2();
        System.out.println(jobDifficulty2.minDifficulty(jd, d));
    }

}
