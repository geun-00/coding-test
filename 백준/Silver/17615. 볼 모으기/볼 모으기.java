import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[] arr = br.readLine().toCharArray();

        int red = 0, blue = 0;

        for (char c : arr) {
            if (c == 'R') {
                red++;
            } else {
                blue++;
            }
        }

        int leftRed = getLeftCount(n, arr, 'R');
        int rightRed = getRightCount(n, arr, 'R');

        int minRed = Math.min(red - leftRed, red - rightRed);

        int leftBlue = getLeftCount(n, arr, 'B');
        int rightBlue = getRightCount(n, arr, 'B');

        int minBlue = Math.min(blue - leftBlue, blue - rightBlue);

        System.out.println(Math.min(minRed, minBlue));
    }

    private static int getLeftCount(int n, char[] arr, char color) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] == color) count++;
            else break;
        }
        return count;
    }

    private static int getRightCount(int n, char[] arr, char color) {
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] == color) count++;
            else break;
        }
        return count;
    }
}