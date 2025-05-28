import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (t-- > 0) {
            int x = 0, y = 0;
            int dir = 0;

            int maxX, maxY;
            int minX, minY;
            maxX = maxY = 0;
            minX = minY = 0;

            for (char com : br.readLine().toCharArray()) {

                switch (com) {
                    case 'F':
                        x += dx[dir];
                        y += dy[dir];
                        break;
                    case 'B':
                        x -= dx[dir];
                        y -= dy[dir];
                        break;
                    case 'L':
                        dir = (dir - 1 + 4) % 4;
                        break;
                    case 'R':
                        dir = (dir + 1) % 4;
                }

                maxX = Math.max(maxX, x);
                maxY = Math.max(maxY, y);

                minX = Math.min(minX, x);
                minY = Math.min(minY, y);
            }

            System.out.println((maxX - minX) * (maxY - minY));
        }
    }
}