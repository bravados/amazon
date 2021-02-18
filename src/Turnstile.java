import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Turnstile {

    public static List<Integer> turnstilePredictor(int n,
                                                   List<Integer> arrTime,
                                                   List<Integer> direction){

        Integer predictions[] = new Integer[n];

        int last_priority = 1, last_second = 0;
        int i, j, bound;

        for(i = 0, j = i; i < n - 1; i++, j = i){

            // check if priority needs to be reset
            if(arrTime.get(i) > last_second + 1)
                last_priority = 1;

            if(arrTime.get(i).equals(arrTime.get(i + 1))){

                // if last_second is greater, it has priority! (i.e. when coming from resolving ties)
                if(last_second < arrTime.get(i))
                    last_second = arrTime.get(i);

                // resolve the ones with priority first
                while(j < n && arrTime.get(j) <= last_second){

                    if(direction.get(j).equals(last_priority)) {
                        predictions[j] = last_second;
                        last_second++;
                    }
                    j++;
                }

                // resolve the others
                bound = j;
                // bound is necessary to establish the limit of times to be filled.
                // Otherwise, since j keeps increasing, it can add new customers that have been coming
                // in the meantime leaving orphan gaps (ex: 0111115599)
                j = i;
                while(j < n && j < bound && arrTime.get(j) <= last_second){

                    if(!direction.get(j).equals(last_priority)){
                        predictions[j] = last_second;
                        last_second++;
                    }
                    j++;
                }

                last_priority = last_priority == 1 ? 0 : 1;
                i = j - 1; // because i is going to be incremented at the end of the loop
            } else {
                predictions[i] = arrTime.get(i);
                last_priority = direction.get(i);
            }
        }

        if(i == n - 1)
            predictions[i] = arrTime.get(i);

        return Arrays.asList(predictions);
    }

    public static void main(String args[]){
        int n = 4;
        List<Integer> arrTime = Arrays.asList(0, 0, 1, 5);
        List<Integer> direction = Arrays.asList(0, 1, 1, 0);

        n = 5;
        arrTime = Arrays.asList(0, 1, 1, 3, 3);
        direction = Arrays.asList(0, 1, 0, 0, 1);

        n = 10;
        arrTime = Arrays.asList(0, 1, 1, 1, 1, 1, 5, 5, 9, 9);
        direction = Arrays.asList(1, 0, 1, 1, 0, 0, 1, 0, 1, 1);


        System.out.println(turnstilePredictor(n, arrTime, direction));
    }
}
