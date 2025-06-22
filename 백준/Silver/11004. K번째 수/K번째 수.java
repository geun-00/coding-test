import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(0, n - 1, k - 1);
        System.out.println(arr[k - 1]);
    }

    private static void quickSort(int start, int end, int k) {
        if (start < end) {
            int pivot = partition(start, end);

            if (pivot < k) {
                quickSort(pivot + 1, end, k);
            } else if (k < pivot) {
                quickSort(start, pivot - 1, k);
            }
        }
    }

    private static int partition(int start, int end) {
        if (start + 1 == end) {
            if (arr[start] > arr[end]) {
                swap(start, end);
            }
            return end;
        }
        
        int mid = (start + end) / 2;
        int pivot = arr[mid];
        swap(start, mid);

        int left = start + 1;
        int right = end;

        while (left <= right) {
            while (right > 0 && arr[right] > pivot) {
                right--;
            }
            while (left < arr.length - 1 && arr[left] < pivot) {
                left++;
            }

            if (left <= right) {
                swap(left, right);
                left++;
                right--;
            }
        }

        arr[start] = arr[right];
        arr[right] = pivot;

        return right;
    }

    private static void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}