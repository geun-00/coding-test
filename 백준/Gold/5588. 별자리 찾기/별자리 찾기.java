import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        int[][] toFindStars = new int[m][2];

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            toFindStars[i][0] = x;
            toFindStars[i][1] = y;
        }

        Map<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = 0; j < m; j++) {
                int nx = x - toFindStars[j][0];
                int ny = y - toFindStars[j][1];

                String point = nx + "," + ny;
                map.put(point, map.getOrDefault(point, 0) + 1);

            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            if (entry.getValue() == m) {

                String point = entry.getKey();

                String[] arr = point.split(",");

                System.out.println(arr[0] + " " + arr[1]);

                break;
            }
        }
    }
}