import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String king = st.nextToken();
        String stone = st.nextToken();
        int n = Integer.parseInt(st.nextToken());

        int kingX = (king.charAt(1) - '0') - 1;
        int kingY = (king.charAt(0) - 'A');

        int stoneX = (stone.charAt(1) - '0') - 1;
        int stoneY = (stone.charAt(0) - 'A');

        int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
        int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

        for (int i = 0; i < n; i++) {

            int dir = getDir(br.readLine());

            int kingNX = kingX + dx[dir];
            int kingNY = kingY + dy[dir];

            if (kingNX < 0 || kingNY < 0 || kingNX >= 8 || kingNY >= 8) {
                continue;
            }

            if (kingNX == stoneX && kingNY == stoneY) {

                int stoneNX = stoneX + dx[dir];
                int stoneNY = stoneY + dy[dir];

                if (stoneNX < 0 || stoneNY < 0 || stoneNX >= 8 || stoneNY >= 8) {
                    continue;
                }

                stoneX = stoneNX;
                stoneY = stoneNY;
            }

            kingX = kingNX;
            kingY = kingNY;
        }

        System.out.println((char) (kingY + 'A') + "" + (char) (kingX + '0' + 1));
        System.out.println((char) (stoneY + 'A') + "" + (char) (stoneX + '0' + 1));
    }

    private static int getDir(String s) {

        switch (s) {
            case "R":
                return 0;
            case "L":
                return 1;
            case "B":
                return 2;
            case "T":
                return 3;
            case "RT":
                return 4;
            case "LT":
                return 5;
            case "RB":
                return 6;
            case "LB":
                return 7;
        }

        return -1;
    }
}