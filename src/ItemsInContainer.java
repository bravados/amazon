import java.util.*;

public class ItemsInContainer {

    public static List<Integer> itemsInContainer(String s, int[] startIndices, int[] endIndices){
        int count = 0;
        boolean canStart = false;

        List<Integer> compartmentPositions = new ArrayList<>(); // positions where the "|" is found

        int[] accumulated = new int[s.length()];
        int[] nextComp = new int[s.length()];
        int[] prevComp = new int[s.length()];

        List<Integer> numberOfItems = new ArrayList<>();    // the result


        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '*') {
                if (canStart)
                    count++;
            }
            else // it is "|"
            {
                canStart = true;
                accumulated[i] = count;
                compartmentPositions.add(i);
            }

        // if there is not even a single compartment the count is 0
        if(compartmentPositions.size() < 2)
            return Collections.nCopies(s.length(), 0);

        // each position will point to the closest "|" to the right
        int startBy = 0;
        for(Integer pos: compartmentPositions){
            for(int i = startBy; i <= pos; i++)
                nextComp[i] = pos;
            startBy = pos + 1;
        }

        // each position will point to the closest "|" to the left
        startBy = s.length() - 1;
        for(int k = compartmentPositions.size() - 1; k >= 0; k--) {
            for(int i = startBy; i >= compartmentPositions.get(k); i--)
                prevComp[i] = compartmentPositions.get(k);
            startBy = compartmentPositions.get(k) - 1;
        }

        // calculate the delta (the increment)
        for(int i = 0; i < startIndices.length; i++) {
            int startOfRange = nextComp[startIndices[i] - 1],
                    endOfRange = prevComp[endIndices[i] - 1];

            // The range must be defined between positions that contain "|"
            if(
                    startOfRange <= compartmentPositions.get(compartmentPositions.size() - 1) &&
                            endOfRange >= compartmentPositions.get(0) &&
                            nextComp[startOfRange] <= prevComp[endOfRange]
            )
                numberOfItems.add(accumulated[prevComp[endOfRange]] - accumulated[nextComp[startOfRange]]);
            else
                numberOfItems.add(0);
        }
        return numberOfItems;
    }

    public static List<Integer> itemsInContainer2(String s, int[] startIndices, int[] endIndices){
        int count = 0;
        boolean canStart = false;

        NavigableMap<Integer, Integer> accumulations = new TreeMap<>();
        List<Integer> numberOfItems = new ArrayList<>();

        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '|'){
                canStart = true;
                accumulations.put(i, count);
            }
            else
                if(canStart)
                    count++;

        if(accumulations.size() < 2)
            return Collections.nCopies(startIndices.length, 0);

        for(int i = 0; i < startIndices.length; i++){
            Optional<Map.Entry<Integer, Integer>> closestToEnd = Optional.ofNullable(accumulations.floorEntry(endIndices[i] - 1));
            Optional<Map.Entry<Integer, Integer>> closestToStart = Optional.ofNullable(accumulations.ceilingEntry(startIndices[i] - 1));

            if(!closestToEnd.isPresent() || !closestToStart.isPresent())
                numberOfItems.add(0);
            else
                numberOfItems.add(Math.max(0, closestToEnd.get().getValue() - closestToStart.get().getValue()));
        }

        return numberOfItems;
    }

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        // fill the string
        String s = sc.next();

        // fill startIndices
        int n = sc.nextInt();
        int[] startIndices = new int[n];
        for(int i = 0; i < n; i++)
            startIndices[i] = sc.nextInt();

        // fill endIndices
        int[] endIndices = new int[n];
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            endIndices[i] = sc.nextInt();

        System.out.println(itemsInContainer2(s, startIndices, endIndices));
    }
}
