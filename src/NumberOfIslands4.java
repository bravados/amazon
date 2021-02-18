import java.util.Arrays;
import java.util.List;

public class NumberOfIslands4 {

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

        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1') {
                    islands++;
                    markLand(grid, i, j);
                }
            }
        }
        return islands;
    }

    public static void main(String args[]){
        List<Integer> l = Arrays.asList(1, 2);
        System.out.println(l.subList(2, 2));

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
        //System.out.println(numIslands(grid2));
    }

}
