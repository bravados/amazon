import java.util.*;

public class Turnstile3 {

    int[] turnstile(int times[], int directions[]) {
        int result[] = new int[times.length];

        Queue<List<Integer>>
                in = new LinkedList<>(),
                out = new LinkedList<>();

        for (int i = 0; i < times.length; i++)
            if (directions[i] == 0)
                in.add(Arrays.asList(times[i], i));
            else
                out.add(Arrays.asList(times[i], i));

        int time = 0;

        while (!in.isEmpty() || !out.isEmpty()) {
            Integer timeIn = in.isEmpty() ? Integer.MAX_VALUE : in.peek().get(0);
            Integer timeOut = out.isEmpty() ? Integer.MAX_VALUE : out.peek().get(0);

            time = Math.max(time, Math.min(timeIn, timeOut));

            if (timeOut <= timeIn) {
                while (!out.isEmpty() && out.peek().get(0) - time < 1) {
                    List<Integer> poll = out.poll();
                    times[poll.get(1)] = time;
                    time++;
                }
            } else {
                while (!in.isEmpty() && in.peek().get(0) - time < 1) {
                    List<Integer> poll = in.poll();
                    times[poll.get(1)] = time;
                    time++;
                }
            }
        }
        return times;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int times[] = new int[size];
        int dirs[] = new int[size];

        for (int i = 0; i < size; i++)
            times[i] = scanner.nextInt();

        for (int i = 0; i < size; i++)
            dirs[i] = scanner.nextInt();

        Turnstile3 turnstile3 = new Turnstile3();
        int[] result = turnstile3.turnstile(times, dirs);

        for (int i = 0; i < result.length; i++)
            System.out.print(result[i] + " ");
        System.out.println();
    }
}
