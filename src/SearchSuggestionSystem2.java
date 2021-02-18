import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchSuggestionSystem2 {

    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Arrays.sort(products);

        List<List<String>> suggestedProducts = new ArrayList<>();
        int start = 0, end = products.length - 1;

        for(int i = 0; i < searchWord.length(); i++){

            while(start < products.length &&
                    start <= end &&
                    (products[start].length() <= i ||
                    products[start].charAt(i) != searchWord.charAt(i)))
                start++;

            while(end >= 0 &&
                    end >= start &&
                    (products[end].length() <= i ||
                    products[end].charAt(i) != searchWord.charAt(i)))
                end--;

            List<String> productList = new ArrayList<>();

            if(start <= end)
                for(int j = start; j <= Math.min(start + 2, end); j++)
                    productList.add(products[j]);

            suggestedProducts.add(productList);
        }
        return suggestedProducts;
    }

    public static void main(String args[]){
        String[] products = {"bags","baggage","banner","box","cloths"};
        String searchWord = "bags";

        System.out.println(suggestedProducts(products, searchWord));

    }


}
