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
        int h = Integer.parseInt(st.nextToken());

        int[] up = new int[n / 2]; //석순
        int[] down = new int[n / 2]; //종유석

        for (int i = 0; i < n / 2; i++) {
            up[i] = Integer.parseInt(br.readLine());
            down[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(up);
        Arrays.sort(down);

        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int i = 1; i <= h; i++) {

            int upCount = up.length - binarySearch(up, i);
            int downCount = down.length - binarySearch(down, h - i + 1);

            int temp = upCount + downCount;

            if (temp < min) {
                min = temp;
                count = 1;
            } else if (temp == min) {
                count++;
            }
        }

        System.out.println(min + " " + count);
    }

    private static int binarySearch(int[] arr, int target) {

        int s = 0;
        int e = arr.length;

        while (s < e) {

            int mid = (s + e) / 2;

            if (arr[mid] < target) {
                s = mid + 1;
            } else {
                e = mid;
            }
        }

        return s;
    }
}
