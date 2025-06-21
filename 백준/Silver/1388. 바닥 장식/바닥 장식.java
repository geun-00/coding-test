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

        char[][] arr = new char[n][m];
        for (int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        int ans = 0;

        boolean[][] visit = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visit[i][j]) {
                    ans++;

                    if (arr[i][j] == '-') {
                        int temp = j;
                        while (temp < m && arr[i][temp] == '-') {
                            visit[i][temp++] = true;
                        }
                    } else {
                        int temp = i;
                        while (temp < n && arr[temp][j] == '|') {
                            visit[temp++][j] = true;
                        }
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
