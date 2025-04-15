import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int l = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[k + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int low = 0;
        int high = l;
        int length = l, pos = arr[1];

        while (low <= high) {
            int mid = (low + high) / 2;

            int count = 0;
            int prev = l;
            int firstPos = 0;

            for (int i = k; i >= 0; i--) {
                if (prev - arr[i] > mid) {
                    if (i == k) {
                        count = c + 1;
                        break;
                    }
                    count++;
                    prev = arr[i + 1];
                    firstPos = arr[i + 1];

                    if (prev - arr[i] > mid) {
                        count = c + 1;
                        break;
                    }
                }
            }

            if (count <= c) {
                length = mid;
                pos = (count < c) ? arr[1] : firstPos;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(length + " " + pos);
    }
}