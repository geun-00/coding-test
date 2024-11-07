import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] counting = new int[8001];

        double sum = 0;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            sum += arr[i];
            counting[arr[i] + 4000]++;
        }

        sb.append(Math.round(sum / n)).append("\n");

        Arrays.sort(arr);

        sb.append(arr[n / 2]).append("\n");

        int freq = 0;
        for (int i = 0; i <= 8000; i++) {
            freq = Math.max(freq, counting[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i <= 8000; i++) {
            if (counting[i] == freq) {
                list.add(i - 4000);
            }
        }

        if (list.size() == 1) {
            sb.append(list.get(0)).append("\n");
        } else {
            list.sort(null);
            sb.append(list.get(1)).append("\n");
        }

        sb.append(arr[n - 1] - arr[0]);

        System.out.print(sb);
    }
}