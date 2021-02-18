import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ZombieInMatrix {

    public static int minHours(int rows, int columns, List<List<Integer>> grid){

        List<List<Integer>> humansLeft = new LinkedList<>();
        List<List<Integer>> newZombies = new LinkedList<>();
        int minHours = 0;

        // store all humans O(m*n)
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < columns; j++)
                if(grid.get(i).get(j) == 0)
                    humansLeft.add(Arrays.asList(i, j));

       int [][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

       if(humansLeft.isEmpty()) return 0;   // there are no humans left

       while(!humansLeft.isEmpty()){
           minHours++;

           for(List<Integer> human: humansLeft){
               boolean zombie = false;

               for(int d = 0; d < directions.length && !zombie; d++){
                   int ni = human.get(0) + directions[d][0];
                   int nj = human.get(1) + directions[d][1];

                   if(ni >= 0 && ni < rows && nj >= 0 && nj < columns && grid.get(ni).get(nj) == 1)
                       zombie = true;
               }

               if(zombie)
                   newZombies.add(human);
           } // I have all the new zombies

           for(List<Integer> newZombie: newZombies){
               humansLeft.remove(newZombie);
               grid.get(newZombie.get(0)).set(newZombie.get(1), 1);
           }
       }
        return minHours;
    }

    public static void main(String args[]){
        List<List<Integer>> grid = Arrays.asList(
                Arrays.asList(0, 1, 1, 0, 1),
                Arrays.asList(0, 1, 0, 1, 0),
                Arrays.asList(0, 0, 0, 0, 1),
                Arrays.asList(0, 1, 0, 0, 0)
        );

        System.out.println(minHours(grid.size(), grid.get(0).size(), grid));


    }
}
