import java.util.*;

public class NumberOfIslands2 {

    public static int numIslands(char[][] grid){
        Map<Integer, Set<Integer>> indexedOnes = new HashMap<>();

        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == '1'){
                    Set<Integer> sameX = indexedOnes.getOrDefault(i, new HashSet<>());
                    sameX.add(j);
                    indexedOnes.putIfAbsent(i, sameX);
                }

        Queue<List<Integer>> island = new LinkedList<>();
         int count = 0;
         int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!indexedOnes.isEmpty()){
            count++;

            // add the seed for the first island
            Integer someKey = indexedOnes.keySet().iterator().next();
            Set<Integer> sameX = indexedOnes.get(someKey);
            Integer someValue = sameX.iterator().next();

            island.add(Arrays.asList(someKey, someValue));

            // remove the value from the index because it is going to be treated right away
            sameX.remove(someValue);
            if(sameX.isEmpty())
                indexedOnes.remove(someKey);

            while(!island.isEmpty()){
                List<Integer> land = island.poll();

                for(int[] coord: directions){
                    int newX = land.get(0) + coord[0];
                    int newY = land.get(1) + coord[1];

                    sameX = indexedOnes.get(newX);

                    if(sameX != null && sameX.contains(newY)){
                        island.add(Arrays.asList(newX, newY));
                        sameX.remove(newY);
                        if(sameX.isEmpty())
                            indexedOnes.remove(newX);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String args[]){

        char[][] grid = new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        },
                grid2 = new char[][]{
                        {'1', '1', '0', '0', '0'},
                        {'1', '1', '0', '0', '0'},
                        {'0', '0', '1', '0', '0'},
                        {'0', '0', '0', '1', '1'}
                };
        System.out.println(numIslands(grid));
    }
}
