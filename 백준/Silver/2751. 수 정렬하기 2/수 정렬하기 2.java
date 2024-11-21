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

//        quickSort(arr, 0, n - 1);
        mergeSort(arr, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.print(sb);
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        int mid = (l + r) / 2;
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {

        int i = l;
        int j = mid + 1;
        int idx = l;

        while (i <= mid && j <= r) {
            if (arr[i] < arr[j]) {
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

        for (int k = l; k <= r; k++) {
            arr[k] = temp[k];
        }
    }

    private static void quickSort(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }

        int mid = s + (e - s) / 2;
        int pivot = arr[mid];

        int low = s;
        int high = e;

        while (low <= high) {
            while (arr[low] < pivot) {
                low++;
            }

            while (arr[high] > pivot) {
                high--;
            }

            if (low <= high) {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }

        if (s < high) {
            quickSort(arr, s, high);
        }
        if (low < e) {
            quickSort(arr, low, e);
        }
    }
}