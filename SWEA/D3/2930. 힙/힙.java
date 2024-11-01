import java.util.*;
import java.io.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= t ; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			int n = Integer.parseInt(br.readLine());
			
			PriorityQueue<Integer> qu = new PriorityQueue<Integer>(Collections.reverseOrder());
			
			for(int i = 0; i < n; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				if("1".equals(st.nextToken())) {
					qu.offer(Integer.parseInt(st.nextToken()));
				} else {
					if(qu.isEmpty()) {
						sb.append(-1).append(" ");
					}else {
						sb.append(qu.poll()).append(" ");
					}
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}