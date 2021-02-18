import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FiveStarSellers {

    private static double calculatePercentage(List<Integer> productRating){
        return (double)productRating.get(0) / (double)productRating.get(1);
    }

    private static double calculateDeltaPercentage(List<Integer> productRating){
        return (double)(productRating.get(0) + 1) / (double)(productRating.get(1) + 1) - (double)productRating.get(0) / (double)productRating.get(1);
    }

    private static Comparator<List<Integer>> getSortingCriteria(){
        return (p1, p2) -> {
            double deltaPerc1 = calculateDeltaPercentage(p1),
                    deltaPerc2 = calculateDeltaPercentage(p2);
            return Double.compare(deltaPerc1, deltaPerc2) * -1;
        };
    }

    public static int fiveStarReviews(List<List<Integer>> productRatings, int ratingsThreshold){
        PriorityQueue<List<Integer>> priorityQueue = new PriorityQueue<>(getSortingCriteria());
        double currentPercentage = 0.0;
        int total = productRatings.size(), added = 0;

        for(int i = 0; i < productRatings.size(); i++){
            currentPercentage += calculatePercentage(productRatings.get(i));
            priorityQueue.add(productRatings.get(i));
        }
        currentPercentage /= total;

        while(currentPercentage * 100 < (double) ratingsThreshold){
            List<Integer> bestCandidate = priorityQueue.poll();
            currentPercentage += calculateDeltaPercentage(bestCandidate) / total;

            bestCandidate.set(0, bestCandidate.get(0) + 1);
            bestCandidate.set(1, bestCandidate.get(1) + 1);

            priorityQueue.add(bestCandidate);

            added++;
        }

        return added;
    }



    public static void main(String args[]){
        List<List<Integer>> productRatings = Arrays.asList(
                Arrays.asList(4, 4),
                Arrays.asList(1, 2),
                Arrays.asList(3, 6)
        );

        System.out.println(fiveStarReviews(productRatings, 77));

    }
}
