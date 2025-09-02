import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int k = Integer.parseInt(br.readLine());

        if ((c * r) < k) {
            System.out.println(0);
            return;
        }

        int[][] arr = new int[r][c];
        int x = r - 1;
        int y = 0;
        int count = 1;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int dir = 0;

        while (count < k) {
            count++;
            arr[x][y] = 1;

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= r || ny >= c || arr[nx][ny] == 1) {
                dir = (dir + 1) % 4;
                nx = x + dx[dir];
                ny = y + dy[dir];
            }

            x = nx;
            y = ny;
        }

        System.out.println((y + 1) + " " + (r - x));
    }
}