import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountsMerge {

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailsToName = new HashMap<>();
        Map<String, List<String>> emailsGraph = new HashMap<>();
        Set<String> accountsWithoutEmails = new HashSet<>();

        String previousEmail;
        // build graph
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).size() == 1)
                accountsWithoutEmails.add(accounts.get(i).get(0)); // only the name
            else {
                previousEmail = accounts.get(i).get(1);
                emailsToName.put(previousEmail, accounts.get(i).get(0));
                emailsGraph.putIfAbsent(previousEmail, new LinkedList<>());

                for(int j = 2; j < accounts.get(i).size(); j++){
                    String currentEmail = accounts.get(i).get(j);
                    emailsToName.put(currentEmail, accounts.get(i).get(0));
                    emailsGraph.get(previousEmail).add(currentEmail);
                    emailsGraph.computeIfAbsent(currentEmail, (key) -> new LinkedList<>()).add(previousEmail);
                    previousEmail = currentEmail;
                }
            }
        }

        // iterate graph
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        Iterator<Map.Entry<String, List<String>>> it = emailsGraph.entrySet().iterator();

        while(it.hasNext()){
            Map.Entry<String, List<String>> oneEmail = it.next();

            if(!visited.contains(oneEmail.getKey())){
                Set<String> sameAccountEmails = new HashSet<>();
                sameAccountEmails.add(oneEmail.getKey());

                List<String> relatedEmails = oneEmail.getValue();

                while(!relatedEmails.isEmpty()){
                    String firstRelatedEmail = relatedEmails.get(0);
                    relatedEmails.remove(firstRelatedEmail);

                    if(!visited.contains(firstRelatedEmail)){
                        visited.add(firstRelatedEmail);
                        sameAccountEmails.add(firstRelatedEmail);

                        if(emailsGraph.containsKey(firstRelatedEmail))
                            relatedEmails.addAll(emailsGraph.get(firstRelatedEmail));
                    }
                }
                List<String> mergedAccount = new ArrayList<>();
                mergedAccount.add(0, emailsToName.get(oneEmail.getKey())); // prepend name
                mergedAccount.addAll(sameAccountEmails.stream().sorted(Comparator.comparing(Function.identity())).collect(Collectors.toList())); // sorted set added
                result.add(mergedAccount);
            }
        }
        result.addAll(accountsWithoutEmails.stream().map(account -> Arrays.asList(account)).collect(Collectors.toList()));
        return result;
    }

    public static void main(String args[]) {
        List<List<String>> accounts = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("Mary", "mary@mail.com")
        );

        List<List<String>> lists = accountsMerge(accounts);
        for (List<String> l : lists) {
            System.out.println(l);
        }
    }
}
