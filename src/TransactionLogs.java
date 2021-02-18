import java.util.*;
import java.util.function.Function;

public class TransactionLogs {

    public String[] transactionLogs(List<List<String>> logs, int threshold) {
        Map<String, Integer> count = new HashMap<>();

        for (List<String> log : logs) {
            count.put(log.get(0), count.getOrDefault(log.get(0), 0) + 1);

            if (!log.get(1).equals(log.get(0)))
                count.put(log.get(1), count.getOrDefault(log.get(1), 0) + 1);
        }

        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            if (entry.getValue() >= threshold)
                result.add(entry.getKey());
        }
        Collections.sort(result);

        return result.toArray(new String[0]);
    }

    public static void main(String args[]){
        List<List<String>> logs = Arrays.asList(
                Arrays.asList("345366", "89921" ,"45"),
                Arrays.asList("029323", "38239" ,"23"),
                Arrays.asList("38239 ","345366" ,"15"),
                Arrays.asList("029323", "38239" ,"77"),
                Arrays.asList("345366", "38239" ,"23"),
                Arrays.asList("029323", "345366", "13"),
                Arrays.asList("38239","38239", "23")
        );

        TransactionLogs transactionLogs = new TransactionLogs();
        String[] userIds = transactionLogs.transactionLogs(logs, 3);

        for(int i = 0; i < userIds.length; i++)
            System.out.print(userIds[i] + " ");
        System.out.println();


    }
}
