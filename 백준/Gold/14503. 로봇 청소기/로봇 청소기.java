import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        int x = r;
        int y = c;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (true) {
            if (map[x][y] == 0) {
                map[x][y] = 2;
                count++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (map[nx][ny] == 0) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                int tempX = 0, tempY = 0;

                switch (d) {
                    case 0:
                        tempX = x + 1;
                        tempY = y;
                        break;
                    case 1:
                        tempX = x;
                        tempY = y - 1;
                        break;
                    case 2:
                        tempX = x - 1;
                        tempY = y;
                        break;
                    case 3:
                        tempX = x;
                        tempY = y + 1;
                }

                if (map[tempX][tempY] == 1) {
                    break;
                } else {
                    x = tempX;
                    y = tempY;
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    d = (d + 3) % 4;

                    int tempX = 0, tempY = 0;

                    switch (d) {
                        case 0:
                            tempX = x - 1;
                            tempY = y;
                            break;
                        case 1:
                            tempX = x;
                            tempY = y + 1;
                            break;
                        case 2:
                            tempX = x + 1;
                            tempY = y;
                            break;
                        case 3:
                            tempX = x;
                            tempY = y - 1;
                    }

                    if (map[tempX][tempY] == 0) {
                        x = tempX;
                        y = tempY;
                        break;
                    }
                }
            }
        }

        System.out.println(count);
    }
}
