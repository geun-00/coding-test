import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, String> map = new HashMap<>();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            map.putIfAbsent(num, s);
            arr[i] = num;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());

            int low = 0;
            int high = n - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid] < num) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            sb.append(map.get(arr[low])).append("\n");
        }

        System.out.print(sb);
    }
}
