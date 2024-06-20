import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int n, m;
    static ArrayList<Point> chicken = new ArrayList<>();
    static ArrayList<Point> house = new ArrayList<>();
    static int min = Integer.MAX_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];

        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 2) {
                    chicken.add(new Point(i, j));
                } else if (map[i][j] == 1) {
                    house.add(new Point(i, j));
                }
            }
        }

        solve(0,0);
        System.out.println(min);
    }

    private static void solve(int start, int depth) {
        if (depth == m) {
            min = Math.min(min, getMin());
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            arr[depth] = i;
            solve(i + 1, depth + 1);
        }
    }

    private static int getMin() {

        int sum = 0;

        for (Point h : house) {

            int temp = Integer.MAX_VALUE;

            for (int i = 0; i < m; i++) {
                Point c = chicken.get(arr[i]);

                int dist = Math.abs(h.x - c.x) + Math.abs(h.y - c.y);
                temp = Math.min(temp, dist);
            }

            sum += temp;
        }

        return sum;
    }
}
