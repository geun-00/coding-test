import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> ladder = new HashMap<>();
        HashMap<Integer, Integer> snake = new HashMap<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            ladder.put(x, y);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            snake.put(u, v);
        }

        Queue<Player> qu = new ArrayDeque<>();

        boolean[] visit = new boolean[101];
        qu.offer(new Player(1, 0));

        while (!qu.isEmpty()) {

            Player player = qu.poll();

            if (player.num == 100) {
                System.out.println(player.count);
                return;
            }

            for (int i = 1; i <= 6; i++) {

                int next = player.num + i;
                if (next > 100 || visit[next]) {
                    continue;
                }

                if (ladder.containsKey(next)) {
                    qu.offer(new Player(ladder.get(next), player.count + 1));
                } else {
                    qu.offer(new Player(snake.getOrDefault(next, next), player.count + 1));
                }
                visit[next] = true;
            }
        }
    }

    static class Player {
        int num, count;

        public Player(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }
}
