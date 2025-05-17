import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] matches = {
            {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5},
            {1, 2}, {1, 3}, {1, 4}, {1, 5},
            {2, 3}, {2, 4}, {2, 5},
            {3, 4}, {3, 5},
            {4, 5}
    };
    static final int WIN = 0;
    static final int DRAW = 1;
    static final int LOSE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String[] result = br.readLine().split(" ");

            int[][] arr = new int[6][3];
            for (int j = 0; j < result.length; j++) {
                arr[j / 3][j % 3] = Integer.parseInt(result[j]);
            }

            System.out.print(!isValid(arr) ? "0 " : solve(0, arr) ? "1 " : "0 ");
        }
    }

    private static boolean isValid(int[][] arr) {
        for (int[] row : arr) {
            if (row[0] + row[1] + row[2] != 5) {
                return false;
            }
        }
        return true;
    }

    private static boolean solve(int index, int[][] arr) {
        if (index == 15) {
            return true;
        }

        int a = matches[index][0];
        int b = matches[index][1];

        if (arr[a][WIN] > 0 && arr[b][LOSE] > 0) {
            arr[a][WIN]--;
            arr[b][LOSE]--;
            if (solve(index + 1, arr)) {
                return true;
            }
            arr[a][WIN]++;
            arr[b][LOSE]++;
        }
        if (arr[a][DRAW] > 0 && arr[b][DRAW] > 0) {
            arr[a][DRAW]--;
            arr[b][DRAW]--;
            if (solve(index + 1, arr)) {
                return true;
            }
            arr[a][DRAW]++;
            arr[b][DRAW]++;
        }
        if (arr[a][LOSE] > 0 && arr[b][WIN] > 0) {
            arr[a][LOSE]--;
            arr[b][WIN]--;
            if (solve(index + 1, arr)) {
                return true;
            }
            arr[a][LOSE]++;
            arr[b][WIN]++;
        }

        return false;
    }
}