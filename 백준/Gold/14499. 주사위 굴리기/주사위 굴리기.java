import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int TOP = 0;
    static final int BACK = 1;
    static final int RIGHT = 2;
    static final int LEFT = 3;
    static final int FRONT = 4;
    static final int BOTTOM = 5;
    
    static int[] dice = new int[6];
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < k; i++) {
            int d = Integer.parseInt(st.nextToken()) - 1;

            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            rollDice(d);

            if (map[nx][ny] != 0) {
                dice[BOTTOM] = map[nx][ny];
                map[nx][ny] = 0;
            } else {
                map[nx][ny] = dice[BOTTOM];
            }

            System.out.println(dice[TOP]);

            x = nx;
            y = ny;
        }
    }

    private static void rollDice(int d) {

        int[] temp = new int[6];
        for (int i = 0; i < 6; i++) {
            temp[i] = dice[i];
        }

        switch (d) {
            case 0:
                dice[TOP] = temp[LEFT];
                dice[RIGHT] = temp[TOP];
                dice[LEFT] = temp[BOTTOM];
                dice[BOTTOM] = temp[RIGHT];
                break;
            case 1:
                dice[TOP] = temp[RIGHT];
                dice[RIGHT] = temp[BOTTOM];
                dice[LEFT] = temp[TOP];
                dice[BOTTOM] = temp[LEFT];
                break;
            case 2:
                dice[TOP] = temp[FRONT];
                dice[FRONT] = temp[BOTTOM];
                dice[BACK] = temp[TOP];
                dice[BOTTOM] = temp[BACK];
                break;
            case 3:
                dice[TOP] = temp[BACK];
                dice[FRONT] = temp[TOP];
                dice[BACK] = temp[BOTTOM];
                dice[BOTTOM] = temp[FRONT];
                break;
        }
    }

}
