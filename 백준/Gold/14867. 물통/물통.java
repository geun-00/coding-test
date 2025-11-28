import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{0, 0, 0});

        Set<String> visited = new HashSet<>();
        visited.add(getStr(0, 0));

        while (!qu.isEmpty()) {
            int[] cur = qu.poll();
            int wa = cur[0];
            int wb = cur[1];
            int count = cur[2];

            if (wa == c && wb == d) {
                System.out.println(count);
                return;
            }

            int nextA = a;
            if (visited.add(getStr(nextA, wb))) {
                qu.offer(new int[]{nextA, wb, count + 1});
            }

            nextA = 0;
            if (visited.add(getStr(nextA, wb))) {
                qu.offer(new int[]{nextA, wb, count + 1});
            }

            int nextB = b;
            if (visited.add(getStr(wa, nextB))) {
                qu.offer(new int[]{wa, nextB, count + 1});
            }

            nextB = 0;
            if (visited.add(getStr(wa, nextB))) {
                qu.offer(new int[]{wa, nextB, count + 1});
            }

            nextA = (wb + wa >= b) ? (wa - (b - wb)) : 0;
            nextB = Math.min(wb + wa, b);
            if (visited.add(getStr(nextA, nextB))) {
                qu.offer(new int[]{nextA, nextB, count + 1});
            }

            nextA = Math.min(wa + wb, a);
            nextB = (wa + wb >= a) ? (wb - (a - wa)) : 0;
            if (visited.add(getStr(nextA, nextB))) {
                qu.offer(new int[]{nextA, nextB, count + 1});
            }
        }

        System.out.println(-1);
    }

    private static String getStr(int a, int b) {
        return a + "," + b;
    }
}