import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr = new int[6][6];

        String s = br.readLine();
        int px = s.charAt(0) - 'A';
        int py = s.charAt(1) - '0' - 1;

        int fx = px;
        int fy = py;

        int lx = 0;
        int ly = 0;

        arr[px][py]++;

        int[][] move = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2}, {1, -2}, {-1, -2}};

        for (int i = 0; i < 35; i++) {
            s = br.readLine();
            int x = s.charAt(0) - 'A';
            int y = s.charAt(1) - '0' - 1;

            if (arr[x][y] == 1) {
                System.out.println("Invalid");
                return;
            }

            boolean flag = false;
            for (int[] row : move) {
                int r = px + row[0];
                int c = py + row[1];

                if (r == x && c == y) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println("Invalid");
                return;
            }

            arr[x][y]++;
            px = x;
            py = y;
            lx = x;
            ly = y;
        }

        for (int[] row : move) {
            int r = fx + row[0];
            int c = fy + row[1];

            if (r == lx && c == ly) {
                System.out.println("Valid");
                return;
            }
        }

        System.out.println("Invalid");
    }
}