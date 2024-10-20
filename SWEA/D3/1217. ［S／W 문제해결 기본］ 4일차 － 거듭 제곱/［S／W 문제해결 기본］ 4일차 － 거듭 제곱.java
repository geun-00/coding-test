import java.util.Scanner;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
        
        for(int i = 1; i <= 10; i++) {
            int t = sc.nextInt();
        	int n = sc.nextInt();
            int m = sc.nextInt();
            
            System.out.println("#" + i + " " + solve(n, m));
        }
	}
    
    public static int solve(int n, int m){
    	if(m == 1) return n;
        
        return n * solve(n, m - 1);
    }
}