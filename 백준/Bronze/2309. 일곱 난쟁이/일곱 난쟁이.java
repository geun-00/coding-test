import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int total = 0;
        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            total += arr[i];
        }

        int ban1 = 0, ban2 = 0;

        T:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                int temp = total - (arr[i] + arr[j]);

                if (temp == 100) {
                    ban1 = i;
                    ban2 = j;
                    break T;
                }
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (i != ban1 && i != ban2) {
                list.add(arr[i]);
            }
        }

        list.sort(null);
        for (int num : list) {
            System.out.println(num);
        }
    }
}