import java.util.HashSet;
import java.util.Set;

public class NumberOfProvinces {

    private void union(int edge1, int edge2, int cities[]) {
        int parent1 = find(edge1, cities);
        int parent2 = find(edge2, cities);

        cities[parent1] = parent2;
    }

    private int find(int city, int cities[]) {
        if (city != cities[city])
            cities[city] = find(cities[city], cities);
        return cities[city];
    }


    public int findCircleNum(int[][] isConnected) {
        int cities[] = new int[isConnected.length];

        for (int i = 0; i < cities.length; i++) cities[i] = i;

        for (int i = 0; i < isConnected.length; i++)
            for (int j = i; j < isConnected.length; j++)
                if (isConnected[i][j] == 1)
                    union(i, j, cities);

        Set<Integer> parents = new HashSet<>();
        for (int i = 0; i < cities.length; i++) parents.add(find(i, cities));

        return parents.size();
    }

    public static void main(String args[]) {
        int isConnected[][] = {
                {1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,1,0,1,0,0,0,0,0,0,0,0,0,1,0},{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},{0,1,0,1,0,0,0,1,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},{0,0,0,1,0,0,0,1,1,0,0,0,0,0,0},{0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},{1,0,0,0,0,0,0,0,0,1,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},{0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},{0,0,0,0,1,0,0,0,0,0,0,0,1,0,0},{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}
        };

        NumberOfProvinces numberOfProvinces = new NumberOfProvinces();
        System.out.println(numberOfProvinces.findCircleNum(isConnected));
    }
}
