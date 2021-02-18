import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FraudulentActivity {

    public static List<String> findFraudulentActivity(List<List<String>> logs,
                                                      int threshold){
        Map<String, Integer> count = new HashMap<>();

        for(int i = 0; i < logs.size(); i++){
            Set<String> distinctUsers = new HashSet<>(logs.get(i).subList(0, 2));

            for(String user: distinctUsers)
                count.put(user, count.getOrDefault(user, 0) + 1);
        }

        return count.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= threshold)
                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static void main(String args[]){
        List<List<String>> logs = Arrays.asList(
                Arrays.asList("345366", "89921", "45"),
                Arrays.asList("029323", "38239", "23"),
                Arrays.asList("38239", "345366", "15"),
                Arrays.asList("029323", "38239", "77"),
                Arrays.asList("345366", "38239", "23"),
                Arrays.asList("029323", "345366", "13"),
                Arrays.asList("38239", "38239", "23")
        );
        int threshold = 3;

        System.out.println(findFraudulentActivity(logs, threshold));
    }
}
