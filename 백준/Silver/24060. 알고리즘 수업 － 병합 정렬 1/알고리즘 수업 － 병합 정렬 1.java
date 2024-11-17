import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int k;
    static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, n - 1);

        System.out.println(-1);
    }

    private static void mergeSort(int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;

            mergeSort(l, mid);
            mergeSort(mid + 1, r);
            merge(l, mid, r);
        }
    }

    private static void merge(int l, int mid, int r) {

        int[] temp = new int[r - l + 1];
        int i = l;
        int j = mid + 1;
        int idx = 0;

        while (i <= mid && j <= r) {
            if (arr[i] <= arr[j]) {
                temp[idx++] = arr[i++];
            } else {
                temp[idx++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[idx++] = arr[i++];
        }

        while (j <= r) {
            temp[idx++] = arr[j++];
        }

        for (i = l, idx = 0; i <= r; i++, idx++) {
            arr[i] = temp[idx];
            count++;
            if (count == k) {
                System.out.println(arr[i]);
                System.exit(0);
            }
        }
    }
}