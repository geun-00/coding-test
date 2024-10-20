import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n + 1][n + 1]; //각 학생의 위치
        int[][] arr = new int[n * n + 1][4];   //각 학생이 좋아하는 4명의 학생
        int[] order = new int[n * n + 1];

        for (int i = 1; i <= n * n; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            order[i] = s;

            for (int j = 0; j < 4; j++) {
                arr[s][j] = Integer.parseInt(st.nextToken());
            }
        }

        int first = order[1];
        board[2][2] = first;

        for (int i = 2; i < n * n; i++) {
            int s = order[i];

            ArrayList<Point> temp = new ArrayList<>();

            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {

                    if (board[x][y] > 0) continue;

                    int like = 0;
                    int empty = 0;

                    for (int d = 0; d < 4; d++) {
                        int nx = x + dx[d];
                        int ny = y + dy[d];

                        if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
                            if (board[nx][ny] == 0) {
                                empty++;
                            } else {
                                for (int j = 0; j < 4; j++) {
                                    if (board[nx][ny] == arr[s][j]) {
                                        like++;
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    temp.add(new Point(x, y, like, empty));
                }
            }

            temp.sort((o1, o2) -> {
                if (o1.like != o2.like) {
                    return o2.like - o1.like;
                } else if (o1.empty != o2.empty) {
                    return o2.empty - o1.empty;
                } else if (o1.x != o2.x) {
                    return o1.x - o2.x;
                }
                return o1.y - o2.y;
            });

            Point p = temp.get(0);
            board[p.x][p.y] = s;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = order[n * n];
                    break;
                }
            }
        }

        int result = 0;
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                int s = board[x][y];
                int like = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
                        for (int j = 0; j < 4; j++) {
                            if (board[nx][ny] == arr[s][j]) {
                                like++;
                                break;
                            }
                        }
                    }
                }

                if (like > 0) {
                    result += (int) Math.pow(10, like - 1);
                }
            }
        }

        System.out.println(result);
    }

    static class Point {

        int x, y, like, empty;

        public Point(int x, int y, int like, int empty) {
            this.x = x;
            this.y = y;
            this.like = like;
            this.empty = empty;
        }
    }
}
