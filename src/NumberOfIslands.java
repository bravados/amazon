import java.util.Arrays;
import java.util.List;

public class NumberOfIslands {

    private static List<Integer> findFirst1(char[][] grid){
        List<Integer> coordinates = null;

        for(int i = 0; i < grid.length && coordinates == null; i++)
            for(int j = 0; j < grid[0].length && coordinates == null; j++)
                if(grid[i][j] == '1')
                    coordinates = Arrays.asList(i, j);

        return coordinates;
    }

    private static void markLand(char[][]grid, int latitude, int longitude) {
        if(grid[latitude][longitude] == '1') {
            grid[latitude][longitude] = '2';

            // upwards
            if (latitude - 1 >= 0)
                markLand(grid, latitude - 1, longitude);

            // downwards
            if (latitude + 1 < grid.length)
                markLand(grid, latitude + 1, longitude);

            // leftwards
            if (longitude - 1 >= 0)
                markLand(grid, latitude, longitude - 1);

            //rightwards
            if (longitude + 1 < grid[0].length)
                markLand(grid, latitude, longitude + 1);
        }
    }

    public static int numIslands(char[][] grid) {
        int islands = 0;

        List<Integer> coordinates;

        while( (coordinates = findFirst1(grid)) != null){
            islands++;
            markLand(grid, coordinates.get(0), coordinates.get(1));
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
