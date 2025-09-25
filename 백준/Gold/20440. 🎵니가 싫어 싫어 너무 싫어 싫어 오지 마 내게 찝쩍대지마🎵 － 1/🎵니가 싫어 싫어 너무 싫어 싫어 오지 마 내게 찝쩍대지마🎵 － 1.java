import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        int[] temp = new int[n * 2];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            temp[2 * i] = arr[i][0];
            temp[2 * i + 1] = arr[i][1];
        }

        Arrays.sort(temp);
        int m = temp.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map.put(temp[i], i);
        }

        int[] diff = new int[m];
        for (int i = 0; i < n; i++) {
            diff[map.get(arr[i][0])]++;
            diff[map.get(arr[i][1])]--;
        }

        int cur = 0, max = 0;
        int start = -1, end = -1;

        for (int i = 0; i < m; i++) {
            cur += diff[i];

            if (max < cur) {
                max = cur;
                start = temp[i];
                end = -1;
            } else if (cur < max && end == -1) {
                end = temp[i];
            }
        }

        if (end == -1) {
            end = temp[m - 1];
        }

        System.out.println(max);
        System.out.println(start + " " + end);
    }
}