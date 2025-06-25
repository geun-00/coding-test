import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] temp;
    static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            indexMap.put(num, i);
        }

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = indexMap.get(num);
        }

        mergeSort(arr, 0, n - 1);
        System.out.println(ans);
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int i = start;
        int j = mid + 1;
        int index = i;

        while (i <= mid && j <= end) {
            if (arr[i] < arr[j]) {
                temp[index++] = arr[i++];
            } else {
                temp[index++] = arr[j++];
                ans += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[index++] = arr[i++];
        }

        while (j <= end) {
            temp[index++] = arr[j++];
        }

        for (int k = start; k <= end; k++) {
            arr[k] = temp[k];
        }
    }
}