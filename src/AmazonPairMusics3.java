public class AmazonPairMusics3 {
    public int numPairsDivisibleBy60(int[] time) {
        int lengths[] = new int[60];
        int count = 0;

        for (int t : time) {
            if (t % 60 == 0)
                count += lengths[0];
            else
                count += lengths[60 - t % 60];

            lengths[t % 60]++;
        }
        return count;
    }
}
