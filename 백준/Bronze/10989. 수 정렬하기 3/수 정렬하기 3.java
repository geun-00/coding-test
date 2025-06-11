import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(arr, 0, n - 1);
        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append("\n");
        }
        System.out.print(sb);
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int index = left;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                temp[index] = arr[i++];
            } else {
                temp[index] = arr[j++];
            }
            index++;
        }

        while (i <= mid) temp[index++] = arr[i++];
        while (j <= right) temp[index++] = arr[j++];

        for (int k = left; k <= right; k++) {
            arr[k] = temp[k];
        }
    }
}