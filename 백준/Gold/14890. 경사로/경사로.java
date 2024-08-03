import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, l;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (canRow(i)) {
                count++;
            }
            if (canCol(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean canRow(int r) {

        boolean[] incline = new boolean[n];

        for (int i = 0; i < n - 1; i++) {
            int diff = map[r][i] - map[r][i + 1];

            if (Math.abs(diff) > 1) {
                return false;
            }

            //내려가야 하는 경우
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {

                    if (i + j >= n || incline[i + j] || map[r][i] - 1 != map[r][i + j]) {
                        return false;
                    }

                    incline[i + j] = true;
                }
            }
            //올라가야 하는 경우
            else if (diff == -1) {
                for (int j = 0; j < l; j++) {

                    if (i - j < 0 || incline[i - j] || map[r][i] != map[r][i - j]) {
                        return false;
                    }

                    incline[i - j] = true;
                }
            }
        }

        return true;
    }

    private static boolean canCol(int c) {

        boolean[] incline = new boolean[n]; //경사로 설치 여부

        for (int i = 0; i < n - 1; i++) {
            int diff = map[i][c] - map[i + 1][c];

            if (Math.abs(diff) > 1) {
                return false;
            }

            //내려가야 하는 경우
            if (diff == 1) {
                for (int j = 1; j <= l; j++) {

                    if (i + j >= n || incline[i + j] || map[i][c] - 1 != map[i + j][c]) {
                        return false;
                    }

                    incline[i + j] = true;

                }
            }
            //올라가야 하는 경우
            else if (diff == -1) {
                for (int j = 0; j < l; j++) {

                    if (i - j < 0 || incline[i - j] || map[i][c] != map[i - j][c]) {
                        return false;
                    }
                    
                    incline[i - j] = true;
                }
            }
        }

        return true;
    }
}

