import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        System.out.println(bfs(s, t));
    }

    private static String bfs(int s, int t) {
        if (s == t) {
            return "0";
        }

        Set<Long> visited = new HashSet<>();
        visited.add((long) s);

        Queue<Node> qu = new ArrayDeque<>();
        qu.offer(new Node(s, new StringBuilder()));

        while (!qu.isEmpty()) {
            Node cur = qu.poll();
            long now = cur.num;
            StringBuilder query = cur.query;

            if (now == t) {
                return query.toString();
            }

            long mul = now * now;
            if (mul <= t && visited.add(mul)) {
                qu.offer(new Node(mul, new StringBuilder(query).append("*")));
            }

            long sum = now + now;
            if (sum <= t && visited.add(sum)) {
                qu.offer(new Node(sum, new StringBuilder(query).append("+")));
            }

            if (now != 0) {
                long div = now / now;
                if (div <= t && visited.add(div)) {
                    qu.offer(new Node(div, new StringBuilder(query).append("/")));
                }
            }
        }

        return "-1";
    }

    static class Node {
        long num;
        StringBuilder query;

        public Node(long num, StringBuilder query) {
            this.num = num;
            this.query = query;
        }
    }
}