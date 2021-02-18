import java.util.Arrays;
import java.util.List;

public class AmazonFreshPromotion {


   private static boolean equals(String source, String dest){
      return source.equals(dest) || source.equals("anything");
   }

   private static int isIncluded(List<String> sourceList, List<String> destList){
      boolean isMatch = false;
      int lastMatchedIdx = -1;

      int j, k;

      for(j = 0; j < destList.size() && !isMatch; j++) {
         for (k = 0; k < sourceList.size() && j + k < destList.size(); k++) {

            if (equals(sourceList.get(k), destList.get(j + k))) {
               isMatch = true;
            } else {
               isMatch = false;
               break;
            }
         }

         if(k == sourceList.size() && isMatch)
            lastMatchedIdx = sourceList.size() + j - 1;
      }

      return lastMatchedIdx;
   }


   public static int isWinner(List<List<String>> codeList, List<String> shoppingCart){

      if(codeList.isEmpty()) return 1;

      int lastGroupIdx = -1;

      for(int i = 0; i < codeList.size() && (lastGroupIdx != -1 || i == 0); i++)
         lastGroupIdx = isIncluded(codeList.get(i), shoppingCart.subList(lastGroupIdx + 1, shoppingCart.size()));

      return lastGroupIdx != -1 ? 1 : 0;
   }

   public static void main(String args[]){
      List<List<String>> codeList = Arrays.asList(
              Arrays.asList("apple", "apple"),
              Arrays.asList("banana", "anything", "banana"));

      List<List<String>> shoppingCartLists = Arrays.asList(
              Arrays.asList("orange", "apple", "apple", "banana", "orange", "banana"),
              Arrays.asList("apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"),
              Arrays.asList("banana", "orange", "banana", "apple", "apple"),
              Arrays.asList("apple", "banana", "apple", "banana", "orange", "banana"),
              Arrays.asList("apple", "apple", "apple", "banana"));

      for(int i = 0; i < shoppingCartLists.size(); i++)
         if(isWinner(codeList, shoppingCartLists.get(i)) == 1)
            System.out.println(shoppingCartLists.get(i).toString() + " is winner!");
         else System.out.println(shoppingCartLists.get(i).toString() + " NOT a winner");
   }

}
