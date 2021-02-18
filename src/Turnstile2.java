import java.util.*;

public class Turnstile2 {

    public static int[] turnstile(int n,
                                  int[] arrTime,
                                  int[] direction) {

        int[] times = new int[n];

        // build the 2 queues
        List<Queue<List<Integer>>> queues = new ArrayList<>();
        queues.add(new LinkedList<>());
        queues.add(new LinkedList<>());
        for(int i = 0; i < n; i++)
            queues.get(direction[i]).add(Arrays.asList(arrTime[i], i));

        // compare elements at the top to decide which queue is to process
        int currentTime = 0,
                priority = 1;
        while(!queues.get(0).isEmpty() || !queues.get(1).isEmpty()){
            Integer time0 = queues.get(0).isEmpty() ? Integer.MAX_VALUE : queues.get(0).peek().get(0),
                    time1 = queues.get(1).isEmpty() ? Integer.MAX_VALUE : queues.get(1).peek().get(0);
            Integer faster = Math.min(time0, time1);

            // reset the priority
            if(faster - currentTime > 1)
                priority = 1;

            currentTime = Math.max(currentTime, faster);

            int comp = time0.compareTo(time1);
            int queueNumber = (comp == - 1 || comp == 0 && priority == 0) ? 0 : 1;

            do {
                List<Integer> current = queues.get(queueNumber).poll();
                times[current.get(1)] = currentTime;
                currentTime++;
            } while (!queues.get(queueNumber).isEmpty() && queues.get(queueNumber).peek().get(0) <= currentTime);

            priority = queueNumber;
        }
        return times;
    }

    public static void main(String args[]){
        int n = 4;
        int[] arrTime = {0, 0, 1, 5};
        int[] direction = {0, 1, 1, 0};


        int[] times = turnstile(n, arrTime, direction);

        for(int i = 0; i < times.length; i++)
            System.out.print(times[i]);
        System.out.println();
    }
}
