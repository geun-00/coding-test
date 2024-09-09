import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> subArrSum_A = getSubArrSum(a);
        HashMap<Integer, Integer> subArrSum_B = getSubArrSum(b);

        long count = 0;

        for (int num : subArrSum_A.keySet()) {
            int temp = t - num;
            if (subArrSum_B.containsKey(temp)) {
                count += (long) subArrSum_A.get(num) * subArrSum_B.get(temp);
            }
        }

        System.out.println(count);
    }

    private static HashMap<Integer, Integer> getSubArrSum(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        return map;
    }
}
