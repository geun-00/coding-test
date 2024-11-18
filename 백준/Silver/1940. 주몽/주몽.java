import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, temp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        arr = new int[n];
        temp = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, n - 1);

        int left = 0, right = n - 1;
        int ans = 0;

        while (left < right) {

            int sum = arr[left] + arr[right];

            if (sum == m) {
                ans++;
                right--;
            } else if (sum < m) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(ans);

    }

    private static void mergeSort(int l, int r) {
        if (l < r) {

            int m = (l + r) / 2;

            mergeSort(l, m);
            mergeSort(m + 1, r);
            merge(l, m, r);
        }
    }

    private static void merge(int l, int m, int r) {

        int i = l;
        int j = m + 1;
        int idx = l;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[idx++] = arr[i++];
            } else {
                temp[idx++] = arr[j++];
            }
        }

        while (i <= m) {
            temp[idx++] = arr[i++];
        }

        while (j <= r) {
            temp[idx++] = arr[j++];
        }

        for (int k = l; k <= r; k++) {
            arr[k] = temp[k];
        }
    }
}