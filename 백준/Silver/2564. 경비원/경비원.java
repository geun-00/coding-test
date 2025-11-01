import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int num = Integer.parseInt(br.readLine());

        int[] arr = new int[num + 1];

        for (int i = 0; i < num + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            int pos = 0;
            switch (dir) {
                case 1:
                    pos = dist;
                    break;
                case 2:
                    pos = n + m + (n - dist);
                    break;
                case 3:
                    pos = (n * 2 + m) + (m - dist);
                    break;
                case 4:
                    pos = n + dist;
                    break;
            }

            arr[i] = pos;
        }

        int end = (n + m) * 2;
        int ans = 0;
        int dong = arr[num];

        for (int i = 0; i < num; i++) {
            int store = arr[i];
            ans += Math.min(Math.abs(store - dong), Math.min((end - store + dong), (end - dong + store)));
        }

        System.out.println(ans);
    }
}