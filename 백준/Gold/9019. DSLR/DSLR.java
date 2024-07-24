import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[] op = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(bfs(a, b)).append("\n");
        }

        System.out.print(sb);
    }

    private static String bfs(int init, int target) {

        boolean[] visit = new boolean[10000];
        Queue<Info> qu = new ArrayDeque<>();

        visit[init] = true;
        qu.offer(new Info(init, ""));

        while (!qu.isEmpty()) {
            Info now = qu.poll();

            if (now.n == target) {
                return now.s;
            }

            for (char c : op) {
                int next = calculate(now.n, c);

                if (!visit[next]) {
                    visit[next] = true;
                    qu.offer(new Info(next, now.s + c));
                }
            }
        }

        return "CAN'T HERE";
    }

    private static int calculate(int n, char c) {
        switch (c) {
            case 'D':
                return 2 * n % 10000;
            case 'S':
                if (n == 0) {
                    return 9999;
                }
                return n - 1;
            case 'L':
                return (n % 1000) * 10 + n / 1000;
            case 'R':
                return (n % 10) * 1000 + n / 10;
        }

        return -1;
    }

    static class Info {

        int n;
        String s;

        public Info(int n, String s) {
            this.n = n;
            this.s = s;
        }
    }
}
