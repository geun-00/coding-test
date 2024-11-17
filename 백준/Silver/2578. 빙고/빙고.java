import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] bingo = new boolean[5][5];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[5][5];

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int num = Integer.parseInt(st.nextToken());

                for (int x = 0; x < 5; x++) {
                    for (int y = 0; y < 5; y++) {
                        if (arr[x][y] == num) {
                            bingo[x][y] = true;
                            break;
                        }
                    }
                }

                if (check()) {
                    System.out.println(5 * i + j + 1);
                    return;
                }
            }
        }
    }

    private static boolean check() {

        int count = 0;

        boolean flag;
        for (int i = 0; i < 5; i++) {

            flag = true;

            for (int j = 0; j < 5; j++) {
                if (!bingo[i][j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) count++;

            flag = true;

            for (int j = 0; j < 5; j++) {
                if (!bingo[j][i]) {
                    flag = false;
                    break;
                }
            }

            if (flag) count++;
        }

        flag = true;

        for (int i = 0; i < 5; i++) {
            if (!bingo[i][i]) {
                flag = false;
                break;
            }
        }

        if (flag) count++;

        flag = true;

        for (int i = 0; i < 5; i++) {
            if (!bingo[i][5 - i - 1]) {
                flag = false;
                break;
            }
        }

        if (flag) count++;

        return count >= 3;
    }
}