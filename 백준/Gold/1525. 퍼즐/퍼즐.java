import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static String goal = "123456780";
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                sb.append(st.nextToken());
            }
        }

        String init = sb.toString();
        map.put(init, 0);

        System.out.println(bfs(init));
    }

    private static int bfs(String init) {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<String> qu = new ArrayDeque<>();
        qu.offer(init);

        while (!qu.isEmpty()) {
            String now = qu.poll();

            int count = map.get(now);

            if (now.equals(goal)) {
                return count;
            }

            int zero = now.indexOf("0");
            int x = zero / 3;
            int y = zero % 3;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < 3 && ny >= 0 && ny < 3) {

                    int nextZero = nx * 3 + ny;

                    String next = swap(now, zero, nextZero);

                    if (!map.containsKey(next)) {
                        map.put(next, count + 1);
                        qu.offer(next);
                    }
                }
            }
        }

        return -1;
    }

    private static String swap(String s, int i, int j) {
        char[] charArray = s.toCharArray();
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return new String(charArray);
    }
}
