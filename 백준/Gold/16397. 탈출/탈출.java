import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());

        Queue<int[]> qu = new ArrayDeque<>();
        qu.offer(new int[]{n, 0});

        boolean[] visited = new boolean[100_000];
        visited[n] = true;

        while (!qu.isEmpty()) {
            int[] node = qu.poll();
            int num = node[0];
            int count = node[1];

            if (count > t) continue;

            if (num == g) {
                System.out.println(count);
                return;
            }

            if (num + 1 <= 99999 && !visited[num + 1]) {
                qu.offer(new int[]{num + 1, count + 1});
                visited[num + 1] = true;
            }

            num *= 2;
            if (num > 99999) continue;

            char[] arr = String.valueOf(num).toCharArray();
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != '0') {
                    arr[i] = (char) (arr[i] - 1);
                    break;
                }
            }

            num = Integer.parseInt(new String(arr));
            if (!visited[num]) {
                qu.offer(new int[]{num, count + 1});
                visited[num] = true;
            }
        }

        System.out.println("ANG");
    }
}