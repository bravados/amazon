import java.util.*;

public class NumberOfIslands3 {

    private static List<Integer> findFirst1(Map<Integer, Set<Integer>> indexedOnes){
        if(indexedOnes.isEmpty())
            return null;

        int someKey = indexedOnes.keySet().iterator().next(),
                someValue = indexedOnes.get(someKey).iterator().next();
        return Arrays.asList(someKey, someValue);
    }

    private static Map<Integer, Set<Integer>> buildIndex(char[][] grid){
        Map<Integer, Set<Integer>> indexedOnes = new HashMap<>();

        for(int i = 0; i < grid.length; i++)
            for(int j = 0; j < grid[0].length; j++)
                if(grid[i][j] == '1'){
                    Set<Integer> sameX = indexedOnes.getOrDefault(i, new HashSet<>());
                    sameX.add(j);
                    indexedOnes.putIfAbsent(i, sameX);
                }
        return indexedOnes;
    }

    private static void markLand(char[][]grid, int latitude, int longitude, Map<Integer, Set<Integer>> indexedOnes) {
        if(grid[latitude][longitude] == '1') {
            grid[latitude][longitude] = '2';
            indexedOnes.get(latitude).remove(longitude);

            if(indexedOnes.get(latitude).isEmpty())
                indexedOnes.remove(latitude);

            // upwards
            if (latitude - 1 >= 0)
                markLand(grid, latitude - 1, longitude, indexedOnes);

            // downwards
            if (latitude + 1 < grid.length)
                markLand(grid, latitude + 1, longitude, indexedOnes);

            // leftwards
            if (longitude - 1 >= 0)
                markLand(grid, latitude, longitude - 1, indexedOnes);

            //rightwards
            if (longitude + 1 < grid[0].length)
                markLand(grid, latitude, longitude + 1, indexedOnes);
        }
    }

    public static int numIslands(char[][] grid) {
        int islands = 0;
        List<Integer> coordinates;
        Map<Integer, Set<Integer>> indexedOnes = buildIndex(grid);

        while( (coordinates = findFirst1(indexedOnes)) != null){
            islands++;
            markLand(grid, coordinates.get(0), coordinates.get(1), indexedOnes);
        }

        return islands;
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
        System.out.println(numIslands(grid2));
    }

}
