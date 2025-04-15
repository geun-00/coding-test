import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] bingo = {
        {0, 1, 2},
        {3, 4, 5},
        {6, 7, 8},
        {0, 3, 6},
        {1, 4, 7},
        {2, 5, 8},
        {0, 4, 8},
        {2, 4, 6},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String valid = "valid";
        String invalid = "invalid";

        String input;
        while (!(input = br.readLine()).equals("end")) {
            char[] arr = input.toCharArray();

            int x = 0, o = 0, empty = 0;
            for (char c : arr) {
                if (c == 'X') x++;
                else if (c == 'O') o++;
                else if (c == '.') empty++;
            }

            if (o > x || x - o > 1) {
                sb.append(invalid)
                  .append("\n");
                continue;
            }

            boolean XBingo = isBingo(arr, 'X');
            boolean OBingo = isBingo(arr, 'O');

            if (XBingo && OBingo) {
                sb.append(invalid)
                  .append("\n");
            } else if (XBingo) {
                if (x - o == 1) sb.append(valid).append("\n");
                else sb.append(invalid).append("\n");
            } else if (OBingo) {
                if (x == o) sb.append(valid).append("\n");
                else sb.append(invalid).append("\n");
            } else {
                if (empty == 0) sb.append(valid).append("\n");
                else sb.append(invalid).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static boolean isBingo(char[] arr, char target) {
        for (int[] b : bingo) {
            if (arr[b[0]] == target && arr[b[1]] == target && arr[b[2]] == target) {
                return true;
            }
        }
        return false;
    }
}