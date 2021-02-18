import java.util.*;
import java.util.stream.Collectors;

public class SearchSuggestionSystem {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Map<String, Set<String>> index = new HashMap<>();


        for(int i = 0; i < products.length; i++){
            String prefix = new String(), product = products[i];

            for(int j = 0; j < product.length(); j++){
                prefix += product.charAt(j);

                Set<String> relatedProducts = index.getOrDefault(prefix, new TreeSet<>());
                relatedProducts.add(product);

                index.putIfAbsent(prefix, relatedProducts);
            }
        }

        List<List<String>> output = new ArrayList<>();
        String prefix = new String();

        for(int i = 0; i < searchWord.length(); i++){
            prefix += searchWord.charAt(i);

            Set<String> relatedProducts = index.get(prefix);

            if(relatedProducts != null)
                output.add(relatedProducts.stream().limit(3).collect(Collectors.toList()));
            else output.add(new ArrayList<>());
        }
        return output;
    }

    public static void main(String args[]){
        //String [] products = {"mobile","mouse","moneypot","monitor","mousepad"};
        //String [] products = {"bags","baggage","banner","box","cloths"};
        String [] products = {"havana"};

        String searchWord = "tatiana";

        System.out.println(suggestedProducts(products, searchWord));
    }
}
