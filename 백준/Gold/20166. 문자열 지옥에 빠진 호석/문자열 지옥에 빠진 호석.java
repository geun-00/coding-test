import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static HashMap<String, Integer> map = new HashMap<>();
    static int maxLen = 1;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    static int n, m;
    static char[][] word;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        word = new char[n][m];

        for (int i = 0; i < n; i++) {
            word[i] = br.readLine().toCharArray();
        }

        String[] target = new String[k];
        for (int i = 0; i < k; i++) {
            target[i] = br.readLine();
            maxLen = Math.max(maxLen, target[i].length());
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 1, word[i][j] + "");
            }
        }

        for (String s : target) {
            System.out.println(map.getOrDefault(s, 0));
        }
    }

    private static void dfs(int x, int y, int len, String now) {
        map.put(now, map.getOrDefault(now, 0) + 1);

        if (len == maxLen) {
            return;
        }

        for (int i = 0; i < 8; i++) {
            int nx = (x + dx[i] + n) % n;
            int ny = (y + dy[i] + m) % m;

            dfs(nx, ny, len + 1, now + word[nx][ny]);
        }
    }
}
