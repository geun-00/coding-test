import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        HashMap<Character, Integer> map = new HashMap<>();

        String[] init = new String[3];
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if (n > 0) {
                String s = st.nextToken();
                init[i] = s;
                for (int j = 0; j < n; j++) {
                    map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0) + 1);
                }
            }
            if (init[i] == null) {
                init[i] = "";
            }
        }

        String[] goal = new String[3];

        goal[0] = "A".repeat(map.getOrDefault('A', 0));
        goal[1] = "B".repeat(map.getOrDefault('B', 0));
        goal[2] = "C".repeat(map.getOrDefault('C', 0));

        System.out.println(solve(init, goal));
    }

    private static int solve(String[] init, String[] goal) {

        Queue<State> qu = new ArrayDeque<>();
        qu.offer(new State(init, 0));

        HashSet<String> visit = new HashSet<>();
        visit.add(Arrays.toString(init));

        while (!qu.isEmpty()) {

            State now = qu.poll();

            if (Arrays.equals(now.arr, goal)) {
                return now.moves;
            }

            for (int i = 0; i < 3; i++) {
                if (!now.arr[i].isEmpty()) {
                    for (int j = 0; j < 3; j++) {
                        if (i != j) {

                            String[] temp = now.arr.clone();

                            char top = temp[i].charAt(temp[i].length() - 1);
                            temp[i] = temp[i].substring(0, temp[i].length() - 1);
                            temp[j] += top;

                            if (visit.add(Arrays.toString(temp))) {
                                qu.offer(new State(temp, now.moves + 1));
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class State {
        String[] arr;
        int moves;

        public State(String[] arr, int moves) {
            this.arr = arr;
            this.moves = moves;
        }
    }
}
