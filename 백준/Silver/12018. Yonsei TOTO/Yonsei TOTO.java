import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            int[] people = new int[p];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < p; j++) {
                people[j] = Integer.parseInt(st.nextToken());
            }

            if (p < l) {
                arr[i] = 1;
                continue;
            }

            Arrays.sort(people);
            arr[i] = people[p - l];
        }

        Arrays.sort(arr);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (m - arr[i] < 0) {
                break;
            }
            ans = i + 1;
            m -= arr[i];
        }
        System.out.println(ans);
    }
}
