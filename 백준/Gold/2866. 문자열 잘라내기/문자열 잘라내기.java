import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static char[][] table;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        table = new char[r][c];

        for (int i = 0; i < r; i++) {
            table[i] = br.readLine().toCharArray();
        }

        int s = 0;
        int e = r - 1;
        int result = 0;

        while (s <= e) {
            int mid = (s + e) / 2;
            if (check(r, mid, c)) {
                e = mid - 1;
            } else {
                result = mid;
                s = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean check(int row, int mid, int col) {

        HashSet<String> set = new HashSet<>();

        for (int c = 0; c < col; c++) {
            StringBuilder sb = new StringBuilder();
            for (int r = mid; r < row; r++) {
                sb.append(table[r][c]);
            }

            if (!set.add(sb.toString())) {
                return true;
            }
        }

        return false;
    }
}
