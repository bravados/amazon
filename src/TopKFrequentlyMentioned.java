import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentlyMentioned {

    public static List<String> topKFrequentlyMentionedKw(List<String> reviews,
                                                         List<String> keywords,
                                                         int k){
        Map<String, Integer> count = new HashMap<>();

        for(int i = 0; i < reviews.size(); i++){
            Set<String> review = Arrays.stream(reviews.get(i).split(" |\\."))
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            for(int j = 0; j < keywords.size(); j++)
                if(review.contains(keywords.get(j)))
                    count.put(keywords.get(j), count.getOrDefault(keywords.get(j), 0) + 1);
        }

        return count.entrySet()
                .stream()
                .sorted(Comparator.comparing(Function.identity(),
                        (entry1, entry2) -> {
                    if(entry1.getValue() > entry2.getValue())
                        return -1;
                    if(entry2.getValue() > entry1.getValue())
                        return 1;
                    return entry1.getKey().compareTo(entry2.getKey());
                        }))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String args[]){
        List<String> reviews = Arrays.asList(
                "Anacell provides the best services in the city",
                "betacellular has awesome services",
                "Best services provided by anacell, everyone should use anacell");
        List<String> keywords = Arrays.asList("anacell", "cetracular", "betacellular");

        List<String> reviews2 = Arrays.asList(
                "I love anacell Best services; Best services provided by anacell",
                "betacellular has great services",
                "deltacellular provides much better services than betacellular",
                "cetracular is worse than anacell",
                "Betacellular is better than deltacellular.");

        List<String> keywords2 = Arrays.asList(
                "anacell", "betacellular", "cetracular", "deltacellular", "eurocell"
        );

        System.out.println(topKFrequentlyMentionedKw(reviews2, keywords2, 2));


    }
}
