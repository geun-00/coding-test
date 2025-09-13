import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int min = getTimeNum(arr);

        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                for (int k = 1; k <= 9; k++) {
                    for (int l = 1; l <= 9; l++) {
                        set.add(getTimeNum(new int[]{i, j, k, l}));
                    }
                }
            }
        }

        List<Integer> list = new ArrayList<>(set);
        list.sort(null);

        System.out.println(list.indexOf(min) + 1);
    }

    public static int getTimeNum(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int b = arr[i] * 1000 + arr[(i + 1) % 4] * 100 + arr[(i + 2) % 4] * 10 + arr[(i + 3) % 4];
            min = Math.min(min, b);
        }
        return min;
    }
}