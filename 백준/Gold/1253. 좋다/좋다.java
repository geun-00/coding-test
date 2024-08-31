import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(twoPointer(arr , n));
        //System.out.println(binarySearch(arr, n));
    }

    private static int twoPointer(int[] arr, int n) {

        int count = 0;

        for (int i = 0; i < n; i++) {
            int left = 0;
            int right = n - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                int sum = arr[left] + arr[right];

                if (sum == arr[i]) {
                    count++;
                    break;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return count;
    }

    private static int binarySearch(int[] arr, int n) {

        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {

                    int target = arr[i] - arr[j];
                    int index = Arrays.binarySearch(arr, target);

                    if (index >= 0 && index != i && index != j) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }
}
