import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<Integer> lowerM = new ArrayList<>();
        List<Integer> higherM = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());
            
            if (height < 0) lowerM.add(Math.abs(height));
            else higherM.add(height);
        }
        
        st = new StringTokenizer(br.readLine());
        List<Integer> lowerW = new ArrayList<>();
        List<Integer> higherW = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(st.nextToken());

            if (height < 0) lowerW.add(Math.abs(height));
            else higherW.add(height);
        }

        Collections.sort(lowerM);
        Collections.sort(higherM);
        Collections.sort(lowerW);
        Collections.sort(higherW);

        System.out.println(solve(lowerM, higherW) + solve(lowerW, higherM));
    }

    private static int solve(List<Integer> h1, List<Integer> h2) {
        int left = 0 , right = 0;
        int ans = 0;
        while (left < h1.size() && right < h2.size()) {
            if (h1.get(left) > h2.get(right)) {
                ans++;
                left++;
                right++;
            } else {
                left++;
            }
        }
        return ans;
    }
}
