import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static HashSet<String> dents = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());


        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dents.add(x + "," + y);
        }

        System.out.println(bfs(t));
    }

    private static int bfs(int t) {
        Queue<Dent> qu = new ArrayDeque<>();
        qu.offer(new Dent(0, 0, 0));

        HashSet<String> visit = new HashSet<>();
        visit.add("0,0");

        while (!qu.isEmpty()) {
            Dent now = qu.poll();

            if (now.y == t) {
                return now.moves;
            }

            for (int i = now.x - 2; i <= now.x + 2; i++) {
                for (int j = now.y - 2; j <= now.y + 2; j++) {
                    if (i < 0 || j < 0) {
                        continue;
                    }

                    if (dents.contains(i + "," + j)) {
                        String target = i + "," + j;
                        if (visit.add(target)) {
                            qu.offer(new Dent(i, j, now.moves + 1));
                        }
                    }
                }
            }
        }

        return -1;
    }

    static class Dent {
        int x, y, moves;

        public Dent(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }
}
