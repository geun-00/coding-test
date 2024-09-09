import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] left = Arrays.copyOfRange(arr, 0, n / 2);
        int[] right = Arrays.copyOfRange(arr, n / 2, n);

        ArrayList<Long> leftSum = new ArrayList<>();
        ArrayList<Long> rightSum = new ArrayList<>();

        getSums(left, 0, 0, leftSum);
        getSums(right, 0, 0, rightSum);

        rightSum.sort(null);

        long count = 0;

        for (long sum : leftSum) {
            long target = s - sum;

            count += upperBound(target, rightSum) - lowerBound(target, rightSum);
        }

        if (s == 0) {
            count--;
        }

        System.out.println(count);
    }

    private static int upperBound(long target, ArrayList<Long> rightSum) {
        int left = 0;
        int right = rightSum.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (rightSum.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static int lowerBound(long target, ArrayList<Long> rightSum) {
        int left = 0;
        int right = rightSum.size();

        while (left < right) {
            int mid = (left + right) / 2;

            if (rightSum.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static void getSums(int[] arr, int idx, long sum, ArrayList<Long> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }

        getSums(arr, idx + 1, sum + arr[idx], list);
        getSums(arr, idx + 1, sum, list);
    }
}
